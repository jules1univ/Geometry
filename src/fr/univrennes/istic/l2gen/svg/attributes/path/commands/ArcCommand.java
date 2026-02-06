package fr.univrennes.istic.l2gen.svg.attributes.path.commands;

public record ArcCommand(double rx, double ry, double xAxisRotation, boolean largeArcFlag, boolean sweepFlag, double x,
        double y, ArcCommandType type) implements IPathCommand {

    @Override
    public String getValue() {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case ABSOLUTE -> sb.append("A");
            case RELATIVE -> sb.append("a");
        }
        sb.append(rx).append(",").append(ry).append(" ").append(xAxisRotation).append(" ")
                .append(largeArcFlag ? "1" : "0").append(" ").append(sweepFlag ? "1" : "0").append(" ")
                .append(x).append(",").append(y);
        return sb.toString();
    }
}
