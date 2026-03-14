package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Alias;
import seedu.address.model.person.Name;

/**
 * Adds an alias to an existing person in Harmony.
 */
public class AddAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an alias to an existing person in Harmony. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ALIAS + "ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Benjamin "
            + PREFIX_ALIAS + "Benjumpin";

    public static final String MESSAGE_SUCCESS = "Alias added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_ALIAS = "This alias already exists for this contact";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Error: Name does not exist";

    private final Name targetName;
    private final Alias aliasToAdd;

    /**
     * Creates an AddAliasCommand to add {@code alias} to the person with {@code name}.
     */
    public AddAliasCommand(Name name, Alias alias) {
        requireNonNull(name);
        requireNonNull(alias);
        this.targetName = name;
        this.aliasToAdd = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: implement once Person supports aliases ie. getAliases(), constructor with aliases
        // 1. Find person by name. If not found, throw MESSAGE_PERSON_NOT_FOUND
        // 2. Check if that alias already exists for that person. If yes, throw MESSAGE_DUPLICATE_ALIAS
        // 3. Add alias to person and update model
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddAliasCommand)) {
            return false;
        }

        AddAliasCommand otherCommand = (AddAliasCommand) other;
        return targetName.equals(otherCommand.targetName)
                && aliasToAdd.equals(otherCommand.aliasToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .add("aliasToAdd", aliasToAdd)
                .toString();
    }
}
