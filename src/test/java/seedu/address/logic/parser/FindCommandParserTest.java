package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AliasMatchesPredicate;
import seedu.address.model.person.GameContainsKeywordPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(List.of("Alice")));
        assertParseSuccess(parser, "n/Alice", expectedFindCommand);

        // with leading whitespace
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);
    }

    @Test
    public void parse_validNameArgsMultiWord_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(List.of("Alice Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindCommand);
    }

    @Test
    public void parse_emptyNamePrefix_throwsParseException() {
        assertParseFailure(parser, "n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validGamePrefix_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new GameContainsKeywordPredicate("Valorant"));
        assertParseSuccess(parser, "g/Valorant", expectedFindCommand);

        // with leading whitespace
        assertParseSuccess(parser, " g/Valorant", expectedFindCommand);
    }

    @Test
    public void parse_emptyGamePrefix_throwsParseException() {
        assertParseFailure(parser, "g/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAliasPrefix_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new AliasMatchesPredicate("BenJumpin"));
        assertParseSuccess(parser, "al/BenJumpin", expectedFindCommand);

        // with leading whitespace
        assertParseSuccess(parser, " al/BenJumpin", expectedFindCommand);
    }

    @Test
    public void parse_emptyAliasPrefix_throwsParseException() {
        assertParseFailure(parser, "al/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nameAndGameCombined_returnsFindCommand() {
        FindCommand result = null;
        try {
            result = parser.parse("n/Alice g/Valorant");
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Parsing combined name and game should not throw: " + e.getMessage());
        }
        org.junit.jupiter.api.Assertions.assertNotNull(result);
    }

    @Test
    public void parse_nameAndAliasCombined_returnsFindCommand() {
        FindCommand result = null;
        try {
            result = parser.parse("n/Alice al/BenJumpin");
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Should not throw: " + e.getMessage());
        }
        org.junit.jupiter.api.Assertions.assertNotNull(result);
    }

    @Test
    public void parse_gameAndAliasCombined_returnsFindCommand() {
        FindCommand result = null;
        try {
            result = parser.parse("g/Valorant al/BenJumpin");
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Should not throw: " + e.getMessage());
        }
        org.junit.jupiter.api.Assertions.assertNotNull(result);
    }

    @Test
    public void parse_allThreeCombined_returnsFindCommand() {
        FindCommand result = null;
        try {
            result = parser.parse("n/Alice g/Valorant al/BenJumpin");
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Parsing all three constraints should not throw: " + e.getMessage());
        }
        org.junit.jupiter.api.Assertions.assertNotNull(result);
    }

    @Test
    public void parse_emptyGameInCombined_throwsParseException() {
        assertParseFailure(parser, "n/Alice g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyAliasInCombined_throwsParseException() {
        assertParseFailure(parser, "n/Alice al/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
