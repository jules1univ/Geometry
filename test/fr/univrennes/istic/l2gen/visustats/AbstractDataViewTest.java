package fr.univrennes.istic.l2gen.visustats;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;
import fr.univrennes.istic.l2gen.visustats.view.IDataView;

@Ignore("Abstract Data View Test")
public abstract class AbstractDataViewTest<DataView extends IDataView> {

    public abstract DataView create();

    private final DataSet generateData() {
        return new DataSet(List.of(
                new Value(30, new Label("Category A"), Color.RED),
                new Value(20, new Label("Category B"), Color.GREEN),
                new Value(50, new Label("Category C"), Color.BLUE)),
                new Label("X-Axis"),
                new Label("Y-Axis"),
                Color.BLUE);
    }

    @Test
    public final void testSVG() {
        DataView dataView = create();
        assert dataView != null;

        dataView.setData(List.of(generateData()));

        File output = new File(String.format("output/test_%s.svg", dataView.getClass().getSimpleName().toLowerCase()));
        if (output.exists()) {
            output.delete();
        }
        output.getParentFile().mkdirs();

        IShape background = new Rectangle(0, 0, 1000, 1000);
        background.getStyle().fillColor(Color.WHITE);

        assert SVGExport.export(List.of(background, dataView), output.getAbsolutePath(), 1000, 1000);
    }
}
