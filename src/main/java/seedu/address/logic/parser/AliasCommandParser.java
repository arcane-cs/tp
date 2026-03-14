package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses alias-related commands by dispatching on the action word (add, delete, etc.).
 */
public class AliasCommandParser implements Parser<Command> {

    public static final String MESSAGE_USAGE = "alias: Manages aliases.\n"
            + "  alias add n/NAME al/ALIAS\n"
            + "  alias delete n/NAME al/ALIAS";

    private static final Pattern ACTION_FORMAT = Pattern.compile("(?<action>\\S+)(?<arguments>.*)");

    @Override
    public Command parse(String args) throws ParseException {
        final Matcher matcher = ACTION_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String action = matcher.group("action");
        final String arguments = matcher.group("arguments");

        switch (action) {
        case "add":
            return new AddAliasCommandParser().parse(arguments);
        case "delete":
            return new DeleteAliasCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
