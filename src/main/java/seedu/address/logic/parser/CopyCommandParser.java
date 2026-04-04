package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new CopyCommand object.
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        String preamble = argMultimap.getPreamble().trim();
        boolean useUserProfile = preamble.equalsIgnoreCase("me");
        boolean hasNamePrefix = argMultimap.getValue(PREFIX_NAME).isPresent();

        if (!useUserProfile) {
            ParserUtil.verifyIndexOrNamePresent(preamble, hasNamePrefix, CopyCommand.MESSAGE_USAGE);
        } else if (hasNamePrefix) {
            throw new ParseException("Please do not provide a name prefix (n/) "
                    + "when targeting your own profile with 'me'.");
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        Index index = (!useUserProfile && !preamble.isEmpty() && !hasNamePrefix)
                ? ParserUtil.parseIndex(preamble) : null;
        Name name = hasNamePrefix ? ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()) : null;

        return new CopyCommand(index, name, useUserProfile);
    }
}
