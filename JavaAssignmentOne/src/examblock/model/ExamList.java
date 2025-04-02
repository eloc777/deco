package examblock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection object for holding and managing a list of Exams.
 */
public class ExamList {
    /**
     * List of Exams.
     */
    private List<Exam> exams;

    /**
     * Constructs an empty list of Exams.
     */
    public ExamList() {
        this.exams = new ArrayList<>();
    }

    /**
     * Adds an Exam to this list of Exams.
     *
     * @param exam The exam object being added to this list.
     * @throws IllegalArgumentException if the exam is null.
     */
    public void add(Exam exam) {
        if (exam
                == null) {
            throw new IllegalArgumentException("Exam cannot be null.");
        }
        exams.add(exam);
    }

    /**
     * Removes a given Exam from this ExamList.
     *
     * @param exam The exam to remove from this list.
     * @throws IllegalArgumentException if the exam is null or not found in the list.
     */
    public void removeExam(Exam exam) {
        if (exam
                == null) {
            throw new IllegalArgumentException("Exam cannot be null.");
        }
        if (!exams.remove(exam)) {
            throw new IllegalArgumentException("Exam not found in the list.");
        }
    }

    /**
     * Gets the first Exam with a matching Subject title.
     *
     * @param title The title of the Exam's Subject to be found.
     * @return The first Exam with a matching Subject title, if it exists.
     * @throws IllegalStateException if no matching exam is found.
     */
    public Exam bySubjectTitle(String title) throws IllegalStateException {
        for (Exam exam : exams) {
            if (exam.getSubject().toString().equalsIgnoreCase(title)) {
                return exam;
            }
        }
        throw new IllegalStateException();
    }

    /**
     * Creates a new List holding references to all the Exams managed by this ExamList and
     * returns it.
     *
     * @return A new List holding references to all the Exams managed by this ExamList.
     */
    public List<Exam> all() {
        return new ArrayList<>(exams);
    }

    /**
     * Returns detailed string representations of the contents of this exam list.
     *
     * @return Detailed string representations of the contents of this exam list.
     */
    public String getFullDetail() {
        StringBuilder details = new StringBuilder("Exam List Details:\n");
        for (Exam exam : exams) {
            details.append(exam.getFullDetail()).append("\n");
        }
        return details.toString();
    }

    /**
     * Returns a string representation of the contents of the exam manager.
     *
     * @return A string representation of the contents of the exam manager.
     */
    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder("Exams:\n");
        for (int i = 0; i
                < exams.size(); i++) {
            summary.append(i
                    + 1
                    + ". ");
            summary.append(exams.get(i).toString());
            summary.append("\n");
        }
        return summary.toString();
    }
}