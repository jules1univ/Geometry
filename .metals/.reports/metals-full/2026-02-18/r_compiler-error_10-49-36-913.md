file://<WORKSPACE>/test/fr/univrennes/istic/l2gen/visustats/view/datagroup/ColumnsDataGroupViewTest.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/test/fr/univrennes/istic/l2gen/visustats/view/datagroup/ColumnsDataGroupViewTest.java
text:
```scala
package fr.univrennes.istic.l2gen.visustats.view.datagroup;

import org.junit.Ignore;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.io.svg.SVGExportTestUtil;
import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.visustats.data.DataGroup;
import fr.univrennes.istic.l2gen.visustats.data.DataSet;
import fr.univrennes.istic.l2gen.visustats.data.Label;
import fr.univrennes.istic.l2gen.visustats.data.Value;
import fr.univrennes.istic.l2gen.visustats.view.dataset.AbstractDataSetViewTest;

public class ColumnsDataGroupViewTest {

    private DataGroup createDataGroup(int size, double minValue, double maxValue) {
        DataGroup group = new DataGroup(new Label("Test title"));
        for (int i = 0; i < size; i++) {
            group.add(
                    AbstractDataSetViewTest.createDataSet(size, minValue, maxValue));
        }
        return group;
    }

    @Test
    public void testSVG() {
        ColumnsDataGroupView colview = new ColumnsDataGroupView(createDataGroup(3, 10, 150), new Point(500, 500), 15,
                10,
                100, new Label("GRAPHIQ);
        SVGExportTestUtil.export(colview);
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
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:31)
	dotty.tools.pc.SimpleCollector.<init>(PcCollector.scala:351)
	dotty.tools.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:88)
	dotty.tools.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:111)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator