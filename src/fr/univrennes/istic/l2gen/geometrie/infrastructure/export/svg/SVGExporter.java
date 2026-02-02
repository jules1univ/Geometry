package fr.univrennes.istic.l2gen.geometrie.infrastructure.export.svg;

import java.io.FileWriter;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.export.Exporter;
import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLAttribute;
import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.geometrie.shapes.AbstractShape;

public final class SVGExporter extends Exporter {

    public SVGExporter() {
    }

    public boolean export(AbstractShape forme, String filename) {
        XMLTag svg = new XMLTag("svg");
        svg.addAttribute(new XMLAttribute("xmlns", "http://www.w3.org/2000/svg"));
        svg.addAttribute(new XMLAttribute("version", "1.1"));
        svg.addAttribute(new XMLAttribute("width", "1000"));
        svg.addAttribute(new XMLAttribute("height", "1000"));

        XMLTag background = new XMLTag("rect");
        background.addAttribute(new XMLAttribute("width", "100%"));
        background.addAttribute(new XMLAttribute("height", "100%"));
        background.addAttribute(new XMLAttribute("fill", "white"));
        svg.addChild(background);

        svg.addChild(forme.toSVG());

        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(svg.toXMLString());
            fw.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
