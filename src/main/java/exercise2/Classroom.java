package exercise2;

import exercise1.DuplicateStudentException;
import exercise1.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A classroom gathers many distinct students.
 */
public class Classroom {
    private Set<Student> students = new HashSet<>();

    /**
     * Adds a student to this classroom.
     *
     * @throws NullPointerException if the student is null.
     * @throws DuplicateStudentException if the given student is already part of the classroom.
     */
    public void addStudent(Student student) {
        Objects.requireNonNull(student, "student may not be null");

        if (students.contains(student)) {
            throw new DuplicateStudentException(student.getRegistrationNumber());
        } else {
            students.add(student);
        }
/*
        boolean newElement = students.add(student);
        if (!newElement) {
            throw new DuplicateStudentException(student.getRegistrationNumber());
        }
*/
    }

    /**
     * Returns the average score of the classroom, all courses included.
     *
     * @return the average score of the students or 0 if there is none.
     */
    public double averageScore() {
        return students.stream()
            .flatMapToInt(student -> student.getScoreByCourse().values().stream().mapToInt(Integer::intValue))
            .average()
            .orElse(0.0);
    }

    /**
     * Returns the number of students that are part of this classroom.
     */
    public int countStudents() {
        return students.size();
    }

    /**
     * Returns the <code>n</code> students that scored best for the given course.
     * The returned students are sorted by decreasing score.
     *
     * @param course the course to evaluate.
     * @param n the number of top students to return.
     * @return the top <code>n</code> students or an empty list if there is no student or none did score the course.
     * @throws NullPointerException if the course is null.
     * @throws IllegalArgumentException if <code>n</code> is less than or equal to 0.
     */
    public List<Student> topScorers(String course, int n) {
        Map<Student, OptionalInt> scoreByStudent = students.stream()
            .collect(Collectors.toMap(Function.identity(), student -> student.getScore(course)));
        return scoreByStudent.entrySet().stream()
            .filter(entry -> entry.getValue().isPresent())
            .sorted(Map.Entry.comparingByValue((o1, o2) -> -Integer.compare(o1.getAsInt(), o2.getAsInt())))
            .limit(n)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    /**
     * Returns all the students that are successful.
     * The returned list is sorted by decreasing average score.
     *
     * @return a sorted list of students or an empty list.
     */
    public List<Student> successfulStudents() {
        return students.stream()
            .filter(Student::isSuccessful)
            .sorted(Comparator.comparingDouble(student -> -student.averageScore()))
            .collect(Collectors.toList());
    }
}
