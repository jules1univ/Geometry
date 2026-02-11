package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;

public class ColumnDataViewTest extends AbstractDataViewTest<ColumnDataView> {

    @Override
    public ColumnDataView create() {
        return new ColumnDataView(new Point(500, 500), 100, 50, 200);
    }

}
