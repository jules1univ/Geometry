package fr.univrennes.istic.l2gen.svg.attributes.path;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.svg.attributes.path.commands.ArcCommand;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.ArcCommandType;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.CloseCommand;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.CubicBezierCommand;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.CubicBezierCommandType;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.IPathCommand;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.MoveCommand;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.MoveCommandType;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.QuadBezierCommand;
import fr.univrennes.istic.l2gen.svg.attributes.path.commands.QuadBezierCommandType;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGAttribute;

/**
 * Représente un chemin SVG implémentant ISVGAttribute.
 * Un chemin est composé d'une série de commandes de tracé (move, line, curve,
 * arc, etc.).
 * Maintient en cache la boîte englobante pour les performances.
 */
public class SVGPath implements ISVGAttribute {

    private List<IPathCommand> commands = new ArrayList<>();

    private boolean isDirty = true;
    private BoundingBox cachedBox = null;

    /**
     * Constructeur par défaut. Crée un chemin vide.
     */
    public SVGPath() {
    }

    /**
     * Constructeur avec chaîne de commandes SVG path.
     * 
     * @param raw la chaîne de commandes (ex: "M 10 10 L 20 20 Z")
     */
    public SVGPath(String raw) {
        this.commands = ParseCommands.parse(raw);
        refreshBox();
    }

    /**
     * Ferme le chemin avec une commande de fermeture.
     * 
     * @return cette instance pour enchainage de méthodes
     */
    public void close() {
        this.commands.add(new CloseCommand());
        refreshBox();
    }

    /**
     * Ajoute une commande de déplacement (move).
     * 
     * @param x        la coordonnée x de destination
     * @param y        la coordonnée y de destination
     * @param relative true si le déplacement est relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath move(double x, double y, boolean relative) {
        this.commands.add(new MoveCommand(x, y, relative ? MoveCommandType.RELATIVE : MoveCommandType.ABSOLUTE));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une commande de ligne.
     * 
     * @param x        la coordonnée x
     * @param y        la coordonnée y
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath line(double x, double y, boolean relative) {
        this.commands.add(new MoveCommand(x, y, relative ? MoveCommandType.LINE_RELATIVE : MoveCommandType.LINE));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une commande de ligne horizontale.
     * 
     * @param x        la coordonnée x
     * @param y        la coordonnée y
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath horizontal(double x, double y, boolean relative) {
        this.commands.add(
                new MoveCommand(x, null, relative ? MoveCommandType.HORIZONTAL_RELATIVE : MoveCommandType.HORIZONTAL));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une commande de ligne verticale.
     * 
     * @param y        la coordonnée y
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath vertical(double y, boolean relative) {
        this.commands
                .add(new MoveCommand(null, y, relative ? MoveCommandType.VERTICAL_RELATIVE : MoveCommandType.VERTICAL));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une courbe de Bézier cubique.
     * 
     * @param x1       coordonnée x du premier point de contrôle
     * @param y1       coordonnée y du premier point de contrôle
     * @param x2       coordonnée x du deuxième point de contrôle
     * @param y2       coordonnée y du deuxième point de contrôle
     * @param x        coordonnée x du point final
     * @param y        coordonnée y du point final
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath cubicBezier(double x1, double y1, double x2, double y2, double x, double y, boolean relative) {
        this.commands.add(new CubicBezierCommand(x1, y1, x2, y2, x, y,
                relative ? CubicBezierCommandType.RELATIVE : CubicBezierCommandType.ABSOLUTE));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une courbe de Bézier cubique lisse (premier point de contrôle
     * implicite).
     * 
     * @param x2       coordonnée x du deuxième point de contrôle
     * @param y2       coordonnée y du deuxième point de contrôle
     * @param x        coordonnée x du point final
     * @param y        coordonnée y du point final
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath cubicBezierSmooth(double x2, double y2, double x, double y, boolean relative) {
        this.commands.add(new CubicBezierCommand(null, null, x2, y2, x, y,
                relative ? CubicBezierCommandType.SMOOTH_RELATIVE : CubicBezierCommandType.SMOOTH));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une courbe de Bézier quadratique.
     * 
     * @param x1       coordonnée x du point de contrôle
     * @param y1       coordonnée y du point de contrôle
     * @param x        coordonnée x du point final
     * @param y        coordonnée y du point final
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath quadBezier(double x1, double y1, double x, double y, boolean relative) {
        this.commands.add(new QuadBezierCommand(x1, y1, x, y,
                relative ? QuadBezierCommandType.RELATIVE : QuadBezierCommandType.ABSOLUTE));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une courbe de Bézier quadratique lisse (point de contrôle implicite).
     * 
     * @param x        coordonnée x du point final
     * @param y        coordonnée y du point final
     * @param relative true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath quadBezierSmooth(double x, double y, boolean relative) {
        this.commands.add(new QuadBezierCommand(null, null, x, y,
                relative ? QuadBezierCommandType.SMOOTH_RELATIVE : QuadBezierCommandType.SMOOTH));
        refreshBox();
        return this;
    }

    /**
     * Ajoute une commande d'arc elliptique.
     * 
     * @param rx            rayon majeur en x
     * @param ry            rayon majeur en y
     * @param xAxisRotation rotation de l'axe x en degrés
     * @param largeArcFlag  true pour l'arc majeur
     * @param sweepFlag     true pour le balayage positif
     * @param x             coordonnée x du point final
     * @param y             coordonnée y du point final
     * @param relative      true si relatif, false si absolu
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath arc(double rx, double ry, double xAxisRotation, boolean largeArcFlag, boolean sweepFlag, double x,
            double y, boolean relative) {
        this.commands.add(new ArcCommand(rx, ry, xAxisRotation, largeArcFlag, sweepFlag, x, y,
                relative ? ArcCommandType.RELATIVE : ArcCommandType.ABSOLUTE));
        refreshBox();
        return this;
    }

    /**
     * Réinitialise le chemin en supprimant toutes les commandes.
     * 
     * @return cette instance pour enchainage de méthodes
     */
    public SVGPath reset() {
        this.commands.clear();
        refreshBox();
        return this;
    }

