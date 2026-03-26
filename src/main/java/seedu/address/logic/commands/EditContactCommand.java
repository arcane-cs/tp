package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Edits the name of an existing contact in the address book.
 */
public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "contact edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the name of the contact identified by their current name.\n"
            + "Parameters: " + PREFIX_NAME + "NAME " + PREFIX_NEW_NAME + "NEW_NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Janelle " + PREFIX_NEW_NAME + "Jan";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Contact updated: %1$s \u2192 %2$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Error: Name not found";
    public static final String MESSAGE_DUPLICATE_PERSON = "Error: A contact with that name already exists";

    private final Name targetName;
    private final Name newName;

    /**
     * @param targetName the current name of the contact to edit
     * @param newName    the new name for the contact
     */
    public EditContactCommand(Name targetName, Name newName) {
        requireNonNull(targetName);
        requireNonNull(newName);
        this.targetName = targetName;
        this.newName = newName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.getFilteredPersonList().stream()
                .filter(p -> p.getName().equals(targetName))
                .findFirst()
                .orElseThrow(() -> new CommandException(MESSAGE_PERSON_NOT_FOUND));

        Person editedPerson = new Person(newName, personToEdit.getTags(), personToEdit.getGames(),
                personToEdit.isUserProfile());

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, targetName, newName),
                false, false, editedPerson);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditContactCommand)) {
            return false;
        }
        EditContactCommand otherCommand = (EditContactCommand) other;
        return targetName.equals(otherCommand.targetName)
                && newName.equals(otherCommand.newName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .add("newName", newName)
                .toString();
    }
}
