package seedu.codesphere.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.logic.parser.ParserUtil;
import seedu.codesphere.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String ranking;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String ranking) {
        this.ranking = ranking;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        ranking = source.ranking.toString();
    }

    @JsonValue
    public String getTagName() {
        return ranking;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(ranking)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return ParserUtil.parseTag(ranking);
    }

}
