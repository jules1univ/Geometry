package fr.univrennes.istic.l2gen.svg.attributes.path.commands;

public record QuadBezierCommand(Double x1, Double y1, Double x, Double y, QuadBezierCommandType type)
        implements IPathCommand {

    @Override
    public String getValue() {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case ABSOLUTE -> sb.append("Q");
            case RELATIVE -> sb.append("q");
            case SMOOTH -> sb.append("T");
            case SMOOTH_RELATIVE -> sb.append("t");
        }
        if (x1 != null && y1 != null) {
            sb.append(x1).append(",").append(y1).append(" ");
        }
        sb.append(x).append(",").append(y);
        return sb.toString();
    }

}