    /**
     * Marque le cache de la boîte englobante comme obsolète.
     */
    private void refreshBox() {
        this.isDirty = true;
    }

    /**
     * Recalcule et met à jour la boîte englobante du chemin.
     */
    private void updateBox() {
        if (!this.isDirty) {
            return;
        }

        if (this.commands.isEmpty()) {
            this.cachedBox = BoundingBox.empty();
            this.isDirty = false;
            return;
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        double currentX = 0.0;
        double currentY = 0.0;

        double subpathStartX = 0.0;
        double subpathStartY = 0.0;

        for (IPathCommand command : this.commands) {
            if (command instanceof MoveCommand move) {
                double x = move.x() != null ? move.x() : 0.0;
                double y = move.y() != null ? move.y() : 0.0;

                switch (move.type()) {
                    case ABSOLUTE, LINE -> {
                        currentX = x;
                        currentY = y;
                    }
                    case RELATIVE, LINE_RELATIVE -> {
                        currentX += x;
                        currentY += y;
                    }
                    case HORIZONTAL -> {
                        currentX = x;
                    }
                    case HORIZONTAL_RELATIVE -> {
                        currentX += x;
                    }
                    case VERTICAL -> {
                        currentY = y;
                    }
                    case VERTICAL_RELATIVE -> {
                        currentY += y;
                    }
                }

                if (move.type() == MoveCommandType.ABSOLUTE || move.type() == MoveCommandType.RELATIVE) {
                    subpathStartX = currentX;
                    subpathStartY = currentY;
                }

                minX = Math.min(minX, currentX);
                minY = Math.min(minY, currentY);
                maxX = Math.max(maxX, currentX);
                maxY = Math.max(maxY, currentY);

            } else if (command instanceof CubicBezierCommand cubic) {
                double x1, y1, x2, y2, x, y;

                if (cubic.type() == CubicBezierCommandType.RELATIVE
                        || cubic.type() == CubicBezierCommandType.SMOOTH_RELATIVE) {
                    x1 = cubic.x1() != null ? currentX + cubic.x1() : currentX;
                    y1 = cubic.y1() != null ? currentY + cubic.y1() : currentY;
                    x2 = currentX + cubic.x2();
                    y2 = currentY + cubic.y2();
                    x = currentX + cubic.x();
                    y = currentY + cubic.y();
                } else {
                    x1 = cubic.x1() != null ? cubic.x1() : currentX;
                    y1 = cubic.y1() != null ? cubic.y1() : currentY;
                    x2 = cubic.x2();
                    y2 = cubic.y2();
                    x = cubic.x();
                    y = cubic.y();
                }

                minX = Math.min(minX, Math.min(Math.min(x1, x2), x));
                minY = Math.min(minY, Math.min(Math.min(y1, y2), y));
                maxX = Math.max(maxX, Math.max(Math.max(x1, x2), x));
                maxY = Math.max(maxY, Math.max(Math.max(y1, y2), y));

                currentX = x;
                currentY = y;

            } else if (command instanceof QuadBezierCommand quad) {
                double x1, y1, x, y;

                if (quad.type() == QuadBezierCommandType.RELATIVE
                        || quad.type() == QuadBezierCommandType.SMOOTH_RELATIVE) {
                    x1 = quad.x1() != null ? currentX + quad.x1() : currentX;
                    y1 = quad.y1() != null ? currentY + quad.y1() : currentY;
                    x = currentX + quad.x();
                    y = currentY + quad.y();
                } else {
                    x1 = quad.x1() != null ? quad.x1() : currentX;
                    y1 = quad.y1() != null ? quad.y1() : currentY;
                    x = quad.x();
                    y = quad.y();
                }

                minX = Math.min(minX, Math.min(x1, x));
                minY = Math.min(minY, Math.min(y1, y));
                maxX = Math.max(maxX, Math.max(x1, x));
                maxY = Math.max(maxY, Math.max(y1, y));

                currentX = x;
                currentY = y;

            } else if (command instanceof ArcCommand arc) {
                double x, y;

                if (arc.type() == ArcCommandType.RELATIVE) {
                    x = currentX + arc.x();
                    y = currentY + arc.y();
                } else {
                    x = arc.x();
                    y = arc.y();
                }

                minX = Math.min(minX, Math.min(currentX, x));
                minY = Math.min(minY, Math.min(currentY, y));
                maxX = Math.max(maxX, Math.max(currentX, x));
                maxY = Math.max(maxY, Math.max(currentY, y));

                currentX = x;
                currentY = y;

            } else if (command instanceof CloseCommand) {
                currentX = subpathStartX;
                currentY = subpathStartY;
            }
        }

        this.cachedBox = new BoundingBox(minX, minY, maxX, maxY);
        this.isDirty = false;
    }

    /**
     * Retourne la boîte englobante du chemin.
     * Met à jour la boîte si nécessaire.
     * 
     * @return la boîte englobante
     */
    public BoundingBox getBoundingBox() {
        updateBox();
        return this.cachedBox;
    }

    /**
     * Vérifie si le chemin contient au moins une commande.
     * 
     * @return true si le chemin n'est pas vide, false sinon
     */
    @Override
    public boolean hasContent() {
        return !this.commands.isEmpty();
    }

    /**
     * Retourne la représentation en chaîne SVG path du chemin.
     * 
     * @return la chaîne au format SVG path (ex: "M 10 10 L 20 20 Z")
     */
    @Override
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        for (IPathCommand command : this.commands) {
            sb.append(command.getValue());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}