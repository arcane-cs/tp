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
 * Deletes an alias from an existing person in Harmony.
 */
public class DeleteAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an alias from an existing person in Harmony. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ALIAS + "ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Benjamin "
            + PREFIX_ALIAS + "Benjumpin";

    public static final String MESSAGE_SUCCESS = "Alias removed from %1$s: %2$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "No contact found with that name";
    public static final String MESSAGE_ALIAS_NOT_FOUND = "This alias does not exist for this contact";

    private final Name targetName;
    private final Alias aliasToDelete;

    /**
     * Creates a DeleteAliasCommand to remove {@code alias} from the person with {@code name}.
     */
    public DeleteAliasCommand(Name name, Alias alias) {
        requireNonNull(name);
        requireNonNull(alias);
        this.targetName = name;
        this.aliasToDelete = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: implement once Person supports aliases ie. getAliases(), constructor with aliases
        // 1. Find person by name. If not found, throw MESSAGE_PERSON_NOT_FOUND
        // 2. Check if that alias exists for that person. If not, throw MESSAGE_ALIAS_NOT_FOUND
        // 3. Remove alias from person and update model
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteAliasCommand)) {
            return false;
        }

        DeleteAliasCommand otherCommand = (DeleteAliasCommand) other;
        return targetName.equals(otherCommand.targetName)
                && aliasToDelete.equals(otherCommand.aliasToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .add("aliasToDelete", aliasToDelete)
                .toString();
    }
}
