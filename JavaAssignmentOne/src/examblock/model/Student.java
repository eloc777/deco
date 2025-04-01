package examblock.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Year 12 student.
 */
public class Student {
    protected Long lui;
    protected String givenNames;
    protected String familyName;
    protected LocalDate dob;
    protected String house;
    protected Boolean aara;
    protected List<Subject> subjects;

    /**
     * Constructs a new Student object with no AARA requirements by default.
     *
     * @param lui         the student's 10-digit Learner Unique Identifier (LUI)
     * @param givenNames  the given names for the student
     * @param familyName  the family name for the student
     * @param day         the day of birth (1-31)
     * @param month       the month of birth (1-12)
     * @param year        the year of birth (1965-2015)
     * @param house       the house colour (Blue, Green, Red, White, or Yellow)
     */
    public Student(Long lui, String givenNames, String familyName, int day, int month, int year, String house) {
        this.lui = lui;
        this.givenNames = givenNames;
        this.familyName = familyName;
        this.dob = LocalDate.of(year, month, day);
        this.house = house;
        this.aara = false;
        this.subjects = new ArrayList<>();
    }

    /**
     * Constructs a new Student object with AARA requirements.
     *
     * @param lui         the student's 10-digit Learner Unique Identifier (LUI)
     * @param givenNames  the given names for the student
     * @param familyName  the family name for the student
     * @param day         the day of birth (1-31)
     * @param month       the month of birth (1-12)
     * @param year        the year of birth (1965-2015)
     * @param house       the house colour (Blue, Green, Red, White, or Yellow)
     * @param aara        true if the student requires AARA adjustments, false otherwise
     */
    public Student(Long lui, String givenNames, String familyName, int day, int month, int year, String house, Boolean aara) {
        this.lui = lui;
        this.givenNames = givenNames;
        this.familyName = familyName;
        this.dob = LocalDate.of(year, month, day);
        this.house = house;
        this.aara = aara;
        this.subjects = new ArrayList<>();
    }

    /**
     * Changes the LUI of the student.
     *
     * @param lui the new LUI for the student
     */
    public void changeLui(Long lui) {
        this.lui = lui;
    }

    /**
     * Sets the family name of the student.
     *
     * @param familyName the new family name
     */
    public void setFamily(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Sets the given names of the student.
     *
     * @param givenNames the new given names
     */
    public void setGiven(String givenNames) {
        this.givenNames = givenNames;
    }

    /**
     * Gets the LUI of the student.
     *
     * @return the student's LUI
     */
    public Long getLui() {
        return lui;
    }

    /**
     * Gets the given names of the student.
     *
     * @return the student's given names
     */
    public String givenNames() {
        return givenNames;
    }

    /**
     * Gets the family name of the student.
     *
     * @return the student's family name
     */
    public String familyName() {
        return familyName;
    }

    /**
     * Gets the full name of the student (all given names and family name).
     *
     * @return the full name of the student
     */
    public String fullName() {
        return givenNames + " " + familyName;
    }

    /**
     * Gets the first given name of the student.
     *
     * @return the student's first given name
     */
    public String firstName() {
        String[] names = givenNames.split(" ");
        return names[0];
    }

    /**
     * Gets the short name of the student (first given name and family name).
     *
     * @return the short name of the student
     */
    public String shortName() {
        return firstName() + " " + familyName;
    }

    /**
     * Gets the date of birth of the student.
     *
     * @return the student's date of birth
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Gets the house colour of the student.
     *
     * @return the student's house colour
     */
    public String getHouse() {
        return house;
    }

    /**
     * Returns true if the student is an AARA student (requires access arrangements and reasonable adjustments).
     *
     * @return true if the student requires AARA adjustments, false otherwise
     */
    public Boolean isAara() {
        return aara;
    }

    /**
     * Adds a subject to the student.
     *
     * @param subject the subject to be added
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    /**
     * Removes a subject from the student.
     *
     * @param subject the subject to be removed
     */
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    /**
     * Gets the list of subjects for the student.
     *
     * @return the list of subjects for this student
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Creates and returns a string representation of the student's detailed state.
     *
     * @return a detailed string representation of the student
     */
    public String getFullDetail() {
        return "Student LUI: " + lui + "\n" +
                "Name: " + fullName() + "\n" +
                "Date of Birth: " + dob + "\n" +
                "House: " + house + "\n" +
                "AARA: " + (aara ? "Yes" : "No") + "\n" +
                "Subjects: " + subjects.size();
    }

    /**
     * Creates and returns a string representation of the student's basic state.
     *
     * @return a basic string representation of the student
     */
    @Override
    public String toString() {
        return "Student: " + fullName() + " (" + lui + ")";
    }
}