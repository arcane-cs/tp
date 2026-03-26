package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditContactCommand;
import seedu.address.model.person.Name;

public class EditContactCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);

    private final EditContactCommandParser parser = new EditContactCommandParser();

    @Test
    public void parse_validArgs_success() {
        EditContactCommand expected = new EditContactCommand(new Name(VALID_NAME_AMY), new Name(VALID_NAME_BOB));
        assertParseSuccess(parser, " n/" + VALID_NAME_AMY + " e/" + VALID_NAME_BOB, expected);
    }

    @Test
    public void parse_missingNamePrefix_failure() {
        assertParseFailure(parser, " e/" + VALID_NAME_BOB, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingNewNamePrefix_failure() {
        assertParseFailure(parser, " n/" + VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingBothPrefixes_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidName_failure() {
        assertParseFailure(parser, " n/James& e/" + VALID_NAME_BOB, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidNewName_failure() {
        assertParseFailure(parser, " n/" + VALID_NAME_AMY + " e/James&", Name.MESSAGE_CONSTRAINTS);
    }
}
