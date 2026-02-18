file://<WORKSPACE>/src/fr/univrennes/istic/l2gen/visustats/data/Label.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/fr/univrennes/istic/l2gen/visustats/data/Label.java
text:
```scala
package fr.univrennes.istic.l2gen.visustats.data;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Text;
import fr.univrennes.istic.l2gen.svg.color.Color;

public record Label(String name, Color color) {
    public Label(String name) {
        this(name, Color.BLACK);
    }

    public Text createText(Point position) {
        Text text = new Text(position.getX(), position.getY(), name);
        text.getStyle()
                .fontFamily("Arial")
                .fontSize(16)
                .textAnchor("middle");
        return text;
    }

    public Text createTitle(Point position) {
        Text title = new Text(position.getX(), position.getY(), name);
        title.getStyle()
                .fontFamily("Arial")
                .fontSize(26)
                .textAnchor("middle");
        return title;
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