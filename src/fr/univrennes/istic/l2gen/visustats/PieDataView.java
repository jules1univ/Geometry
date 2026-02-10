package fr.univrennes.istic.l2gen.visustats;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Path;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Text;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("g")
public class PieDataView implements IDataView {
    // TODO: finir implementation de PieView

    @SVGField
    private List<IShape> elements;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    private Point center;
    private double radius;
    private double[] data;

    public PieDataView() {
        this.elements = new ArrayList<>();
        this.center = new Point(0, 0);
    }

    public PieDataView(Point center, double radius, double[] data) {
        this.elements = new ArrayList<>();
        this.center = center;
        this.radius = radius;

        this.data = data;
        this.update();
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
        return new PieDataView(new Point(this.center.getX(), this.center.getY()), this.radius, this.data);
    }

    @Override
    public void setTitle(String title) {
        // TODO: implémenter setTitle pour PieView
    }

    @Override
    public void setData(double[] data) {
        this.data = data;
        this.update();
    }

    private void update() {
        double total = 0;
        for (double value : data)
            total += value;

        for (int i = 0; i < data.length; i++) {
            double startAngle = 0;
            for (int j = 0; j < i; j++) {
                startAngle += data[j] / total * 360;
            }
            double endAngle = startAngle + data[i] / total * 360;
            double midAngle = (startAngle + endAngle) / 2;

            // Slice
            Path slice = createSlice(startAngle, endAngle);
            slice.getStyle().fillColor(Color.random()).strokeColor(Color.BLACK).strokeWidth(2);
            this.elements.add(slice);

            // Arrow
            double arrowStartX = center.getX() + radius * Math.cos(Math.toRadians(midAngle));
            double arrowStartY = center.getY() + radius * Math.sin(Math.toRadians(midAngle));
            double arrowEndX = center.getX() + radius * 1.3 * Math.cos(Math.toRadians(midAngle));
            double arrowEndY = center.getY() + radius * 1.3 * Math.sin(Math.toRadians(midAngle));

            Path arrow = new Path();
            arrow.draw()
                    .move(arrowStartX, arrowStartY, false)
                    .line(arrowEndX, arrowEndY, false);
            arrow.getStyle().strokeColor(Color.BLACK).strokeWidth(1);
            this.elements.add(arrow);

            Text label = new Text(arrowEndX + 5, arrowEndY, String.valueOf(data[i]));
            label.getStyle()
                    .fillColor(Color.BLACK)
                    .fontSize(76)
                    .fontFamily("Arial");
            this.elements.add(label);
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
