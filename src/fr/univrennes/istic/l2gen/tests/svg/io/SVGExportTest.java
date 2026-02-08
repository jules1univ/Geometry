package fr.univrennes.istic.l2gen.tests.svg.io;

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
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public class SVGExportTest {

    @SVGPoint
    private static class TestPoint {

        @SVGPointX
        private double x;

        @SVGPointY
        private double y;

        public TestPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    @SVGTag("super-rect")
    private static class TestSuperRect implements ISVGShape {

        @SVGField
        private String superField = "superValue";

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
        }

    }

    @Test
    public void testSVGConvert() {
        TestRect rect = new TestRect();
        XMLTag svgRect = SVGExport.convert(rect);

        assert svgRect.getTagName().equals("rect");

        assert svgRect.hasAttribute("x");
        assert svgRect.getAttribute("x").value().equals("0.0");

        assert svgRect.hasAttribute("randomValues");
        assert svgRect.getAttribute("randomValues").value().equals("10.0,10.0 20.0,20.0");

        assert svgRect.hasAttribute("superField");
        assert svgRect.getAttribute("superField").value().equals("superValue");

        assert svgRect.getChildrenCount() == 2;

        XMLTag svgAnimate = svgRect.getFirstChild();
        assert svgAnimate != null;

        assert svgAnimate.getTagName().equals("animate");

        assert svgAnimate.hasAttribute("type");
        assert svgAnimate.getAttribute("type").value().equals("rotate");

        assert svgAnimate.hasAttribute("begin");
        assert svgAnimate.getAttribute("begin").value().equals("0");

        XMLTag svgAnimateTransform = svgRect.getChildren(1);
        assert svgAnimateTransform != null;

        assert svgAnimateTransform.getTagName().equals("animateTransform");
        assert svgAnimateTransform.hasAttribute("type");
        assert svgAnimateTransform.getAttribute("type").value().equals("translate");
    }

    @Test
    public void testSVGConvertWithNullValues() {
        TestRect rect = new TestRect();
        rect.position = null;
        XMLTag svgRect = SVGExport.convert(rect);

        assert svgRect.getTagName().equals("rect");

        assert !svgRect.hasAttribute("x");
        assert !svgRect.hasAttribute("y");
    }

    @Test
    public void testSVGConvertWithEmptyList() {
        TestRect rect = new TestRect();
        rect.randomValues.clear();
        XMLTag svgRect = SVGExport.convert(rect);

        assert svgRect.getTagName().equals("rect");

        assert !svgRect.hasAttribute("randomValues");
    }

    @Test
    public void testSVGExport() {
        TestRect rect = new TestRect();
        String filepath = "test_output.svg";
        assert SVGExport.export(rect, filepath);

        File file = new File(filepath);
        assert file.exists();

        file.delete();
    }

}
