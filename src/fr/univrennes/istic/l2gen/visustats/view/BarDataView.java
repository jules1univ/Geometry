package fr.univrennes.istic.l2gen.visustats.view;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Path;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.interfaces.field.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;

@SVGTag("g")
public class BarDataView implements IDataView {
    @SVGField
    private List<IShape> elements;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    private Point origin;
    private double barWidth = 40;
    private double spacing = 10;
    private double maxHeight = 200;
    private DataSet data;

    public BarDataView() {
        this.elements = new ArrayList<>();
        this.origin = new Point(0, 0);
    }

    public BarDataView(Point origin, double barWidth, double spacing, double maxHeight) {
        this.elements = new ArrayList<>();
        this.origin = origin;
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
        if (this.data.size() == 0) {
            return;
        }

        double maxValue = this.data.values().stream().mapToDouble(Value::value).sum(); // somme pour une seule barre
        double baseX = origin.getX();
        double baseY = origin.getY();
        double accHeight = 0;

        double left = baseX - (barWidth / 2);
        double right = baseX + (barWidth / 2);

        for (int i = 0; i < this.data.size(); i++) {
            double val = this.data.getValue(i);
            double height = (val / maxValue) * maxHeight;
            double bottom = baseY + accHeight;
            double top = bottom + height;

            Path barSegment = new Path();
            barSegment.draw()
                    .move(left, bottom, false)
                    .line(right, bottom, false)
                    .line(right, top, false)
                    .line(left, top, false)
                    .line(left, bottom, false)
                    .close();

            Color fill = this.data.get(i).color().orElse(this.data.mainColor());
            barSegment.getStyle()
                    .fillColor(fill)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(1);

            this.elements.add(barSegment);

            Label defaultLabel = new Label(String.format("%.2f", val));
            Label label = this.data.get(i).label().orElse(defaultLabel);

            this.elements.add(label.createText(new Point(left + barWidth / 2.0, top - 5)));

            accHeight += height;
        }
    }

    @Override
    public IShape copy() {
        return new BarDataView(new Point(this.origin.getX(), this.origin.getY()), this.barWidth, this.spacing,
                this.maxHeight);
    }

    @Override
    public Point getCenter() {
        // pas vraiment le centre mais l'origine du graphique
        return this.origin;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indent)));
        sb.append("BarDataView");
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