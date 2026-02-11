package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;

public class BarsDataViewTest extends AbstractDataViewTest<BarsDataView> {

    @Override
    public BarsDataView create() {
        return new BarsDataView(new Point(500, 500), 100, 50, 200);
    }

}
