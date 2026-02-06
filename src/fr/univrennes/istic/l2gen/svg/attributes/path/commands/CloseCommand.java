package fr.univrennes.istic.l2gen.svg.attributes.path.commands;

public record CloseCommand() implements IPathCommand {

    @Override
    public String getValue() {
        return "Z";
    }

}
