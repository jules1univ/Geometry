package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;

public class ColumnDataViewTest extends AbstractDataViewTest<ColumnsDataView> {

    @Override
    public ColumnsDataView create() {
        return new ColumnsDataView(new Point(500, 500), 100, 50, 200);
    }

}
