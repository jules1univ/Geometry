package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.io.svg.SVGExportTestUtil;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.view.dataset.AbstractDataSetViewTest;

public class PieDataGroupViewTest {

    private DataGroup createDataGroup(int size, double minValue, double maxValue) {
        DataGroup group = new DataGroup(new Label("Pie Chart Group"));
        for (int i = 0; i < size; i++) {
            group.add(AbstractDataSetViewTest.createDataSet(size, minValue, maxValue));
        }
        return group;
    }

    @Test
    public void testSVG() {
        PieDataGroupView pieView = new PieDataGroupView(createDataGroup(3, 10, 150), 20, 80, new Point(500, 300));
        SVGExportTestUtil.export(pieView);
    }
}
