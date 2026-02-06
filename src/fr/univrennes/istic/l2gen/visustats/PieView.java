package fr.univrennes.istic.l2gen.visustats;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("g")
public class PieView implements IDataView {
    // TODO: finir implementation de PieView

    @SVGField
    private List<IShape> slices;

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

    public PieView(Point cetner, double[] data) {
        this.slices = new ArrayList<>();
        this.center = cetner;
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
        for (IShape slice : this.slices) {
            slice.resize(px, py);
        }
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

}
