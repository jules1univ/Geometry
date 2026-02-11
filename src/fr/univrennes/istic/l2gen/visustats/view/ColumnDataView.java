package fr.univrennes.istic.l2gen.visustats.view;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Path;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Text;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.interfaces.field.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;

@SVGTag("g")
public class ColumnDataView implements IDataView {
    @SVGField
    private List<IShape> elements;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    private Point center;
    private double barWidth = 40;
    private double spacing = 10;
    private double maxHeight = 200;
    private DataSet data;

    public ColumnDataView() {
        this.elements = new ArrayList<>();
        this.center = new Point(0, 0);
    }

    public ColumnDataView(Point center, double barWidth, double spacing, double maxHeight) {
        this.elements = new ArrayList<>();
        this.center = center;
        this.barWidth = barWidth;
        this.spacing = spacing;
        this.maxHeight = maxHeight;
    }

    @Override
    public void setData(List<DataSet> datasets) {
        if (datasets == null || datasets.isEmpty()) {
            return;
        }
        this.data = datasets.get(0);
        update();
    }

    private void update() {
        this.elements.clear();
        if (this.data == null || this.data.size() == 0) {
            return;
        }
        // Cherche la hauteur max des barres
        double maxValue = this.data.values().stream().mapToDouble(Value::value).max().orElse(1.0);
        // origine X,Y du graphique pour que les barres soient bien centrées
        double baseX = center.getX() - ((data.size() * barWidth + (data.size() - 1) * spacing) / 2.0);
        double baseY = center.getY() + maxHeight / 2;

        for (int i = 0; i < this.data.size(); i++) {
            double val = this.data.getValue(i);
            // valeur normalisée pour eviter avoir graphique trop grand
            double height = (val / maxValue) * maxHeight;

            // coté gauche et droite de la barre
            double left = baseX + i * (barWidth + spacing);
            double right = left + barWidth;
            double top = baseY - height;

            Path bar = new Path();
            bar.draw()
                    .move(left, baseY, false)
                    .line(right, baseY, false)
                    .line(right, top, false)
                    .line(left, top, false)
                    .line(left, baseY, false)
                    .close();

            Color fill = this.data.get(i).color().orElse(this.data.mainColor());
            bar.getStyle()
                    .fillColor(fill)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(1);

            this.elements.add(bar);

            Label defaultLabel = new Label(String.format("%.2f", val));
            Label label = this.data.get(i).label().orElse(defaultLabel);

            Text text = new Text(left + barWidth / 2.0, top - 5, label.name());
            text.getStyle()
                    .fillColor(label.color())
                    .textAnchor("middle")
                    .fontSize(12)
                    .fontFamily("Arial");

            this.elements.add(text);
        }
    }

    @Override
    public IShape copy() {
        return new ColumnDataView(new Point(this.center.getX(), this.center.getY()), this.barWidth, this.spacing,
                this.maxHeight);
    }

    @Override
    public Point getCenter() {
        // pas vraiment le centre mais l'origine du graphique
        return this.center;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indent)));
        sb.append("ColumnView");
        return sb.toString();
    }

    @Override
    public double getHeight() {
        return this.maxHeight;
    }

    @Override
    public SVGStyle getStyle() {
        return this.style;
    }

    @Override
    public SVGTransform getTransform() {
        return this.transform;
    }

    @Override
    public double getWidth() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size() * barWidth + Math.max(0, this.data.size() - 1) * spacing;
    }

    @Override
    public void move(double dx, double dy) {
        this.transform.translate(dx, dy);
    }

    @Override
    public void resize(double px, double py) {
        this.transform.scale(px, py);
    }

    @Override
    public void rotate(double deg) {
        this.transform.rotate(deg, this.getCenter().getX(), this.getCenter().getY());
    }
}