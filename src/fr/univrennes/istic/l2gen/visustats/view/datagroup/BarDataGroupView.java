package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;

public class BarDataGroupView extends AbstractDataGroupView {

    protected double maxHeight;
    protected double spacing;
    protected Point centre;

    public BarDataGroupView(DataGroup data, double spacing, double maxHeight, Point centre) {
        super(data, spacing);
        this.maxHeight = maxHeight;
        this.spacing = spacing;
        this.centre = centre;
    }

    @Override
    protected void update() {

        double ratio = maxHeight / data.max();

        // poitn de centre du graph

        double axisX = centre.getX();
        double axisY = centre.getY() + maxHeight;

        // 0 <=> maxHeight;

        // axis X=> 0 <=> (spacing + width(dataset)) * nb_dataset

        for (int i = 0; i < data.size(); i++) {

        }

        /*
         * dataset.draw(
         * x + ( spacing + width ) * i,
         * y
         * )
         */

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
