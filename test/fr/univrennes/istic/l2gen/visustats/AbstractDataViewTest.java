package fr.univrennes.istic.l2gen.visustats;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;

@Ignore("Abstract Data View Test")
public abstract class AbstractDataViewTest<DataView extends IDataView> {

    public abstract DataView create();

    @Test
    public final void testSVG() {
        DataView dataView = create();
        assert dataView != null;

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
