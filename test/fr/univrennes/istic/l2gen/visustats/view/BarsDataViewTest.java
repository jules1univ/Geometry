package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;

public class BarsDataViewTest extends AbstractDataViewTest<BarDataView> {

    @Override
    public BarDataView create() {
        return new BarDataView(new Point(500, 500), 100, 50, 200);
    }

}
