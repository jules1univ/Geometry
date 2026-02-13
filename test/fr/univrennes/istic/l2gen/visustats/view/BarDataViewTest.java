package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.visustats.view.set.BarDataSetView;

public class BarDataViewTest extends AbstractDataSetViewTest<BarDataSetView> {

    @Override
    public BarDataSetView create() {
        return new BarDataSetView(new Point(500, 500), 100, 200);
    }

}
