package exercise2;

import exercise1.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class ClassroomTest {
    private Classroom classroom;
    private Student thorosOfMyr;
    private Student yaraGreyjoy;
    private Student maesterAemon;
    private Student myrcellaBaratheon;
    private Student gregorClegane;
    private Student branStark;
    private Student osha;
    private Student hodor;

    @Before
    public void fillInClassroom() {
        classroom = new Classroom();
        hodor = new Student("Hodor", "001003");
        hodor.setScore("hunting", 12);
        hodor.setScore("figthing", 14);
        hodor.setScore("running", 10);
        classroom.addStudent(hodor);
        osha = new Student("Osha", "001005");
        osha.setScore("fighting", 14);
        osha.setScore("cooking", 4);
        osha.setScore("swimming", 4);
        classroom.addStudent(osha);
        branStark = new Student("Bran Stark", "001006");
        branStark.setScore("running", 0);
        branStark.setScore("archery", 14);
        classroom.addStudent(branStark);
        gregorClegane = new Student("Gregor Clegane", "001008");
        gregorClegane.setScore("fighting", 18);
        gregorClegane.setScore("running", 10);
        gregorClegane.setScore("hunting", 16);
        classroom.addStudent(gregorClegane);
        myrcellaBaratheon = new Student("Myrcella Baratheon", "001011");
        myrcellaBaratheon.setScore("cooking", 14);
        myrcellaBaratheon.setScore("swimming", 16);
        myrcellaBaratheon.setScore("archery", 8);
        classroom.addStudent(myrcellaBaratheon);
        maesterAemon = new Student("Maester Aemon", "001019");
        maesterAemon.setScore("running", 2);
        maesterAemon.setScore("cooking", 16);
        maesterAemon.setScore("hunting", 17);
        classroom.addStudent(maesterAemon);
        yaraGreyjoy = new Student("Yara Greyjoy", "001028");
        yaraGreyjoy.setScore("swimming", 18);
        yaraGreyjoy.setScore("fighting", 19);
        yaraGreyjoy.setScore("hunting", 16);
        classroom.addStudent(yaraGreyjoy);
        thorosOfMyr = new Student("Thoros of Myr", "001037");
        thorosOfMyr.setScore("fighting", 15);
        thorosOfMyr.setScore("archery", 10);
        thorosOfMyr.setScore("cooking", 19);
        classroom.addStudent(thorosOfMyr);
    }

    @Test
    public void averageScore() {
        assertThat(classroom.averageScore()).isCloseTo(12.48, offset(0.1));
    }

    @Test
    public void countStudents() {
        assertThat(classroom.countStudents()).isEqualTo(8);
    }

    @Test
    public void topScorers() {
        assertThat(classroom.topScorers("fighting", 3)).containsExactly(yaraGreyjoy, gregorClegane, thorosOfMyr);
    }

    @Test
    public void successfulStudents() {
        assertThat(classroom.successfulStudents()).containsExactly(yaraGreyjoy, gregorClegane, thorosOfMyr, myrcellaBaratheon, hodor);
    }
}
