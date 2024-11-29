import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentStream {

    public static void main(String[] args) {
        List<Student> studentList = Stream.of(
                        new Student(1, "Rohit", 30, "Male", "Mechanical Engineering", "Mumbai", 122, Arrays.asList("+912632632782", "+1673434729929")),
                        new Student(2, "Pulkit", 56, "Male", "Computer Engineering", "Delhi", 67, Arrays.asList("+912632632762", "+1673434723929")),
                        new Student(3, "Ankit", 25, "Female", "Mechanical Engineering", "Kerala", 164, Arrays.asList("+912632633882", "+1673434709929")),
                        new Student(4, "Satish Ray", 30, "Male", "Mechanical Engineering", "Kerala", 26, Arrays.asList("+9126325832782", "+1671434729929")),
                        new Student(5, "Roshan", 23, "Male", "Biotech Engineering", "Mumbai", 12, Arrays.asList("+012632632782")),
                        new Student(6, "Chetan", 24, "Male", "Mechanical Engineering", "Karnataka", 90, Arrays.asList("+9126254632782", "+16736784729929")),
                        new Student(7, "Arun", 26, "Male", "Electronics Engineering", "Karnataka", 324, Arrays.asList("+912632632782", "+1671234729929")),
                        new Student(8, "Nam", 31, "Male", "Computer Engineering", "Karnataka", 433, Arrays.asList("+9126326355782", "+1673434729929")),
                        new Student(9, "Sonu", 27, "Female", "Computer Engineering", "Karnataka", 7, Arrays.asList("+9126398932782", "+16563434729929", "+5673434729929")),
                        new Student(10, "Shubham", 26, "Male", "Instrumentation Engineering", "Mumbai", 98, Arrays.asList("+912632646482", "+16734323229929")))
                .collect(Collectors.toList());

        Predicate<Integer> p = x -> x > 50 && x < 100;

        // 1. Find the list of students whose rank is in between 50 and 100
        System.out.println();
        studentList.stream().filter(x -> p.test(x.getRank())).forEach(System.out::println);

        //2. Find the Students who stays in Karnataka and sort them by their names
        System.out.println();
        studentList.stream().filter(x -> x.getCity().equalsIgnoreCase("karnataka"))
                .sorted(Comparator.comparing(Student::getFirstName)).forEach(System.out::println);

        // 3. Find all departments names
        System.out.println();
        studentList.stream().map(Student::getDept).distinct().forEach(System.out::println);

        //4.  Find all the contact numbers
        System.out.println();
            studentList.stream().flatMap(x -> x.getContacts().stream()).distinct().forEach(System.out::println);

        //5.  Group The Student By Department Names
        System.out.println();
        studentList.stream().collect(Collectors.groupingBy(Student::getDept)).entrySet().forEach(System.out::println);

        //6. Find the department who is having maximum number of students
        System.out.println();
        Map.Entry<String, Long> result = studentList.stream().collect(Collectors.groupingBy(Student::getDept, Collectors.counting()))
                .entrySet()
                .stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(result);

        //7. Find the average age of male and female students
        System.out.println();
        studentList.stream().collect(Collectors.groupingBy(Student::getDept, Collectors.averagingInt(Student::getAge)))
                .entrySet().forEach(System.out::println);

        //8. Find the highest rank in each department
        System.out.println();
        studentList.stream().collect(Collectors.groupingBy(Student::getDept, Collectors.minBy(Comparator.comparingInt(Student::getRank))))
                .entrySet().forEach(x -> System.out.println(x.getKey() + "\n" + x.getValue().get()));

        //9 .Find the student who has second rank
        System.out.println();
        Student s = studentList.stream().sorted(Comparator.comparingInt(Student::getRank)).skip(1).findFirst().get();
        System.out.println(s);

    }
}
