package fr.univrennes.istic.l2gen.svg.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.univrennes.istic.l2gen.svg.animations.AnimationCount;
import fr.univrennes.istic.l2gen.svg.animations.AnimationDuration;
import fr.univrennes.istic.l2gen.svg.animations.AnimationTransformType;
import fr.univrennes.istic.l2gen.svg.animations.SVGAnimate;
import fr.univrennes.istic.l2gen.svg.animations.SVGAnimateTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.field.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;
import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public class ImportExportTest {

    @SVGPoint
    private static class TestPoint implements ISVGShape {

        @SVGPointX
        private double x;

        @SVGPointY
        private double y;

        @SuppressWarnings("unused")
        public TestPoint() {
        }

        public TestPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    @SVGTag("super-rect")
    private static class TestSuperRect implements ISVGShape {

        @SVGField
        protected String superField = "superValue";

        public TestSuperRect() {
        }

    }

    @SVGTag("rect")
    private static class TestRect extends TestSuperRect {

        @SVGField
        private SVGAnimate childAnimation;

        @SVGField
        private SVGAnimateTransform childAnimationTransform;

        @SVGField({ "x", "y" })
        private TestPoint position = new TestPoint(0, 0);

        @SVGField({ "width", "height" })
        private TestPoint size = new TestPoint(100, 100);

        @SVGField
        private List<TestPoint> randomValues = new ArrayList<>();

        @SVGField
        private List<TestSuperRect> miniRects = new ArrayList<>();

        public TestRect() {
            this.childAnimation = new SVGAnimate();
            this.childAnimation
                    .type(AnimationTransformType.ROTATE)
                    .begin("0")
                    .dur(AnimationDuration.s(1))
                    .repeatCount(AnimationCount.INDEFINITE)
                    .repeatDur(AnimationDuration.s(1));
            this.randomValues.add(new TestPoint(10, 10));
            this.randomValues.add(new TestPoint(20, 20));

            this.childAnimationTransform = new SVGAnimateTransform();
            this.childAnimationTransform
                    .type(AnimationTransformType.TRANSLATE)
                    .begin("0")
                    .dur(AnimationDuration.s(1))
                    .repeatCount(AnimationCount.INDEFINITE)
                    .repeatDur(AnimationDuration.s(1));

            this.miniRects.add(new TestSuperRect());
            this.miniRects.add(new TestSuperRect());
            this.miniRects.add(new TestSuperRect());
            this.miniRects.add(new TestSuperRect());
        }

    }

    @Test
    public void testExportFile() {
        TestRect rect = new TestRect();
        String filepath = "test_output.svg";
        assert SVGExport.export(rect, filepath, 0, 0);

        File file = new File(filepath);
        assert file.exists();

        file.delete();
    }

    @Test
    public void testImportFile() {
        SVGImport.register(TestRect.class);
        SVGImport.register(TestPoint.class);

        TestRect rect = new TestRect();
        String filepath = "test_output.svg";
        assert SVGExport.export(rect, filepath, 0, 0);

        List<ISVGShape> importShapes = SVGImport.load(filepath);
        assert importShapes.size() != 0;

        ISVGShape importShape = importShapes.get(0);
        assert importShape instanceof TestRect;

        File file = new File(filepath);
        assert file.exists();

        file.delete();
    }

    @Test
    public void testExportConvert() {
        TestRect rect = new TestRect();
        XMLTag svgRect = SVGExport.convert(rect);

        assert svgRect.getTagName().equals("rect");

        assert svgRect.hasAttribute("x");
        assert svgRect.getAttribute("x").getValue().equals("0.0");

        assert svgRect.hasAttribute("randomValues");
        assert svgRect.getAttribute("randomValues").getValue().equals("10.0,10.0 20.0,20.0");

        assert svgRect.hasAttribute("superField");
        assert svgRect.getAttribute("superField").getValue().equals("superValue");

        assert svgRect.getChildrenCount() == 6;

        XMLTag svgAnimate = svgRect.getFirstChild();
        assert svgAnimate != null;

        assert svgAnimate.getTagName().equals("animate");

        assert svgAnimate.hasAttribute("type");
        assert svgAnimate.getAttribute("type").getValue().equals("rotate");

        assert svgAnimate.hasAttribute("begin");
        assert svgAnimate.getAttribute("begin").getValue().equals("0");

        XMLTag svgAnimateTransform = svgRect.getChildren(1);
        assert svgAnimateTransform != null;

        assert svgAnimateTransform.getTagName().equals("animateTransform");
        assert svgAnimateTransform.hasAttribute("type");
        assert svgAnimateTransform.getAttribute("type").getValue().equals("translate");
    }

    @Test
    public void testImportConvert() {
        SVGImport.register(TestRect.class);
        SVGImport.register(TestPoint.class);

        TestRect rect = new TestRect();
        XMLTag svgRect = SVGExport.convert(rect);

        ISVGShape importShape = SVGImport.convert(svgRect);

        assert importShape != null;
        assert importShape instanceof TestRect;

        TestRect importedRect = (TestRect) importShape;
        assert importedRect.position != null;
        assert importedRect.position.x == 0.0;
        assert importedRect.position.y == 0.0;

        assert importedRect.randomValues != null;
        assert importedRect.randomValues.size() == 2;
        assert importedRect.randomValues.get(0).x == 10.0;
        assert importedRect.randomValues.get(0).y == 10.0;

        assert importedRect.childAnimation != null;
        assert importedRect.childAnimation.type() == AnimationTransformType.ROTATE;
        assert importedRect.childAnimation.begin().equals("0");
        assert importedRect.childAnimation.to() == null;

        assert importedRect.superField.equals("superValue");
    }

    @Test
    public void testExportConvertWithNull() {
        TestRect rect = new TestRect();
        rect.position = null;
        XMLTag svgRect = SVGExport.convert(rect);

        assert svgRect.getTagName().equals("rect");

        assert !svgRect.hasAttribute("x");
        assert !svgRect.hasAttribute("y");
    }

    @Test
    public void testExportConvertWithEmptyList() {
        TestRect rect = new TestRect();
        rect.randomValues.clear();
        XMLTag svgRect = SVGExport.convert(rect);

        assert svgRect.getTagName().equals("rect");

        assert !svgRect.hasAttribute("randomValues");
    }

    @Test
    public void testImportMissingRegister() {
        TestRect rect = new TestRect();
        XMLTag svgRect = SVGExport.convert(rect);

        ISVGShape importShape = SVGImport.convert(svgRect);

        assert importShape == null;
    }

    @Test
    public void testImportMissingAttribute() {
        SVGImport.register(TestRect.class);
        SVGImport.register(TestPoint.class);

        TestRect rect = new TestRect();
        XMLTag svgRect = SVGExport.convert(rect);

        svgRect.removeAttribute("x");

        ISVGShape importShape = SVGImport.convert(svgRect);

        assert importShape != null;
        assert importShape instanceof TestRect;

        TestRect importedRect = (TestRect) importShape;
        assert importedRect.position != null;
        assert importedRect.position.x == 0.0;
        assert importedRect.position.y == 0.0;
    }

}
