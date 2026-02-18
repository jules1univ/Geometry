file://<WORKSPACE>/src/fr/univrennes/istic/l2gen/visustats/view/datagroup/ColumnsDataGroupView.java
### java.lang.OutOfMemoryError: Java heap space

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 2144
uri: file://<WORKSPACE>/src/fr/univrennes/istic/l2gen/visustats/view/datagroup/ColumnsDataGroupView.java
text:
```scala
package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Text;
import fr.univrennes.istic.l2gen.svg.color.Color;
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

    public ColumnsDataGroupView(DataGroup data, Point center, double spacing, double barWidth, double maxHeight) {
        super(data, spacing);
        this.maxHeight = maxHeight;
        this.center = center;
        this.barWidth = barWidth;
        this.update();

    }

    @Override
    protected void update() {
        List<DataSet> dataSets = data.datasets();

        this.elements.clear();

        double barSpacing = spacing * 0.8;
        // largeur dataview
        double elWidth = (barWidth + barSpacing) * data.maxSize();
        int n = data.size();
        double totalWidth = elWidth * n + spacing * Math.max(0, n - 1);

        double offsetX = -totalWidth / 2.0;

        for (int i = 0; i < dataSets.size(); i++) {
            DataSet ds = dataSets.get(i);

            double x = center.getX() + offsetX + elWidth / 2.0;
            ColumnsDataSetView colView = new ColumnsDataSetView(new Point(
                    x,
                    center.getY()), barWidth, barSpacing, maxHeight);
            colView.setData(ds);
            this.elements.add(colView);

            offsetX += elWidth + spacing;
        }
        
        this.elements.add(this.data.)
    @@
    }

}

```



#### Error stacktrace:

```
dotty.tools.dotc.util.WeakHashSet.<init>(WeakHashSet.scala:54)
	dotty.tools.dotc.core.Uniques.<init>(Uniques.scala:11)
	dotty.tools.dotc.core.Contexts$ContextState.<init>(Contexts.scala:962)
	dotty.tools.dotc.core.Contexts$ContextBase.<init>(Contexts.scala:860)
	dotty.tools.dotc.Driver.initCtx(Driver.scala:60)
	dotty.tools.dotc.interactive.InteractiveDriver.<init>(InteractiveDriver.scala:34)
	dotty.tools.pc.CachingDriver.<init>(CachingDriver.scala:30)
	dotty.tools.pc.ScalaPresentationCompiler.$init$$$anonfun$1(ScalaPresentationCompiler.scala:85)
```
#### Short summary: 

java.lang.OutOfMemoryError: Java heap space