package examblock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection class for managing a list of Students.
 */
public class StudentList {

    private List<Student> students;

    /**
     * Constructs an empty list of Students.
     */
    public StudentList() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a Student to this list of Students.
     *
     * @param student the student object being added to this list.
     */
    public void add(Student student) {
        students.add(student);
    }

    /**
     * Creates a new List holding references to all the Students managed by this StudentList and returns it.
     *
     * @return a new List holding references to all the Students managed by this StudentList.
     */
    public List<Student> all() {
        return new ArrayList<>(students);
    }

    /**
     * Get the Student with a matching LUI.
     *
     * @param lui the LUI of the Student to be found.
     * @return Student with a matching LUI, if it exists.
     * @throws IllegalStateException if it can't find a matching student.
     */
    public Student byLui(Long lui) throws IllegalStateException {
        for (Student student : students) {
            if (student.getLui().equals(lui)) {
                return student;
            }
        }
        throw new IllegalStateException("No student found with LUI: " + lui);
    }

    /**
     * Counts the number of either non-AARA or AARA students taking a particular Subject.
     *
     * @param subject the subject to be found.
     * @param aara    true to count AARA students or false to count non-AARA students.
     * @return the number of either non-AARA or AARA students taking a particular subject.
     */
    public int countStudents(Subject subject, boolean aara) {
        int count = 0;
        for (Student student : students) {
            if (student.getSubjects().contains(subject) && student.isAara() == aara) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns detailed string representations of the contents of this student list.
     *
     * @return detailed string representations of the contents of this student list.
     */
    public String getFullDetail() {
        StringBuilder details = new StringBuilder("Student List Details:\n");
        for (Student student : students) {
            details.append(student.getFullDetail()).append("\n");
        }
        return details.toString();
    }

    /**
     * Returns a minimal string representation of the contents of this student list.
     *
     * @return a minimal string representation of the contents of this student list.
     */
    @Override
    public String toString() {
        StringBuilder minimalDetails = new StringBuilder("Student List: ");
        for (Student student : students) {
            minimalDetails.append(student.shortName()).append(", ");
        }
        return minimalDetails.toString().replaceAll(", $", ""); // Remove trailing comma
    }
}