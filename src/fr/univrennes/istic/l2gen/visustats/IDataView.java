package fr.univrennes.istic.l2gen.visustats;

import fr.univrennes.istic.l2gen.geometry.IShape;

// TODO: est ce que ce ne serait pas mieux d'être une abstract class ??
public interface IDataView extends IShape {

    public void setTitle(String title);

    // TODO: voir comment on va faire pour structurer les données
    public void setData(double[] data);

    // TODO: voir comment faire pour ajouter des labels aux données

}
