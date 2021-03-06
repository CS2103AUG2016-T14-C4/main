package seedu.task.model;

import java.util.HashMap;
import java.util.Set;

import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.logic.CommandKeys.Commands;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;

/**
 * The API of the Model component.
 */
public interface Model {
    
    public enum FilterType {
        ALL,
        PIN,
        PENDING,
        COMPLETED,
        OVERDUE,
    }
    
    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the Task List */
    ReadOnlyTaskManager getTaskManager();

    /** Deletes the given task. */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Adds the given task */
    void addTask(Task task) throws UniqueTaskList.DuplicateTaskException;

    /** Updates the given task */
    void updateTask(ReadOnlyTask orginalTask, Task updateTask) throws UniqueTaskList.DuplicateTaskException;
    
    /** Rollback the task list */
    void rollback();
    
    //@@author A0153467Y
    /** Pins the given task as important */
    void pinTask(ReadOnlyTask originalTask, Task toPin);
    
    //@@author A0153467Y
    /** Unpins the given task*/
    void unpinTask(ReadOnlyTask originalTask, Task toUnpin);

    //@@author A0153467Y    
    /** Mark the given task as completed */
    void completeTask(ReadOnlyTask originalTask, Task completedTask);
    
    //@@author A0153467Y
    /** Unmark the given important task */
    void uncompleteTask(ReadOnlyTask originalTask, Task uncompletedTask);
    //@@author
    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyTask>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredListToShowAll();
    
    //@@author A0141052Y
    /** Updates the filter of the filtered task list to show based on the preset **/
    void updateFilteredList(FilterType filter);
    
    /** Updates the filter to show based on a list of tags provided **/
    void updateFilteredListByTags(Set<Tag> tags);
    //@@author

    /**
     * Updates the filter of the filtered task list to filter by the given
     * keywords
     */
    void updateFilteredTaskList(Set<String> keywords);
    
    //@@author A0144939R
    /**
     * Gets the alias map
     * @return The command represented by the alias, or null if no mapping exists
     */
    public HashMap<String, Commands> getAliasMap();
    
    /**
     * Sets mapping for given alias
     * @param command
     * @param alias
     */
    public void setMapping(Commands command, String alias);
}
