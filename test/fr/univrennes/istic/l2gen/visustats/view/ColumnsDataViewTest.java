package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.visustats.view.set.ColumnsDataSetView;

public class ColumnsDataViewTest extends AbstractDataSetViewTest<ColumnsDataSetView> {

    @Override
    public ColumnsDataSetView create() {
        return new ColumnsDataSetView(new Point(500, 500), 100, 50, 200);
    }

}
