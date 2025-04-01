package examblock.model;

/**
 *An object describing a single Year 12 Subject.
 */
public class Subject {
    /**
     * title of the subject
     */
    private final String title;
    /**
     * description of the subject
     */
    private final String description;

    /**
     * Constructs a new Year 12 Subject object.
     *
     * @param title       the string title of this subject, consisting of one or more capitalised
     *                    words separated by one or
     *                    more spaces or other punctuation.
     * @param description the string description of this subject, in whole sentences, each
     *                    beginning with a capital and
     *                    finishing with a full stop, with words separated by one or more spaces
     *                    or other punctuation.
     */
    public Subject(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Gets the `title` of this subject.
     *
     * @return the String title of this subject.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the text `description` of this subject.
     *
     * @return the String description of this subject.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a detailed string representation of this subject. Returns the `title` in all
     * uppercase,
     * then on a new line, the entire text `description` inside double quotes.
     *
     * @return a string representation of this subject.
     */
    public String getFullDetail() {
        return title.toUpperCase()
                + "\n\""
                + description
                + "\"";
    }

    /**
     * Returns a brief string representation of this subject. Returns the subject `title` in all
     * uppercase.
     *
     * @return the subject title as a String in all uppercase and a newline.
     */
    @Override
    public String toString() {
        return title.toUpperCase()
                + "\n";
    }
}
