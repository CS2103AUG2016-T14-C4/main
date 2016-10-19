package seedu.task.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;

/**
 * Finds and lists all tasks in task list whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 2103 cs 2105";

    private final Set<String> keywords;
    private final String tag;

    public FindCommand(Set<String> keywords,String tag) {
        this.keywords = keywords;
        this.tag = tag;
    }

    @Override
    public CommandResult execute() {
    	if(tag!=null){
    		model.updateFilteredTagList(tag);
    		return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    	}
        model.updateFilteredTaskList(keywords);
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

}