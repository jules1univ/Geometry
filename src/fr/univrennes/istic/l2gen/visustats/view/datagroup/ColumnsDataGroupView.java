package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.field.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.view.dataset.ColumnsDataSetView;
import fr.univrennes.istic.l2gen.visustats.view.dataset.IDataSetView;

@SVGTag("g")
public class ColumnsDataGroupView extends AbstractDataGroupView {
    private double maxHeight;
    private Point center;
    private double barWidth;

    @SVGField
    private List<IDataSetView> elements = new ArrayList<>();

    public ColumnsDataGroupView(DataGroup data, Point center, double spacing, double barWidth, double maxHeight) {
        super(data, spacing);
        this.maxHeight = maxHeight;
        this.center = center;
        this.barWidth = barWidth;
        this.update();

    }

    @Override
    protected void update() {
        Label title = data.title();
        List<Label> legends = data.legends();
        List<DataSet> dataSets = data.datasets();

        int n = dataSets.size();

        // largeur totale occupée par les colonnes (n * width + (n-1) * spacing)

        double startX = center.getX();
        double baseY = center.getY(); // ligne de base (les colonnes "montent" vers le haut)
        double width = 0;
        this.elements.clear();
        for (int i = 0; i < n; i++) {

            // position X du centre de la colonne i
            double left = startX + i * (width + spacing);
            double originX = left + width / 2.0;
            Point origin = new Point(originX, baseY);

            // crée la vue de colonne, initialise avec position/tailles
            ColumnsDataSetView colView = new ColumnsDataSetView(origin, barWidth, spacing, maxHeight);
            colView.setData(this.data.get(i));
            width = colView.getWidth();
            this.elements.add(colView);
        }

    }

}
