package examblock.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a single Year 12 Exam.
 * Provides details about the exam, including its type, subject, date, time, and optional attributes like unit, paper, and subtitle.
 */
public class Exam {
    /**
     * An enum representing the type of exam (INTERNAL or EXTERNAL).
     * INTERNAL exams are conducted by the school, while EXTERNAL exams are conducted by the QCAA.
     */
    public enum ExamType {
        /**
         * Internal assessment, conducted by the school.
         */
        INTERNAL,

        /**
         * External assessment, conducted by the QCAA.
         */
        EXTERNAL
    }

    // Fields
    protected final Subject subject;
    protected final ExamType examType;
    protected final Character unit; // Optional unit ID
    protected final Character paper; // Optional paper number (e.g., 1 or 2)
    protected final String subtitle; // Optional subtitle
    protected final LocalDate date;
    protected final LocalTime time;

    // Constructors

    /**
     * Constructs an Exam with minimal details.
     *
     * @param subject   The subject of the exam.
     * @param examType  The type of the exam (INTERNAL or EXTERNAL).
     * @param day       The day of the exam date.
     * @param month     The month of the exam date (1-12).
     * @param year      The year of the exam date (2025 or greater).
     * @param hour      The hour of the start time (7-17 in 24-hour format).
     * @param minute    The minute of the start time (0-59).
     */
    public Exam(Subject subject, ExamType examType, int day, int month, int year, int hour, int minute) {
        this(subject, examType, null, null, null, day, month, year, hour, minute);
    }

    /**
     * Constructs an Exam with an optional unit specified.
     *
     * @param subject   The subject of the exam.
     * @param examType  The type of the exam (INTERNAL or EXTERNAL).
     * @param unit      An optional unit ID if only one unit is applicable.
     * @param day       The day of the exam date.
     * @param month     The month of the exam date (1-12).
     * @param year      The year of the exam date (2025 or greater).
     * @param hour      The hour of the start time (7-17 in 24-hour format).
     * @param minute    The minute of the start time (0-59).
     */
    public Exam(Subject subject, ExamType examType, Character unit, int day, int month, int year, int hour, int minute) {
        this(subject, examType, null, null, unit, day, month, year, hour, minute);
    }

    /**
     * Constructs an Exam with a paper number and subtitle but no unit specified.
     *
     * @param subject   The subject of the exam.
     * @param examType  The type of the exam (INTERNAL or EXTERNAL).
     * @param paper     An optional paper number (null or 1 or 2).
     * @param subtitle  An optional subtitle (e.g., "Technology Free").
     * @param day       The day of the exam date.
     * @param month     The month of the exam date (1-12).
     * @param year      The year of the exam date (2025 or greater).
     * @param hour      The hour of the start time (7-17 in 24-hour format).
     * @param minute    The minute of the start time (0-59).
     */
    public Exam(Subject subject,
                ExamType examType,
                Character paper,
                String subtitle,
                int day,
                int month,
                int year,
                int hour,
                int minute) {
        this(subject, examType, paper, subtitle, null, day, month, year, hour, minute);
    }

    /**
     * Constructs an Exam with all optional details provided.
     *
     * @param subject   The subject of the exam.
     * @param examType  The type of the exam (INTERNAL or EXTERNAL).
     * @param paper     An optional paper number (null or 1 or 2).
     * @param subtitle  An optional subtitle (e.g., "Technology Free").
     * @param unit      An optional unit ID if only one unit is applicable.
     * @param day       The day of the exam date.
     * @param month     The month of the exam date (1-12).
     * @param year      The year of the exam date (2025 or greater).
     * @param hour      The hour of the start time (7-17 in 24-hour format).
     * @param minute    The minute of the start time (0-59).
     */
    public Exam(Subject subject,
                ExamType examType,
                Character paper,
                String subtitle,
                Character unit,
                int day,
                int month,
                int year,
                int hour,
                int minute) {
        if (subject == null || examType == null) {
            throw new IllegalArgumentException("Subject and Exam Type cannot be null.");
        }
        this.subject = subject;
        this.examType = examType;
        this.unit = unit;
        this.paper = paper;
        this.subtitle = subtitle;
        this.date = LocalDate.of(year, month, day);
        this.time = LocalTime.of(hour, minute);
    }

    // Methods

    /**
     * Gets the subject of this exam.
     *
     * @return The subject associated with this exam.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the full title of this exam including type and optional details like paper and subtitle.
     *
     * @return A string representing the full title of this exam.
     */
    public String getTitle() {
        StringBuilder title = new StringBuilder(examType.toString() + "\n" + subject.toString());
        if (paper != null) {
            title.append(", Paper ").append(paper);
        }
        if (subtitle != null) {
            title.append("\n").append(subtitle);
        }
        return title.toString();
    }

    /**
     * Gets a short title for this exam without any subtitles.
     *
     * @return A string representing a short title for this exam.
     */
    public String getShortTitle() {
        StringBuilder shortTitle = new StringBuilder(examType.toString() + ", " + subject.toString());
        if (paper != null) {
            shortTitle.append(", Paper ").append(paper);
        }
        return shortTitle.toString();
    }

    /**
     * Gets the date of this exam.
     *
     * @return A LocalDate object representing this exam's date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the start time for this exam's window.
     *
     * @return A LocalTime object representing this exam's start time.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a detailed string representation for this exam including all attributes.
     *
     * @return A detailed string representation for this exam including all attributes like type and optional fields.
     */
    public String getFullDetail() {
        return "Exam Details:\n" +
                "Subject: " + subject + "\n" +
                "Exam Type: " + examType + "\n" +
                "Unit: " + unit + "\n" +
                "Paper: " + paper + "\n" +
                "Subtitle: " + subtitle + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time;
    }

    /**
     * Returns a brief string representation for this exam including its type and subject only.
     *
     *@return A brief string representation for this.exam
     */
    @Override
    public String toString() { return getShortTitle();}}
