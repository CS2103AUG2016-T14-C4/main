package seedu.task.storage;

import javax.xml.bind.annotation.XmlValue;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;

/**
 * JAXB-friendly adapted version of the Tag.
 */
public class XmlAdaptedTag {

    @XmlValue
    public String tagName;

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedTag() {}

    /**
     * Converts a given Tag into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedTag(Tag source) {
        tagName = source.tagName;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Tag toModelType() throws IllegalValueException {
        return new Tag(tagName);
    }

}
