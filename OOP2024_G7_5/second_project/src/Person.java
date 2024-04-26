import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Person {
    private String name;
    private LocalDate dateofBirth;
    private LocalDate dateofDeath;
    private Person mother;
    private Person father;

    public Person(String name, LocalDate dateofBirth, LocalDate dateofDeath, Person mother, Person father) {
        this.name = name;
        this.dateofBirth = dateofBirth;
        this.dateofDeath = dateofDeath;
        this.mother = mother;
        this.father = father;
    }

    public static List<Person> fromCsv(String path)
    {
        List<Person> people=new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null)
            {
                fromCsvLine(line,people);
            }
            return people;
        }
        catch (Exception e) {
            System.err.println("nie moge odczytac pliku");
            return null;
        }
    }


    public static void fromCsvLine(String line, List<Person> people)
    {
        try {
            String[] parts =line.split(",");
            String name = null;
            LocalDate dateofBirth=null;
            LocalDate dateofDeath=null;
            Person mother=null;
            Person father=null;
            int numofCols=parts.length;
            if(numofCols>0)
            {
                name=parts[0].trim();
            }
            if(numofCols>1)
            {
                String dob=parts[1].trim();
                if(!dob.isEmpty())
                {
                    dateofBirth=LocalDate.parse(dob, DateTimeFormatter.ofPattern("DD.MM.YYYY"));
                }
            }
            if(numofCols>2)
            {
                String dod=parts[2].trim();
                if(!dod.isEmpty())
                {
                    dateofDeath=LocalDate.parse(dod, DateTimeFormatter.ofPattern("DD.MM.YYYY"));
                }
            }
            if(dateofBirth!=null&&dateofDeath!=null && dateofDeath.isBefore(dateofBirth))
            {
                throw new NegativeLifespanException(name);
            }
            people.add(new Person(name,dateofBirth,dateofDeath,mother,father));
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("Person{" );
        sb.append("name='" + name + '\'');
        if(dateofBirth!=null)
        {
            sb.append(", dateofBirth=" + dateofBirth);
        }
        if(dateofDeath!=null)
        {
            sb.append(", dateofDeath=" + dateofDeath);
        }
        if(mother!=null)
        {
            sb.append(", mother=" + mother.name);
        }
        if(father!=null)
        {
            sb.append(", father=" + father.name);
        }
        sb.append('}');

        return sb.toString();
    }
}
