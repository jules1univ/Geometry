package fr.univrennes.istic.l2gen.visustats.view;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.base.Line;
import fr.univrennes.istic.l2gen.geometry.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Value;
import fr.univrennes.istic.l2gen.visustats.view.set.IDataSetView;

@Ignore("Abstract DataSet View Test")
public abstract class AbstractDataSetViewTest<DataSetView extends IDataSetView> {

    public abstract DataSetView create();

    private final DataSet createDataSet(int size, double minValue, double maxValue) {

        DataSet dataset = new DataSet();
        for (int i = 0; i < size; i++) {
            double value = Math.random() * (maxValue - minValue) + minValue;
            dataset.values().add(new Value(value, Color.random()));
        }
        return dataset;
    }

    @Test
    public final void testSVG() {
        DataSetView dataView = create();
        assert dataView != null;

        dataView.setData(createDataSet(5, 10, 100));

        File output = new File(String.format("output/test_%s.svg", dataView.getClass().getSimpleName().toLowerCase()));
        if (output.exists()) {
            output.delete();
        }
        output.getParentFile().mkdirs();

        IShape background = new Rectangle(0, 0, 1000, 1000);
        background.getStyle().fillColor(Color.WHITE);

        IShape crossLine1 = new Line(500, 0, 500, 1000);
        crossLine1.getStyle()
                .strokeWidth(2)
                .strokeColor(Color.BLACK);

        IShape crossLine2 = new Line(0, 500, 1000, 500);
        crossLine2.getStyle()
                .strokeWidth(2)
                .strokeColor(Color.BLACK);

        IShape box = new Rectangle(
                dataView.getCenter().getX() - dataView.getWidth() / 2,
                dataView.getCenter().getY() - dataView.getHeight() / 2,
                dataView.getWidth(),
                dataView.getHeight());
        box.getStyle()
                .fillColor(Color.TRANSPARENT)
                .strokeWidth(2)
                .strokeColor(Color.RED)
                .strokeDashArray(5, 5, 5, 5);

        assert SVGExport.export(List.of(background, dataView, crossLine1, crossLine2, box), output.getAbsolutePath(),
                1000,
                1000);
    }
}
