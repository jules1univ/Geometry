package fr.univrennes.istic.l2gen.visustats.view;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.visustats.view.PieDataView;

public final class PieViewTest extends AbstractDataViewTest<PieDataView> {

    @Override
    public PieDataView create() {
        return new PieDataView(new Point(500, 500), 400);
    }

}
