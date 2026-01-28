package fr.univrennes.istic.l2gen.geometrie.model.formes;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public class Text implements IForme {
    private final String text;
    private Point center;

    public Text(double x, double y, String text) {
        this.center = new Point(x, y);
        this.text = text;
    }

    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indentation)));
        sb.append("Text ");
        sb.append(text);
        sb.append(" x=");
        sb.append(this.center.x());
        sb.append(" y=");
        sb.append(this.center.y());
        return sb.toString();
    }

    @Override
    public double largeur() {
        return this.text.length();
    }

    @Override
    public double hauteur() {
        return 1;
    }


    @Override
    public Point centre() {
        return this.center;
    }

    @Override
    public void deplacer(double dx, double dy) {
        this.center.plus(dx, dy);
    }

    @Override
    public IForme dupliquer() {
        return new Text(this.center.x(), this.center.y(), this.text);
    }

    @Override
    public void redimensionner(double px, double py) {  
        // Ne rien faire car le texte n'est pas redimensionnable
    }

    @Override
    public String enSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<text x=\"").append(this.center.x()).append("\" y=\"").append(this.center.y())
                .append("\" font-family=\"Arial\" font-size=\"64\" fill=\"black\" text-anchor=\"middle\">")
                .append(this.text)
                .append("</text>\n");

        return sb.toString();
    }

}
