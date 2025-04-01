package examblock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection object for holding and managing `Subject`s.
 */
public class SubjectList{
    private List<Subject> subjects;

    /**
     * Constructs an empty list of `Subject`s.
     */
    public SubjectList() {
        subjects = new ArrayList<>();
    }

    /**
     * Adds a `Subject` to this list of `Subject`s.
     *
     * @param subject - the subject object being added to this list.
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    /**
     * Removes a given `Subject` from the `SubjectList`.
     *
     * @param subject - the subject to remove from this list.
     */
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    /**
     * Get the first `Subject` with a matching `title`.
     *
     * @param title the `title` of the `Subject` to be found.
     * @return The first `Subject` with a matching `title`, if it exists.
     * @throws IllegalStateException throw an IllegalStateException if it can't find a matching subject
     *         as that indicates there is a misalignment of the executing state and the complete list of possible subjects.
     */
    public Subject byTitle(String title) throws IllegalStateException {
        for (Subject subject : subjects) {
            if (subject.getTitle().equals(title)) {
                return subject;
            }
        }
        throw new IllegalStateException("Subject with title '" + title + "' not found");
    }

    /**
     * Creates a new `List` holding `references` to all the `Subject`s managed by this `SubjectList` and returns it.
     *
     * @return a new `List` holding `references` to all the `Subject`s managed by this `SubjectList`.
     */
    public List<Subject> all() {
        return new ArrayList<>(subjects);
    }

    /**
     * Returns detailed string representations of the contents of this subject list.
     *
     * @return detailed string representations of the contents of this subject list.
     */
    public String getFullDetail() {
        StringBuilder result = new StringBuilder("Subject List - " + subjects.size() + " subject(s)\n");

        for (int i = 0; i < subjects.size(); i++) {
            result.append(subjects.get(i).getFullDetail());
            if (i < subjects.size() - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Returns a brief string representation of the contents of this subject list.
     *
     * @return a brief string representation of the contents of this subject list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Subject List - " + subjects.size() + " subject(s)\n");

        for (int i = 0; i < subjects.size(); i++) {
            result.append(subjects.get(i).toString());
            if (i < subjects.size() - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}