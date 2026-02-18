package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import org.junit.Test;

import fr.univrennes.istic.l2gen.io.svg.SVGExportTestUtil;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;

public class PieDataGroupViewTest {

    /**
     * Crée un DataSet avec des valeurs aléatoires pour les tests.
     * 
     * @param size     nombre de valeurs
     * @param minValue valeur minimale
     * @param maxValue valeur maximale
     * @return un DataSet avec les valeurs générées
     */
    private DataSet createDataSet(int size, double minValue, double maxValue) {
        DataSet dataset = new DataSet();
        for (int i = 0; i < size; i++) {
            double value = Math.random() * (maxValue - minValue) + minValue;
            dataset.values().add(new Value(value, Color.random()));
        }
        return dataset;
    }

    /**
     * Crée un DataGroup pour les tests.
     * 
     * @param size     nombre de datasets dans le groupe
     * @param minValue valeur minimale des données
     * @param maxValue valeur maximale des données
     * @return un DataGroup avec plusieurs datasets
     */
    private DataGroup createDataGroup(int size, double minValue, double maxValue) {
        DataGroup group = new DataGroup(new Label("Pie Chart Group"));
        for (int i = 0; i < size; i++) {
            group.add(createDataSet(5, minValue, maxValue));
        }
        return group;
    }

    /**
     * Test d'export SVG pour PieDataGroupView avec plusieurs camemberts côte à
     * côte.
     */
    @Test
    public void testSVG() {
        // Créer un DataGroup avec 3 datasets
        DataGroup dataGroup = createDataGroup(3, 10, 150);

        // Créer la vue PieDataGroupView avec:
        // - spacing: 20 pixels entre les camemberts
        // - pieRadius: 80 pixels pour chaque camembert
        PieDataGroupView pieView = new PieDataGroupView(dataGroup, 20, 80);

        // Exporter en SVG pour validation
        SVGExportTestUtil.export(pieView);
    }
}
