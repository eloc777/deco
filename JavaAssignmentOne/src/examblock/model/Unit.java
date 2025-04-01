package examblock.model;

/**
 * An object describing a single-semester Year 12 Unit of a Year 12 Subject.
 * These are typically Unit 3 or Unit 4 for the Year 12 units, but may be different.
 */
public class Unit {
    protected final Subject subject;
    protected final Character unitId;
    protected final String title;
    protected final String description;

    /**
     * Constructs a new `Subject` `Unit` object.
     *
     * @param subject the parent subject of this unit.
     * @param unitId the single character unit identifier of this unit.
     * @param title the string title of this unit, consisting of one or more capitalised words separated by one or more spaces or other punctuation.
     * @param description the string description of this unit, in whole sentences, each beginning with a capital and finishing with a full stop, with words separated by one or more spaces or other punctuation.
     */
    public Unit(Subject subject, Character unitId, String title, String description) {
        this.subject = subject;
        this.unitId = unitId;
        this.title = title;
        this.description = description;
    }

    /**
     * Gets the parent `Subject` of this unit.
     *
     * @return the reference to the unit's parent subject.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns the identifier of this unit.
     *
     * @return the identifier of this unit.
     */
    public Character id() {
        return unitId;
    }

    /**
     * Gets the text `description` of the unit.
     *
     * @return the string `description` of the unit.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a detailed string representation of this unit.
     *
     * @return a detailed string representation of this unit.
     */
    public String getFullDetail() {
        return "UNIT " + unitId + ": " + title.toUpperCase() + "\n" +
                "Subject: " + subject.getTitle() + "\n" +
                "\"" + description + "\"";
    }

    /**
     * Returns a brief string representation of this unit.
     *
     * @return the unitID and title of this unit.
     */
    @Override
    public String toString() {
        return "Unit " + unitId + ": " + title;
    }
}
