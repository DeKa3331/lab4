import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people=Person.fromCsv("C:\\Users\\Jakub\\IdeaProjects\\OOP2024_G7_5\\family.csv");
        for(Person person: people)
        {
            System.out.println(person);
        }
    }
}