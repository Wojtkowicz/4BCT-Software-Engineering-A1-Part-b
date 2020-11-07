
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    // Creating students
    private static final Student student1 = new Student("Mary Ham", 20, "01/06/2000", 16365972L);
    private static final Student student2 = new Student("John Smith", 22, "09/08/1998", 86739684L);
    private static final Student student3 = new Student("Paul Baker", 21, "24/12/1998", 26542867L);
    private static final Student student4 = new Student("Peter Black", 22, "03/04/1998", 29367418L);
    private static final Student student5 = new Student("Amy Turner", 22, "28/05/1998", 52009569L);
    private static final Student student6 = new Student("Zack Wheel", 20, "17/11/1999", 10376423L);
    private static final Student student7 = new Student("Sarah Yale", 21, "21/01/1999", 85746277L);
    private static final Student student8 = new Student("Michael Love", 23, "19/04/1997", 55493658L);
    private static final Student student9 = new Student("Oran Apple", 22, "02/11/1997", 73953765L);
    private static final Student student10 = new Student("Rachel Red", 22, "12/01/1998", 23498832L);

    // Creating modules
    private static final Module module1 = new Module("Software Engineering", "CT417");
    private static final Module module2 = new Module("Machine Learning", "CT4101");
    private static final Module module3 = new Module("Artificial Intelligence", "CT421");
    private static final Module module4 = new Module("Circuits", "E224");
    private static final Module module5 = new Module("Math", "E861");

    // Creating Academic start and end date
    private static final DateTime dateTime1 = new DateTime(2020, 9, 1, 10, 0);
    private static final DateTime dateTime2 = new DateTime(2021, 6, 20, 16, 30);

    // Creating courses
    private static final CourseProgramme courseProgramme1 = new CourseProgramme("CS & IT", dateTime1, dateTime2);
    private static final CourseProgramme courseProgramme2 = new CourseProgramme("ECE", dateTime1, dateTime2);

    // Creating Lists of courses, modules and students
    private static List<CourseProgramme> courses;
    private static List<Module> modules;
    private static List<Student> students;

    public static void main(String args[]) {

        // Creating student, module and course data
        setUp();

        // Printing Data to console
        printCourseData();
        printModuleData();
        printStudentData();
    }

    private static void setUp() {

        // Populating Lists of available courses, modules and students
        courses = List.of(courseProgramme1, courseProgramme2);
        modules = List.of(module1, module2, module3, module4, module5);
        students = List.of(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10);

        // Setting Course Modules
        courseProgramme1.setModules(List.of(module1, module2, module3));
        courseProgramme2.setModules(List.of(module2, module3, module4, module5));

        // Signing up students to the courses
        signUpStudentToCourse(student1, courseProgramme1);
        signUpStudentToCourse(student2, courseProgramme1);
        signUpStudentToCourse(student3, courseProgramme1);
        signUpStudentToCourse(student4, courseProgramme1);
        signUpStudentToCourse(student5, courseProgramme1);
        signUpStudentToCourse(student6, courseProgramme2);
        signUpStudentToCourse(student7, courseProgramme2);
        signUpStudentToCourse(student8, courseProgramme2);
        signUpStudentToCourse(student9, courseProgramme2);
        signUpStudentToCourse(student10, courseProgramme2);
    }

    // Adds a student to a course and its modules, saves the course to the student record
    private static void signUpStudentToCourse(final Student student, final CourseProgramme courseProgramme) {
        List<CourseProgramme> requiredCourseList = courses.stream().filter(c -> c.getName().equals(courseProgramme.getName())).collect(Collectors.toList());
        List<Student> requiredStudentList = students.stream().filter(s -> s.getName().equals(student.getName())).collect(Collectors.toList());
        CourseProgramme currentCourse = requiredCourseList.get(0);
        Student currentStudent = requiredStudentList.get(0);
        List<Module> currentCourseModules = currentCourse.getModules();
        List<CourseProgramme> studentCourses = currentStudent.getRegisteredCourses();
        List<Module> currentStudentModules = currentStudent.getRegisteredModules();

        // Add the student to the course
        List<Student> courseStudents = currentCourse.getStudentsEnrolled();
        List<Student> updatedCourseStudents = new ArrayList<>();
        if (courseStudents != null && !courseStudents.contains(student)) {
            updatedCourseStudents.addAll(courseStudents);
            updatedCourseStudents.add(student);
            currentCourse.setStudentsEnrolled(updatedCourseStudents);
        } else if (courseStudents == null) {
            updatedCourseStudents.add(student);
            currentCourse.setStudentsEnrolled(updatedCourseStudents);
        }

        // Add student to the course modules
        if (currentCourseModules != null) {
            currentCourseModules.stream().forEach(m -> {
                List<Student> updatedModuleStudents = new ArrayList<>();
                if (m.getStudentsEnrolled() != null && !m.getStudentsEnrolled().contains(student)) {
                    updatedModuleStudents.addAll(m.getStudentsEnrolled());
                    updatedModuleStudents.add(student);
                    m.setStudentsEnrolled(updatedModuleStudents);
                } else if (m.getStudentsEnrolled() == null) {
                    updatedModuleStudents.add(student);
                    m.setStudentsEnrolled(updatedModuleStudents);
                }
            });
        }

        // Add course to student record
        List<CourseProgramme> updatedStudentCourses = new ArrayList<>();
        if (studentCourses != null && !studentCourses.contains(courseProgramme)) {
            updatedStudentCourses.addAll(studentCourses);
            updatedStudentCourses.add(courseProgramme);
            currentStudent.setRegisteredCourses(updatedStudentCourses);
        } else if (studentCourses == null) {
            updatedStudentCourses.add(courseProgramme);
            currentStudent.setRegisteredCourses(updatedStudentCourses);
        }

        // Add course modules to the student record
        List<Module> updatedStudentModules = new ArrayList<>();
        if (currentStudentModules != null) {
            updatedStudentModules.addAll(currentStudentModules);
            currentCourseModules.stream().forEach(m ->
            {
                if (!currentStudentModules.contains(m)) {
                    updatedStudentModules.add(m);
                }
            });
        } else if (studentCourses == null && currentCourseModules != null) {
            updatedStudentModules.addAll(currentCourseModules);
            currentStudent.setRegisteredModules(updatedStudentModules);
        }

        // Add course to the course module
        currentCourseModules.stream().forEach(m -> {
            List<CourseProgramme> updatedModuleCourses = new ArrayList<>();
            if (m.getCourseProgrammes() != null && !m.getCourseProgrammes().contains(courseProgramme)) {
                updatedModuleCourses.addAll(m.getCourseProgrammes());
                updatedModuleCourses.add(courseProgramme);
                m.setCourseProgrammes(updatedModuleCourses);
            } else if (m.getCourseProgrammes() == null) {
                updatedModuleCourses.add(courseProgramme);
                m.setCourseProgrammes(updatedModuleCourses);
            }
        });
    }

    // Printing all course data
    private static void printCourseData() {
        if (!courses.isEmpty()) {
            // Printing Course data given a list of courses
            System.out.println("\n\nCOURSES:");
            courses.stream().forEach(course ->
            {
                System.out.println("\nCourse Name: " + course.getName() +
                        "\nAcademic Start Date: " + course.getAcademicStartDate() +
                        "\nAcademic End Date: " + course.getAcademicEndDate());
                if (course.getModules() != null) {
                    System.out.println("Modules: " + course.getModules().stream().map(Module::getName).collect(Collectors.toList()));
                } else {
                    System.out.println("Modules: NO REGISTERED MODULES");
                }
                if (course.getStudentsEnrolled() != null) {
                    System.out.println("Students: " + course.getStudentsEnrolled().stream().map(Student::getName).collect(Collectors.toList()));
                } else {
                    System.out.println("Students: NO REGISTERED STUDENTS");
                }

            });
        } else {
            System.out.println("NO REGISTERED COURSES");
        }
    }

    // Printing all module data
    private static void printModuleData() {
        if (!modules.isEmpty()) {
            // Printing Module data given a list of modules
            System.out.println("\n\nMODULES:");
            modules.stream().forEach(module ->
            {
                System.out.println("\nModule Name: " + module.getName() +
                        "\nID: " + module.getID());
                if (module.getStudentsEnrolled() != null) {
                    System.out.println("Students: " + module.getStudentsEnrolled().stream().map(Student::getName).collect(Collectors.toList()));
                } else {
                    System.out.println("Students: NO REGISTERED STUDENTS");
                }
                if (module.getCourseProgrammes() != null) {
                    System.out.println("Courses: " + module.getCourseProgrammes().stream().map(CourseProgramme::getName).collect(Collectors.toList()));
                } else {
                    System.out.println("Courses: NO REGISTERED COURSES");
                }

            });
        } else {
            System.out.println("NO REGISTERED MODULES");
        }
    }

    // Printing all student data
    private static void printStudentData() {
        if (!students.isEmpty()) {
            // Printing Module data given a list of modules
            System.out.println("\n\nSTUDENTS:");
            students.stream().forEach(student ->
            {
                System.out.println(
                        "\nStudent Name: " + student.getName() +
                                "\nAge: " + student.getAge() +
                                "\nDOB: " + student.getDOB() +
                                "\nID: " + student.getID() +
                                "\nUser Name: " + student.getUsername());
                if (student.getRegisteredModules() != null) {
                    System.out.println("Modules: " + student.getRegisteredModules().stream().map(Module::getName).collect(Collectors.toList()));
                } else {
                    System.out.println("Modules: NO REGISTERED MODULES");
                }
                if (student.getRegisteredCourses() != null) {
                    System.out.println("Courses: " + student.getRegisteredCourses().stream().map(CourseProgramme::getName).collect(Collectors.toList()));
                } else {
                    System.out.println("Courses: NO REGISTERED COURSES");
                }
            });
        } else {
            System.out.println("NO STUDENTS REGISTERED");
        }
    }
}
