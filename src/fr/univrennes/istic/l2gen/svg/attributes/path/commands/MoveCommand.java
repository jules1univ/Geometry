package fr.univrennes.istic.l2gen.svg.attributes.path.commands;

public record MoveCommand(Double x, Double y, MoveCommandType type) implements IPathCommand {

    @Override
    public String getValue() {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case ABSOLUTE -> sb.append("M");
            case RELATIVE -> sb.append("m");

            case LINE -> sb.append("L");
            case LINE_RELATIVE -> sb.append("l");

            case HORIZONTAL -> sb.append("H");
            case HORIZONTAL_RELATIVE -> sb.append("h");

            case VERTICAL -> sb.append("V");
            case VERTICAL_RELATIVE -> sb.append("v");
        }

        sb.append(x);
        if (y != null) {
            sb.append(",").append(y);
        }
        return sb.toString();
    }

}
