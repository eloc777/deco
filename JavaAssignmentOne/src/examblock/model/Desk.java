package examblock.model;

/**
 * Represents an individual desk in an exam venue
 */
public class Desk {
protected int deskNumber; //The desk number
protected String familyName; // Family name of student sitting at the desk
protected String givenAndInit; // a single string with the first given name, a space, the initial of first middle name, if any, with a full stop after the initial (if present) of the student assigned.
    /**
     * Constructs a Desk. Assigns the integer deskNumber as the numerical identifier and assigns null Strings to the names.
     * @param deskNumber the non-zero positive integer desk number
     */
    public Desk(int deskNumber) {
        this.deskNumber=deskNumber;
    }

    /**
     * Gets the number of this desk.
     * @return The number of this desk
     */
    public int DeskNumber() {
        return deskNumber;
    }

    /**
     * Gets the family name of the student assigned to this desk.
     * @return The family name of the student assigned to this desk.
     */
    public String FamilyName() {
        return familyName;
    }

    /**
     * Sets the family name of the student assigned to this desk.
     * @param familyName familyName - the family name of the student assigned to this desk.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Sets the first given name and initial of the student assigned to this desk.
     * @param givenAndInit a single string with the first given name, a space, the initial of first middle name, if any,
     *                    with a full stop after the initial (if present) of the student assigned.
     */
    public void setGivenAndInit(String givenAndInit) {
        this.givenAndInit = givenAndInit;
    }

    /**
     * Gets the first given name and initial of the student assigned to this desk. Gets the first given name, a space,
     * the initial of first middle name, if any, with a full stop after the initial (if present) of the student assigned
     * to this desk.
     * @return The first given name and initial of the student assigned to this desk.
     */
    public String getGivenAndInit() {
        return givenAndInit;
    }

    @Override
    public String toString() {
        return "Desk{" +
                "deskNumber=" + deskNumber +
                ", familyName='" + familyName + '\'' +
                ", givenAndInit='" + givenAndInit + '\'' +
                '}';
    }
}
