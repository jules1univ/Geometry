file://<WORKSPACE>/src/fr/univrennes/istic/l2gen/visustats/view/datagroup/ColumnsDataGroupView.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 949
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
    private double barWidth@@;
    private Label title;

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
        Text titleText = new Text(center.getX(), center.getY() - maxHeight / 1.5, this.title.name());
        titleText.getStyle()
                .fillColor(Color.BLACK)
                .textAnchor("middle")
                .fontSize(18)
                .fontFamily("Arial");
        this.elements.add(titleText);

    }

}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:935)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:164)
	dotty.tools.pc.CachingDriver.run(CachingDriver.scala:45)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:40)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:389)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator