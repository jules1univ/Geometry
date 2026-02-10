package fr.univrennes.istic.l2gen.visustats;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Path;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("g")
public class PieView implements IDataView {
    // TODO: finir implementation de PieView

    @SVGField
    private List<Path> slices;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    private Point center;
    private double radius;
    private double[] data;

    public PieView() {
        this.slices = new ArrayList<>();
        this.center = new Point(0, 0);
    }

    public PieView(Point center, double[] data) {
        this.slices = new ArrayList<>();
        this.center = center;
    }

    @Override
    public double getWidth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWidth'");
    }

    @Override
    public double getHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
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
        for (IShape slice : this.slices) {
            slice.move(dx, dy);
        }
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
        return new PieView(new Point(this.center.getX(), this.center.getY()), this.data);
    }

    @Override
    public void setTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTitle'");
    }

    @Override
    public void setData(double[] data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setData'");
    }

    private void updateSlices() {
        double total = 0;
        for (double value : data) {
            total += value;
        }

        for (int i = 0; i < data.length; i++) {
            double startAngle = 0;
            for (int j = 0; j < i; j++) {
                startAngle += data[j] / total * 360;
            }
            double endAngle = startAngle + data[i] / total * 360;
            Path slice = createSlice(startAngle, endAngle);
            slice.getStyle().fillColor(Color.random());
            this.slices.add(slice);
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
