package examblock.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a single Exam Session in a specific venue.
 * A session is a block of time in a venue where exams are conducted.
 * Sessions have unique numbers within each venue but not across venues.
 */
public class Session {
    /**
     * Session venue.
     */
    private final Venue venue;
    /**
     * Session number
     */
    private final int sessionNumber;
    /**
     * Session date
     */
    private final LocalDate day;
    /**
     * Starting time of the session
     */
    private final LocalTime startTime;
    /**
     * List of exams in the session
     */
    private ExamList exams;
    /**
     * total number of students in the session.
     */
    private int studentCount;
    /**
     * a list of desks available for this session
     */
    private List<Desk> desks;

    /**
     * Constructs a new empty Exam Session for a particular Venue.
     * The session has the same rows and columns of desks as the venue.
     *
     * @param venue         The exam venue for the new session.
     * @param sessionNumber The number (unique by venue) of the new session.
     * @param day           The date of the session.
     * @param start         The start time of the exam window.
     * @throws IllegalArgumentException if the venue is null.
     */
    public Session(Venue venue, int sessionNumber, LocalDate day, LocalTime start) {
        if (venue
                == null) {
            throw new IllegalArgumentException("Venue cannot be null.");
        }
        this.venue = venue;
        this.sessionNumber = sessionNumber;
        this.day = day;
        this.startTime = start;
        this.exams = new ExamList();
        this.studentCount = 0; // Initially no students are allocated
    }

    /**
     * Gets the venue of this session.
     *
     * @return The venue of this session.
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Gets the session number of this session.
     *
     * @return The session number of this session.
     */
    public int getSessionNumber() {
        return sessionNumber;
    }

    /**
     * Gets the date of this session.
     *
     * @return The date of this session.
     */
    public LocalDate getDate() {
        return day;
    }

    /**
     * Gets the start time of this session.
     *
     * @return The start time of this session.
     */
    public LocalTime getTime() {
        return startTime;
    }

    /**
     * Gets the list of exams being held in this session.
     *
     * @return A list of exams being held in this session.
     */
    public List<Exam> getExams() {
        return exams.all();
    }

    /**
     * Counts the number of students already scheduled in this session.
     *
     * @return The number of students already scheduled in this session.
     */
    public int countStudents() {
        return studentCount;
    }

    /**
     * Allocates an exam to this session (venue and time).
     *
     * @param exam           The exam to be allocated to this venue and time slot.
     * @param numberStudents The number of students being added with this allocation.
     * @throws IllegalArgumentException if the exam is null or if numberStudents is negative.
     */
    public void scheduleExam(Exam exam, int numberStudents) {
        if (exam
                == null) {
            throw new IllegalArgumentException("Exam cannot be null.");
        }
        if (numberStudents
                < 0) {
            throw new IllegalArgumentException("Number of students cannot be negative.");
        }
        exams.add(exam);
        studentCount += numberStudents; // Update the student count
    }

    /**
     * Helper function to format names for desk variables
     *
     * @param givenNames
     * @param familyName
     * @return single string with the first given name, a space, the initial of
     * first middle name, if any,
     * with a full stop after the initial (if present) of the student assigned.
     */
    private String formatName(String givenNames, String familyName) {

        // Split the given names into an array
        String[] nameParts = givenNames.split("\\s+");
        String firstGivenName = nameParts[0];
        // Extract initials for middle names
        StringBuilder middleInitials = new StringBuilder();
        for (int i = 1; i
                < nameParts.length; i++) {
            middleInitials.append(nameParts[i].charAt(0)).append(" ");
        }
        return firstGivenName
                + " "
                + middleInitials.toString().trim()
                + " "
                + familyName.charAt(0)
                + ".";
    }

