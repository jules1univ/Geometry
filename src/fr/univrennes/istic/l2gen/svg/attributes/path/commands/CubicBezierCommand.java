package fr.univrennes.istic.l2gen.svg.attributes.path.commands;

public record CubicBezierCommand(Double x1, Double y1, Double x2, Double y2, Double x, Double y,
        CubicBezierCommandType type)
        implements IPathCommand {

    @Override
    public String getValue() {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case ABSOLUTE -> sb.append("C");
            case RELATIVE -> sb.append("c");
            case SMOOTH -> sb.append("S");
            case SMOOTH_RELATIVE -> sb.append("s");
        }
        if (x1 != null && y1 != null) {
            sb.append(x1).append(",").append(y1).append(" ");
        }
        sb.append(x2).append(",").append(y2).append(" ").append(x).append(",").append(y);
        return sb.toString();
    }

}
