package fr.univrennes.istic.l2gen.svg.io;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.svg.xml.parser.XMLParser;

public final class SVGImport {
    // TODO: forcer les fields only sur les annotation SVG pour pouvoir charger
    // directement les class et forcer un constructeur vide au moment du register

    private static final Map<String, List<Class<Void>>> shapes = new HashMap<>();

    public static void register(String tagName, Class<Void> importer) {
        shapes.putIfAbsent(tagName, new ArrayList<>());
        shapes.computeIfPresent(tagName, (key, value) -> {
            value.add(importer);
            return null;
        });
    }

    private static ISVGShape convertXMLToShape(XMLTag tag) {
        return null;
    }

    public static ISVGShape load(String filename) {
        try (FileReader fr = new FileReader(filename)) {
            String source = fr.toString();
            XMLParser parser = new XMLParser(source);

            XMLTag root = parser.parse();
            if (root == null) {
                return null;
            }
            XMLTag svg = root.getFirstChildByName("svg");
            if (svg == null) {
                return null;
            }
            XMLTag backgroundRect = svg.getFirstChildByName("rect");
            if (backgroundRect == null) {
                return null;
            }

            XMLTag mainShape = backgroundRect.getFirstChild();
            return convertXMLToShape(mainShape);
        } catch (Exception e) {
            return null;
        }
    }
}
