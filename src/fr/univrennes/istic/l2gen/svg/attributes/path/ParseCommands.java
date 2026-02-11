package fr.univrennes.istic.l2gen.svg.attributes.path;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/**
 * Classe utilitaire pour analyser les commandes SVG path d'une chaîne.
 * Convertit une chaîne de commandes SVG (ex: "M 10 10 L 20 20") en une liste
 * d'objets de commande.
 */
public final class ParseCommands {
    /**
     * Analyse une chaîne de commandes SVG path et retourne une liste de commandes
     * parsées.
     * Supporte les commandes standards SVG : M, m, L, l, H, h, V, v, C, c, S, s, Q,
     * q, T, t, A, a, Z, z.
     * 
     * @param raw la chaîne de commandes SVG path (ex: "M 10 10 L 20 20 Z")
     * @return une liste de commandes parsées implémentant IPathCommand
     */
    public static List<IPathCommand> parse(String raw) {
        Pattern tokenPattern = Pattern.compile(
                "[MmLlHhVvCcSsQqTtAaZz]|[+-]?(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE][+-]?\\d+)?");

        Matcher matcher = tokenPattern.matcher(raw);
        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        List<IPathCommand> commands = new ArrayList<>();

        int i = 0;
        while (i < tokens.size()) {
            String token = tokens.get(i);

            if (!token.matches("[MmLlHhVvCcSsQqTtAaZz]")) {
                i++;
                continue;
            }

            char cmd = token.charAt(0);
            i++;

            char implicitCmd = cmd;
            if (cmd == 'M') {
                implicitCmd = 'L';

            }
            if (cmd == 'm') {
                implicitCmd = 'l';
            }

            boolean firstOcc = true;
            do {
                char currentCmd = firstOcc ? cmd : implicitCmd;
                firstOcc = false;

                switch (currentCmd) {
                    case 'M': {
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(x, y, MoveCommandType.ABSOLUTE));
                        break;
                    }
                    case 'm': {
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(x, y, MoveCommandType.RELATIVE));
                        break;
                    }
                    case 'L': {
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(x, y, MoveCommandType.LINE));
                        break;
                    }
                    case 'l': {
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(x, y, MoveCommandType.LINE_RELATIVE));
                        break;
                    }
                    case 'H': {
                        double x = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(x, null, MoveCommandType.HORIZONTAL));
                        break;
                    }
                    case 'h': {
                        double x = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(x, null, MoveCommandType.HORIZONTAL_RELATIVE));
                        break;
                    }
                    case 'V': {
                        double y = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(null, y, MoveCommandType.VERTICAL));
                        break;
                    }
                    case 'v': {
                        double y = parseDouble(tokens, i++);
                        commands.add(new MoveCommand(null, y, MoveCommandType.VERTICAL_RELATIVE));
                        break;
                    }
                    case 'C': {
                        double x1 = parseDouble(tokens, i++);
                        double y1 = parseDouble(tokens, i++);
                        double x2 = parseDouble(tokens, i++);
                        double y2 = parseDouble(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new CubicBezierCommand(x1, y1, x2, y2, x, y,
                                CubicBezierCommandType.ABSOLUTE));
                        break;
                    }
                    case 'c': {
                        double x1 = parseDouble(tokens, i++);
                        double y1 = parseDouble(tokens, i++);
                        double x2 = parseDouble(tokens, i++);
                        double y2 = parseDouble(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new CubicBezierCommand(x1, y1, x2, y2, x, y,
                                CubicBezierCommandType.RELATIVE));
                        break;
                    }
                    case 'S': {
                        double x2 = parseDouble(tokens, i++);
                        double y2 = parseDouble(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new CubicBezierCommand(null, null, x2, y2, x, y,
                                CubicBezierCommandType.SMOOTH));
                        break;
                    }
                    case 's': {
                        double x2 = parseDouble(tokens, i++);
                        double y2 = parseDouble(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new CubicBezierCommand(null, null, x2, y2, x, y,
                                CubicBezierCommandType.SMOOTH_RELATIVE));
                        break;
                    }
                    case 'Q': {
                        double x1 = parseDouble(tokens, i++);
                        double y1 = parseDouble(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new QuadBezierCommand(x1, y1, x, y,
                                QuadBezierCommandType.ABSOLUTE));
                        break;
                    }
                    case 'q': {
                        double x1 = parseDouble(tokens, i++);
                        double y1 = parseDouble(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new QuadBezierCommand(x1, y1, x, y,
                                QuadBezierCommandType.RELATIVE));
                        break;
                    }
                    case 'T': {
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new QuadBezierCommand(null, null, x, y,
                                QuadBezierCommandType.SMOOTH));
                        break;
                    }
                    case 't': {
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new QuadBezierCommand(null, null, x, y,
                                QuadBezierCommandType.SMOOTH_RELATIVE));
                        break;
                    }
                    case 'A': {
                        double rx = parseDouble(tokens, i++);
                        double ry = parseDouble(tokens, i++);
                        double xAxisRot = parseDouble(tokens, i++);
                        boolean largeArcFlag = parseFlag(tokens, i++);
                        boolean sweepFlag = parseFlag(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new ArcCommand(rx, ry, xAxisRot, largeArcFlag, sweepFlag,
                                x, y, ArcCommandType.ABSOLUTE));
                        break;
                    }
                    case 'a': {
                        double rx = parseDouble(tokens, i++);
                        double ry = parseDouble(tokens, i++);
                        double xAxisRot = parseDouble(tokens, i++);
                        boolean largeArcFlag = parseFlag(tokens, i++);
                        boolean sweepFlag = parseFlag(tokens, i++);
                        double x = parseDouble(tokens, i++);
                        double y = parseDouble(tokens, i++);
                        commands.add(new ArcCommand(rx, ry, xAxisRot, largeArcFlag, sweepFlag,
                                x, y, ArcCommandType.RELATIVE));
                        break;
                    }
                    case 'Z':
                    case 'z': {
                        commands.add(new CloseCommand());
                        break;
                    }
                    default:
                        break;
                }

            } while (i < tokens.size() && isNumber(tokens.get(i)));
        }

        return commands;
    }

    /**
     * Analyse un double à partir d'une liste de tokens.
     * Retourne 0.0 si l'index est invalide ou si le parsing échoue.
     * 
     * @param tokens la liste de tokens
     * @param index  l'index du token à analyser
     * @return la valeur double parsée, ou 0.0 en cas d'erreur
     */
    private static double parseDouble(List<String> tokens, int index) {
        if (index >= tokens.size())
            return 0.0;
        try {
            return Double.parseDouble(tokens.get(index));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Analyse un flag booléen à partir d'une liste de tokens.
     * Analyse le token comme un double : 0.0 = false, non-zéro = true.
     * 
     * @param tokens la liste de tokens
     * @param index  l'index du token à analyser
     * @return true si la valeur est non-zéro, false sinon
     */
    private static boolean parseFlag(List<String> tokens, int index) {
        return parseDouble(tokens, index) != 0.0;
    }

    /**
     * Vérifie si une chaîne représente un nombre (entier ou décimal).
     * 
     * @param token la chaîne à vérifier
     * @return true si le token est un nombre valide, false sinon
     */
    private static boolean isNumber(String token) {
        return token.matches("[+-]?(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE][+-]?\\d+)?");
    }
}
