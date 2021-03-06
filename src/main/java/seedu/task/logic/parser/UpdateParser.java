//@@author A0141052Y
package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.IncorrectCommand;
import seedu.task.logic.commands.UpdateCommand;

public class UpdateParser extends BaseParser {
    private final static String FLAG_NAME = "name";
    private final static String FLAG_START_TIME = "starts";
    private final static String FLAG_CLOSE_TIME = "ends";
    private final static String FLAG_TAGS = "tag";
    private final static String FLAG_REMOVE_TAGS = "remove-tag";
    private final static String[] KEYWORD_ARGS_OPTIONAL = new String[]{
            FLAG_NAME,
            FLAG_START_TIME,
            FLAG_CLOSE_TIME,
            FLAG_TAGS,
            FLAG_REMOVE_TAGS
    };
    //@@author A0144939R
    @Override
    public Command parse(String command, String arguments) {
        this.extractArguments(arguments);
        
        String index = getSingleKeywordArgValue("index");
        Optional<Integer> possibleIndex = parseIndex(index);
        
        if (!possibleIndex.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }
        
        try {
            return new UpdateCommand(
                    possibleIndex.get(),
                    getSingleKeywordArgValue(FLAG_NAME),
                    getSingleKeywordArgValue(FLAG_START_TIME),
                    getSingleKeywordArgValue(FLAG_CLOSE_TIME),
                    getTags(FLAG_TAGS),
                    getTags(FLAG_REMOVE_TAGS)
            );
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }
    
    private Set<String> getTags(String keyword) {
        if (argumentsTable.containsKey(keyword)) {
            return new HashSet<>(argumentsTable.get(keyword));
        } else {
            return new HashSet<>();
        }
    }

    /**
     * Extracts the arguments and puts them in a HashMap
     * This method has been overriden to support the different nature of the update command's arguments 
     */
    @Override
    protected void extractArguments(String args) {
        argumentsTable.clear();
        String[] segments = args.trim().split(" ");
        String currentKey = "index";
        StringJoiner joiner = new StringJoiner(" ");        
        for (String segment : segments) {
            if (isDelimiter(segment)) {
                addToArgumentsTable(currentKey, joiner.toString().trim());
                currentKey = segment;
                joiner = new StringJoiner(" ");                
            } else {
                joiner.add(segment);
            }
        }
        addToArgumentsTable(currentKey, joiner.toString());
    }
    
    /**
     * Checks if a string is a valid delimiter for the add command
     * @param argument a string from the user input
     * @return true if the String is a valid delimiter, and false otherwise
     */
    private boolean isDelimiter(String argument) {
        for(int i = 0; i < KEYWORD_ARGS_OPTIONAL.length; i++) {
            if(argument.equals(KEYWORD_ARGS_OPTIONAL[i])) {
                return true;
            }
        }
        return false;
    }
}
