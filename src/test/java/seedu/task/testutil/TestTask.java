//@@author A0144939R
package seedu.task.testutil;

import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private DateTime openTime;
    private DateTime closeTime;
    private UniqueTagList tags;
    private boolean isImportant;
    private boolean isCompleted;

    public TestTask() {
        tags = new UniqueTagList();
        openTime = new DateTime();
        closeTime = new DateTime();     
    }
    
    //@@author A0141052Y
    /**
     * Creates a duplicate (copy) of an existing TestTask
     * @param task the TestTask to copy from
     */
    public TestTask(TestTask task) {
        this.name = task.getName();
        this.openTime = task.getOpenTime();
        this.closeTime = task.getCloseTime();
        this.isCompleted = task.getComplete();
        this.isImportant = task.getImportance();
        this.tags = new UniqueTagList(task.getTags());
    }
    //@@author
    
    public void setName(Name name) {
        this.name = name;
    }
    //@@author A0153467Y
    public void setIsImportant(boolean isImportant){
    	this.isImportant=isImportant;
    }
    //@@author
    public void setOpenTime(DateTime openTime) {
        this.openTime = openTime;
    }
    
    public void setCloseTime(DateTime closeTime) {
        this.closeTime = closeTime;
    }
    //@@author A0153467Y
    public void setIsCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    @Override
    public Name getName() {
        return name;
    }
    //@@author A0153467Y
    @Override
    public boolean getComplete() {
        return isCompleted;
    }
    //@@author
    @Override
    public DateTime getOpenTime() {
        return openTime;
    }

    @Override
    public DateTime getCloseTime() {
        return closeTime;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    //@@author A0153467Y

    @Override
    public boolean getImportance() {
        return isImportant;
    }
    //@@author
    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().taskName + " ");
        if (!this.getOpenTime().isEmpty()) {
            sb.append("starts " + this.getOpenTime().toISOString() + " ");
        }
        if (!this.getCloseTime().isEmpty()) {
            sb.append("ends " + this.getCloseTime().toISOString() + " ");
        }
        this.getTags().getInternalList().stream().forEach(s -> sb.append("tag " + s.tagName + " "));
        return sb.toString();
    }
    
    public String getArgs() {
        StringBuilder sb = new StringBuilder();
        sb.append(" "+this.getName().taskName + " ");
        sb.append("starts " + this.getOpenTime().toISOString() + " ");
        sb.append("ends " + this.getCloseTime().toISOString() + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("tag " + s.tagName + " "));
        return sb.toString();
    }
}
