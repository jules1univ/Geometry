package fr.univrennes.istic.l2gen.visustats.view.group;

import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;

public interface IDataGroupView {

    public void setTitle(Label title);

    public void setData(DataGroup group);

    public void addData(DataSet dataset, Label colorLabel);
}
