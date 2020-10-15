import com.sun.tools.javac.util.List;
import org.joda.time.DateTime;

public class Main {

    // Creating students to populate modules

    Student s1 = new Student("Mary Ham", 20, "01/06/2000", 16365972L);
    Student s2 = new Student("John Smith", 22, "09/08/1998", 86739684L);
    Student s3 = new Student("Paul Baker", 21, "24/12/1998", 26542867L);
    Student s4 = new Student("Peter Black", 22, "03/04/1998", 29367418L);
    Student s5 = new Student("Amy Turner", 22, "28/05/1998", 52009569L);
    Student s6 = new Student("Zack Wheel", 20, "17/11/1999", 10376423L);
    Student s7 = new Student("Sarah Yale", 21, "21/01/1999", 85746277L);
    Student s8 = new Student("Michael Love", 23, "19/04/1997", 55493658L);
    Student s9 = new Student("Oran Apple", 22, "02/11/1997", 73953765L);
    Student s10 = new Student("Rachel Red", 22, "12/01/1998", 23498832L);

    // Creating modules to populate course programme

    Module m1 = new Module("Software Engineering", "CT417", List.of(s1, s2, s3, s4, s5));
    Module m2 = new Module("Machine Learning", "CT4101", List.of(s1, s2, s3, s4, s5, s6, s7, s10));
    Module m3 = new Module("Artificial Intelligence", "CT421", List.of(s1, s2, s3, s5, s6, s7, s10));
    Module m4 = new Module("Circuits", "E224", List.of(s6, s7, s8, s9, s10));
    Module m5 = new Module("Math", "E224", List.of(s6, s7, s8, s9, s10));

    // Creating Academic start and end date

    DateTime dt1 = new DateTime(2020, 9, 1, 10, 0);
    DateTime dt2 = new DateTime(2021, 6, 20, 16, 30);

    // Creating courses

    CourseProgramme cp1 = new CourseProgramme("CS & IT", List.of(m1, m2, m3), dt1, dt2);
    CourseProgramme cp2 = new CourseProgramme("ECE", List.of(m2, m3, m4, m5), dt1, dt2);



}
