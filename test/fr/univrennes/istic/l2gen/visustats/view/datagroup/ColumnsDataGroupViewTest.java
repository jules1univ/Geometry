package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import org.junit.Ignore;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.io.svg.SVGExportTestUtil;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;
import fr.univrennes.istic.l2gen.visustats.view.dataset.AbstractDataSetViewTest;

public class ColumnsDataGroupViewTest {

    private DataGroup createDataGroup(int size, double minValue, double maxValue) {
        DataGroup group = new DataGroup(new Label("Test title"));
        for (int i = 0; i < size; i++) {
            group.add(
                    AbstractDataSetViewTest.createDataSet(size, minValue, maxValue));
        }
        return group;
    }

    @Test
    public void testSVG() {
        ColumnsDataGroupView colview = new ColumnsDataGroupView(createDataGroup(3, 10, 150), new Point(500, 500), 15,
                10,
                100);
        SVGExportTestUtil.export(colview);
    }
}
