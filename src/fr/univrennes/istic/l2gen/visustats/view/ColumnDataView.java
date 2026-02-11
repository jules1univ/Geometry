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
    private double radius;
    private DataSet data;

    public ColumnDataView() {
        this.elements = new ArrayList<>();
        this.center = new Point(0, 0);
    }

    public ColumnDataView(Point center, double radius) {
        this.elements = new ArrayList<>();
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override
    public double getHeight() {
        return this.radius * 2;
    }

    @Override
    public Point getCenter() {
        return this.center;
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
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indent));
        sb.append("PieView");
        return sb.toString();
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

    @Override
    public IShape copy() {
        return new PieDataView(new Point(this.center.getX(), this.center.getY()), this.radius);
    }

    @Override
    public void setData(List<DataSet> datasets) {
        if (datasets.isEmpty()) {
            return;
        }

        this.data = datasets.get(0);
        this.update();
    }

    private void update() {
        double total = this.data.values().stream().mapToDouble(Value::value).sum();

        for (int i = 0; i < this.data.size(); i++) {
            double startAngle = 0;
            for (int j = 0; j < i; j++) {
                startAngle += data.getValue(j) / total * 360;
            }
            double endAngle = startAngle + data.getValue(i) / total * 360;

            Path slice = createSlice(startAngle, endAngle);
            slice.getStyle()
                    .fillColor(data.get(i).color().orElse(data.mainColor()))
                    .strokeColor(Color.BLACK)
                    .strokeWidth(2);

            this.elements.add(slice);

            double midAngle = Math.toRadians((startAngle + endAngle) / 2);
            double length = radius * 1.2;

            double arrowStartX = center.getX() + radius * Math.cos(midAngle);
            double arrowStartY = center.getY() + radius * Math.sin(midAngle);
            double arrowEndX = center.getX() + length * Math.cos(midAngle);
            double arrowEndY = center.getY() + length * Math.sin(midAngle);

            Path arrow = new Path();
            arrow.draw()
                    .move(arrowStartX, arrowStartY, false)
                    .line(arrowEndX, arrowEndY, false);

            arrow.getStyle()
                    .strokeColor(Color.BLACK)
                    .strokeWidth(2);
            this.elements.add(arrow);

            Label defaultLabel = new Label(String.format("%.2f%%", data.getValue(i) / total * 100));
            Label label = this.data.get(i).label().orElse(defaultLabel);

            Text text = new Text(arrowEndX + 5, arrowEndY + 20, label.name());
            text.getStyle()
                    .fillColor(label.color())
                    .textAnchor("middle")
                    .fontSize(24)
                    .fontFamily("Arial");
            this.elements.add(text);
        }
    }

    public Path createSlice(double startAngle, double endAngle) {
        Path slice = new Path();
        slice
                .draw()
                .move(center.getX(), center.getY(), false)
                .line(center.getX() + radius * Math.cos(Math.toRadians(startAngle)),
                        center.getY() + radius * Math.sin(Math.toRadians(startAngle)),
                        false)
                .arc(radius, radius, 0, endAngle - startAngle > 180, true,
                        center.getX() + radius * Math.cos(Math.toRadians(endAngle)),
                        center.getY() + radius * Math.sin(Math.toRadians(endAngle)), false)
                .close();
        return slice;
    }
}
