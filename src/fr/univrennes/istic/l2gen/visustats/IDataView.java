package fr.univrennes.istic.l2gen.visustats;

import fr.univrennes.istic.l2gen.geometry.IShape;

public interface IDataView extends IShape {

    public void setTitle(String title);

    public void setData(double[] data);

    public void setXLabel(String label);

    public void setYLabel(String label);

}
