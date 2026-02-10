package fr.univrennes.istic.l2gen.visustats.view;

import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;

public interface IDataView extends IShape {

    public void setData(List<DataSet> datasets);

}
