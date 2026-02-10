package fr.univrennes.istic.l2gen.visustats;

import fr.univrennes.istic.l2gen.geometry.Point;

public final class PieViewTest extends AbstractDataViewTest<PieDataView> {

    @Override
    public PieDataView create() {
        return new PieDataView(new Point(500, 500), 400, new double[] { 30, 20, 50 });
    }

}
