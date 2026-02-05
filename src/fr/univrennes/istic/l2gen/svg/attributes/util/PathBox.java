package fr.univrennes.istic.l2gen.svg.attributes.util;

public final class PathBox {

    public static Box computeBox(String data) {
        if (data.isEmpty()) {
            return Box.empty();
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        double currentX = 0;
        double currentY = 0;
        double subpathStartX = 0;
        double subpathStartY = 0;

        double lastControlX = 0;
        double lastControlY = 0;
        char lastCommand = ' ';

        int i = 0;
        while (i < data.length()) {
            while (i < data.length() && Character.isWhitespace(data.charAt(i))) {
                i++;
            }

            if (i >= data.length())
                break;

            char command = data.charAt(i);
            boolean isRelative = Character.isLowerCase(command);
            command = Character.toUpperCase(command);
            i++;

            switch (command) {
                case 'M':
                    double[] mCoords = parseCoordinates(data, i, 2);
                    i = mCoords[0] == -1 ? data.length() : (int) mCoords[0];
                    if (mCoords.length >= 3) {
                        double x = mCoords[1];
                        double y = mCoords[2];
                        if (isRelative) {
                            x += currentX;
                            y += currentY;
                        }
                        currentX = x;
                        currentY = y;
                        subpathStartX = x;
                        subpathStartY = y;
                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                    break;

                case 'L':
                    double[] lCoords = parseCoordinates(data, i, 2);
                    i = lCoords[0] == -1 ? data.length() : (int) lCoords[0];
                    if (lCoords.length >= 3) {
                        double x = lCoords[1];
                        double y = lCoords[2];
                        if (isRelative) {
                            x += currentX;
                            y += currentY;
                        }
                        currentX = x;
                        currentY = y;
                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                    break;

                case 'H':
                    double[] hCoords = parseCoordinates(data, i, 1);
                    i = hCoords[0] == -1 ? data.length() : (int) hCoords[0];
                    if (hCoords.length >= 2) {
                        double x = hCoords[1];
                        if (isRelative) {
                            x += currentX;
                        }
                        currentX = x;
                        minX = Math.min(minX, x);
                        maxX = Math.max(maxX, x);
                    }
                    break;

                case 'V':
                    double[] vCoords = parseCoordinates(data, i, 1);
                    i = vCoords[0] == -1 ? data.length() : (int) vCoords[0];
                    if (vCoords.length >= 2) {
                        double y = vCoords[1];
                        if (isRelative) {
                            y += currentY;
                        }
                        currentY = y;
                        minY = Math.min(minY, y);
                        maxY = Math.max(maxY, y);
                    }
                    break;

                case 'C':
                    double[] cCoords = parseCoordinates(data, i, 6);
                    i = cCoords[0] == -1 ? data.length() : (int) cCoords[0];
                    if (cCoords.length >= 7) {
                        double x1 = cCoords[1], y1 = cCoords[2];
                        double x2 = cCoords[3], y2 = cCoords[4];
                        double x = cCoords[5], y = cCoords[6];

                        if (isRelative) {
                            x1 += currentX;
                            y1 += currentY;
                            x2 += currentX;
                            y2 += currentY;
                            x += currentX;
                            y += currentY;
                        }

                        Box box = getCubicBezierBox(currentX, currentY, x1, y1, x2, y2, x, y);
                        minX = Math.min(minX, box.minX());
                        minY = Math.min(minY, box.minY());
                        maxX = Math.max(maxX, box.maxX());
                        maxY = Math.max(maxY, box.maxY());

                        lastControlX = x2;
                        lastControlY = y2;
                        currentX = x;
                        currentY = y;
                    }
                    break;

                case 'S':
                    double[] sCoords = parseCoordinates(data, i, 4);
                    i = sCoords[0] == -1 ? data.length() : (int) sCoords[0];
                    if (sCoords.length >= 5) {
                        double x2 = sCoords[1], y2 = sCoords[2];
                        double x = sCoords[3], y = sCoords[4];

                        double x1 = currentX;
                        double y1 = currentY;
                        if (lastCommand == 'C' || lastCommand == 'S') {
                            x1 = 2 * currentX - lastControlX;
                            y1 = 2 * currentY - lastControlY;
                        }

                        if (isRelative) {
                            x2 += currentX;
                            y2 += currentY;
                            x += currentX;
                            y += currentY;
                        }

                        Box box = getCubicBezierBox(currentX, currentY, x1, y1, x2, y2, x, y);
                        minX = Math.min(minX, box.minX());
                        minY = Math.min(minY, box.minY());
                        maxX = Math.max(maxX, box.maxX());
                        maxY = Math.max(maxY, box.maxY());

                        lastControlX = x2;
                        lastControlY = y2;
                        currentX = x;
                        currentY = y;
                    }
                    break;

                case 'Q':
                    double[] qCoords = parseCoordinates(data, i, 4);
                    i = qCoords[0] == -1 ? data.length() : (int) qCoords[0];
                    if (qCoords.length >= 5) {
                        double x1 = qCoords[1], y1 = qCoords[2];
                        double x = qCoords[3], y = qCoords[4];

                        if (isRelative) {
                            x1 += currentX;
                            y1 += currentY;
                            x += currentX;
                            y += currentY;
                        }

                        Box box = getQuadBezierBox(currentX, currentY, x1, y1, x, y);
                        minX = Math.min(minX, box.minX());
                        minY = Math.min(minY, box.minY());
                        maxX = Math.max(maxX, box.maxX());
                        maxY = Math.max(maxY, box.maxY());

                        lastControlX = x1;
                        lastControlY = y1;
                        currentX = x;
                        currentY = y;
                    }
                    break;

                case 'T':
                    double[] tCoords = parseCoordinates(data, i, 2);
                    i = tCoords[0] == -1 ? data.length() : (int) tCoords[0];
                    if (tCoords.length >= 3) {
                        double x = tCoords[1], y = tCoords[2];

                        double x1 = currentX;
                        double y1 = currentY;
                        if (lastCommand == 'Q' || lastCommand == 'T') {
                            x1 = 2 * currentX - lastControlX;
                            y1 = 2 * currentY - lastControlY;
                        }

                        if (isRelative) {
                            x += currentX;
                            y += currentY;
                        }

                        Box box = getQuadBezierBox(currentX, currentY, x1, y1, x, y);
                        minX = Math.min(minX, box.minX());
                        minY = Math.min(minY, box.minY());
                        maxX = Math.max(maxX, box.maxX());
                        maxY = Math.max(maxY, box.maxY());

                        lastControlX = x1;
                        lastControlY = y1;
                        currentX = x;
                        currentY = y;
                    }
                    break;

                case 'A':
                    double[] aCoords = parseCoordinates(data, i, 7);
                    i = aCoords[0] == -1 ? data.length() : (int) aCoords[0];
                    if (aCoords.length >= 8) {
                        double rx = aCoords[1], ry = aCoords[2];
                        double xAxisRotation = aCoords[3];
                        boolean largeArcFlag = aCoords[4] != 0;
                        boolean sweepFlag = aCoords[5] != 0;
                        double x = aCoords[6], y = aCoords[7];

                        if (isRelative) {
                            x += currentX;
                            y += currentY;
                        }

                        Box box = getArcBox(currentX, currentY, rx, ry, xAxisRotation,
                                largeArcFlag, sweepFlag, x, y);
                        minX = Math.min(minX, box.minX());
                        minY = Math.min(minY, box.minY());
                        maxX = Math.max(maxX, box.maxX());
                        maxY = Math.max(maxY, box.maxY());

                        currentX = x;
                        currentY = y;
                    }
                    break;

                case 'Z':
                    currentX = subpathStartX;
                    currentY = subpathStartY;
                    break;
            }

            lastCommand = command;
        }

        if (Double.isInfinite(minX)) {
            return Box.empty();
        } else {
            return new Box(minX, minY, maxX, maxY);
        }

    }

    private static double[] parseCoordinates(String data, int startIndex, int count) {
        double[] result = new double[count + 1];
        int resultIndex = 1;
        int i = startIndex;

        for (int coordIndex = 0; coordIndex < count; coordIndex++) {
            while (i < data.length() &&
                    (Character.isWhitespace(data.charAt(i)) || data.charAt(i) == ',')) {
                i++;
            }

            if (i >= data.length() || Character.isLetter(data.charAt(i))) {
                break;
            }

            int start = i;
            if (data.charAt(i) == '-' || data.charAt(i) == '+') {
                i++;
            }
            while (i < data.length() &&
                    (Character.isDigit(data.charAt(i)) || data.charAt(i) == '.')) {
                i++;
            }

            if (i > start) {
                try {
                    result[resultIndex++] = Double.parseDouble(data.substring(start, i));
                } catch (NumberFormatException e) {
                    result[0] = -1;
                    return result;
                }
            }
        }

        result[0] = i;
        return result;
    }

    private static double[] findCubicExtrem(double p0, double p1, double p2, double p3) {
        double a = 3 * (-p0 + 3 * p1 - 3 * p2 + p3);
        double b = 6 * (p0 - 2 * p1 + p2);
        double c = 3 * (p1 - p0);

        if (Math.abs(a) < 1e-10) {
            if (Math.abs(b) < 1e-10) {
                return new double[0];
            }
            return new double[] { -c / b };
        }

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new double[0];
        }

        double sqrtDisc = Math.sqrt(discriminant);
        return new double[] {
                (-b + sqrtDisc) / (2 * a),
                (-b - sqrtDisc) / (2 * a)
        };
    }

    private static double getCubeBezierPoint(double p0, double p1, double p2, double p3, double t) {
        double mt = 1 - t;
        return mt * mt * mt * p0 + 3 * mt * mt * t * p1 + 3 * mt * t * t * p2 + t * t * t * p3;
    }

    private static Box getCubicBezierBox(double x0, double y0, double x1, double y1,
            double x2, double y2, double x3, double y3) {
        double minX = Math.min(Math.min(x0, x3), Math.min(x1, x2));
        double minY = Math.min(Math.min(y0, y3), Math.min(y1, y2));
        double maxX = Math.max(Math.max(x0, x3), Math.max(x1, x2));
        double maxY = Math.max(Math.max(y0, y3), Math.max(y1, y2));

        double[] xExtrema = findCubicExtrem(x0, x1, x2, x3);
        double[] yExtrema = findCubicExtrem(y0, y1, y2, y3);

        for (double t : xExtrema) {
            if (t > 0 && t < 1) {
                double x = getCubeBezierPoint(x0, x1, x2, x3, t);
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
            }
        }

        for (double t : yExtrema) {
            if (t > 0 && t < 1) {
                double y = getCubeBezierPoint(y0, y1, y2, y3, t);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }

        return new Box(minX, minY, maxX, maxY);
    }

    private static Box getQuadBezierBox(double x0, double y0, double x1, double y1,
            double x2, double y2) {
        double minX = Math.min(Math.min(x0, x2), x1);
        double minY = Math.min(Math.min(y0, y2), y1);
        double maxX = Math.max(Math.max(x0, x2), x1);
        double maxY = Math.max(Math.max(y0, y2), y1);

        double tx = (x0 - x1) / (x0 - 2 * x1 + x2);
        double ty = (y0 - y1) / (y0 - 2 * y1 + y2);

        if (tx > 0 && tx < 1 && !Double.isNaN(tx)) {
            double x = (1 - tx) * (1 - tx) * x0 + 2 * (1 - tx) * tx * x1 + tx * tx * x2;
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
        }

        if (ty > 0 && ty < 1 && !Double.isNaN(ty)) {
            double y = (1 - ty) * (1 - ty) * y0 + 2 * (1 - ty) * ty * y1 + ty * ty * y2;
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        return new Box(minX, minY, maxX, maxY);
    }

    private static Box getArcBox(double x0, double y0, double rx, double ry,
            double xAxisRotation, boolean largeArcFlag,
            boolean sweepFlag, double x, double y) {
        double minX = Math.min(x0, x) - Math.abs(rx);
        double minY = Math.min(y0, y) - Math.abs(ry);
        double maxX = Math.max(x0, x) + Math.abs(rx);
        double maxY = Math.max(y0, y) + Math.abs(ry);

        return new Box(minX, minY, maxX, maxY);
    }

}