    /**
     * Allocates students to desks for every exam in this session.
     *
     * @param exams  The current set of Year 12 Exams.
     * @param cohort All Year 12 students participating in exams.
     * @throws IllegalArgumentException if exams or cohort is null.
     */
    public void allocateStudents(ExamList exams, StudentList cohort) {
        if (exams
                == null
                || cohort
                == null) {
            throw new IllegalArgumentException("Exams and cohort cannot be null.");
        }
        //create studentList for each exam
        List<List<Student>> listOfStudentLists = new ArrayList<>();
        for (int i = 0; i
                < this.exams.all().size(); i++) {
            listOfStudentLists.add(new ArrayList<>());
        }
        //check each student to see if they are in a certain exam, if so, add to list
        for (Student student : cohort.all()) {
            boolean alreadyHasExam = false; //boolean to flag if a student gets added to two exam
            // lists
            //loop through each exam for this session and student to the associated list
            for (int i = 0; i
                    < this.exams.all().size(); i++) {

                if (student.getSubjects().all().contains(this.exams.all().get(i).getSubject())
                        && (student.isAara()
                        == venue.isAara())) {
                    if (alreadyHasExam) {
                        throw new IllegalArgumentException("Student "
                                + student
                                + " has more than one exam in this session");
                    }
                    listOfStudentLists.get(i).add(student);
                    //this will be picked up if the method tries to add them to another exam in
                    // this venue
                    alreadyHasExam = true;
                }
            }

        }
        //sort each list of students into alphabetical order
        for (int i = 0; i
                < listOfStudentLists.size(); i++) {
            listOfStudentLists.get(i).sort(Comparator.comparing(Student::familyName));
        }
        int requiredCols = (int) Math.ceil(((double) studentCount
                / venue.getRows()));
        int spareCols = venue.getColumns()
                - requiredCols;
        int spareDesks = venue.deskCount()
                - studentCount;
        //if there are more spare cols than required, than we can add spacer cols
        boolean canSkipCol = requiredCols
                <= spareCols;
        //init array of desks
        desks = new ArrayList<>();
        int desksAllocated = 0;
        int studentsAllocated = 0;
        int subjectCount = listOfStudentLists.size();
        //if multiple subjects, focus on spreading subjects, not spreading students
        if (subjectCount
                > 2) {
            canSkipCol = false;
        }
        //loop through each subject
        for (int i = 0; i
                < subjectCount; i++) {
            //assign a desk for each student in that subject
            for (int j = 0; studentsAllocated
                    < listOfStudentLists.get(i).size(); j++) {
                Desk desk = new Desk(desksAllocated
                        + 1);
                List<Student> currentExam = listOfStudentLists.get(i);
                //if canSkipCol and if we are on odd col, then make col empty
                if (canSkipCol
                        && (j
                        / venue.getRows())
                        % 2
                        != 0) {
                    desks.add(desk);
                    desksAllocated++;
                    spareDesks--;
                    continue;
                }
                //add student details
                desk.setFamilyName(currentExam.get(studentsAllocated).familyName());
                String givinAndInit = formatName(currentExam.get(studentsAllocated).givenNames(),
                        currentExam.get(studentsAllocated).familyName());
                desk.setGivenAndInit(givinAndInit);
                studentsAllocated++;
                desks.add(desk);
                desksAllocated++;
            }
            //add spare desks
            for (int j = 0; j
                    < spareDesks; j++) {
                Desk desk = new Desk(desksAllocated
                        + 1);
                desks.add(desk);
                desksAllocated++;
            }

        }


        System.out.println("Allocating students to desks...");
    }

    /**
     * Prints the layout of desks in this session in the venue.
     * Prints a grid showing desk numbers and assigned student details for each desk.
     */
    public void printDesks() {
        for (int i = 0; i
                < venue.getRows(); i++) {
            for (int j = 0; j
                    < venue.getColumns(); j++) {
                StringBuilder deskInfo = new StringBuilder();
                deskInfo.append("Desk "
                        + desks.get(i
                        + (venue.getRows())
                        * j).deskNumber());
                deskInfo.append("  "
                        + desks.get(i
                        + (venue.getRows())
                        * j).deskGivenAndInit());
                while (deskInfo.length()
                        < 30) {
                    deskInfo.append(" ");
                }
                System.out.print(deskInfo.toString());

            }
            System.out.println();
            System.out.println();
        }
    }


    /**
     * Returns a string representation of the state of this session, including its attributes and
     * exams scheduled within
     * it.
     *
     * @return A string representation of the state of this session.
     */
    @Override
    public String toString() {
        return "Session{"
                + "venue="
                + venue
                + ", sessionNumber="
                + sessionNumber
                + ", day="
                + day
                + ", startTime="
                + startTime
                + '}';
    }
}
