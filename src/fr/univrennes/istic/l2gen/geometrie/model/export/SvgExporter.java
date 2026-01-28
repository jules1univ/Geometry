package fr.univrennes.istic.l2gen.geometrie.model.export;

import java.io.FileWriter;

import fr.univrennes.istic.l2gen.geometrie.model.shapes.IShape;

public final class SvgExporter {

    private final double canvasSize;
    private final String canvasBackgroundColor;

    public SvgExporter(double size, String backgroundColor) {
        this.canvasSize = size;
        this.canvasBackgroundColor = backgroundColor;
    }

    public boolean export(IShape forme, String filename) {
        StringBuilder sb = new StringBuilder();

        sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + canvasSize + "\" height=\""
                + canvasSize + "\">\n");
        sb.append("<rect width=\"100%\" height=\"100%\" fill=\"" + canvasBackgroundColor + "\"/>\n");
        sb.append(forme.toSVG());
        sb.append("\n</svg>");

        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
