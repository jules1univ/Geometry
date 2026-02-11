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
public class BarDataView implements IDataView {

    @SVGField
    private List<IShape> elements;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    private DataSet data;

    public BarDataView() {
        this.elements = new ArrayList<>();
    }

    public BarDataView(List<IShape> elements) {
        this.elements = elements;
    }

    @Override
    public double getWidth() {
        return 0.0;
        // TODO
    }

    @Override
    public double getHeight() {
        return 0.0;
        // TODO
    }

    @Override
    public Point getCenter() {
        return null;
        // TODO
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
        return null;
        // TODO
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
        return null; // TODO
    }

    @Override
    public void setData(List<DataSet> datasets) { // TODO
    }

    private void update() {
        for (int i = 0; i < this.data.size(); i++) {
            // TODO
        }
    }

}