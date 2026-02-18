package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.view.dataset.BarDataSetView;

@SVGTag("g")
public class BarDataGroupView extends AbstractDataGroupView {

    protected double maxHeight;
    protected double spacing;
    protected Point centre;
    private double barWidth;

    public BarDataGroupView(
            DataGroup data,
            double spacing,
            double maxHeight,
            double barWidth, Point centre) {
        super(data, spacing);
        this.maxHeight = maxHeight;
        this.spacing = spacing;
        this.centre = centre;
        this.barWidth = barWidth;
    }

    @Override
    protected void update() {

        double ratio = maxHeight / data.max();

        // poitn de centre du graph

        double axisX = centre.getX();
        double axisY = centre.getY() + maxHeight;

        // 0 <=> maxHeight;

        // axis X=> 0 <=> (spacing + width(dataset)) * nb_dataset
        double width = 0.f;
        for (int i = 0; i < data.size(); i++) {
            BarDataSetView barview = new BarDataSetView(new Point(
                    centre.getX() + (spacing + width) * i,
                    centre.getY()), barWidth, maxHeight);
            barview.setData(data.get(i));

            width = barview.getWidth();

            this.elements.add(barview);
        }

        /*
         * dataset.draw(
         * x + ( spacing + width ) * i,
         * y
         * )
         */
    }

}
