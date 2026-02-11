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
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;

@SVGTag("g")
public class LineDataView implements IDataView {
    @SVGField
    private List<IShape> elements;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    private DataSet data;

    public LineDataView() {
        this.elements = new ArrayList<>();
    }

    public LineDataView(List<IShape> elements) {
        this.elements = elements;
    }

    @Override
    public double getWidth() {
        double plusPetit = elements.get(0).getWidth();
        double plusGrand = elements.get(0).getWidth();
        for (IShape element : elements) {
            if (element.getWidth() < plusPetit) {
                plusPetit = element.getWidth();
            }
            if (element.getWidth() > plusGrand) {
                plusGrand = element.getWidth();
            }
        }
        return plusGrand - plusPetit;
    }

    @Override
    public double getHeight() {
        double plusPetit = elements.get(0).getHeight();
        double plusGrand = elements.get(0).getHeight();
        for (IShape element : elements) {
            if (element.getHeight() < plusPetit) {
                plusPetit = element.getHeight();
            }
            if (element.getHeight() > plusGrand) {
                plusGrand = element.getHeight();
            }
        }
        return plusGrand - plusPetit;
    }

    @Override
    public Point getCenter() {
        double plusPetitX = elements.get(0).getCenter().getX();
        double plusGrandX = elements.get(0).getCenter().getX();
        double plusPetitY = elements.get(0).getCenter().getY();
        double plusGrandY = elements.get(0).getCenter().getY();
        for (IShape element : elements) {
            if (element.getCenter().getX() < plusPetitX) {
                plusPetitX = element.getCenter().getX();
            }
            if (element.getCenter().getX() > plusGrandX) {
                plusGrandX = element.getCenter().getX();
            }
            if (element.getCenter().getY() < plusPetitY) {
                plusPetitY = element.getCenter().getY();
            }
            if (element.getCenter().getY() > plusGrandY) {
                plusGrandY = element.getCenter().getY();
            }
        }
        return new Point((plusPetitX + plusGrandX) / 2, (plusPetitY + plusGrandY) / 2);
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
        sb.append("LineDataView");
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
        return new LineDataView(elements);
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
        for (int i = 0; i < this.data.size(); i++) {
            // TODO
        }
    }

}
