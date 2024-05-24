import java.util.List;
import java.util.function.Function;
public class Main {

        public static void main(String[] args) {
            List<Person> people = Person.fromCsv("family.csv");

            Person.toBinaryFile("family.bin", people);
            List<Person> people2 = Person.fromBinaryFile("family.bin");
            for(Person p : people2){
                System.out.println(p.toString());
            }
            PlantUMLRunner.setPath("plantuml-1.2024.4.jar");
            String uml = Person.umlPeople(people2);
            PlantUMLRunner.generateUML(uml, "./", "peopleUML");

            List<Person> sorted = Person.deadSorted(people2);

            Person old = Person.oldest(people);
            System.out.println("--------------------------");
            for(Person p : sorted){
                System.out.println(p.toString());
            }
            System.out.println("Najstarsza osoba: ");
            System.out.println(old.toString());
            Function<String, String> postProcessYellow = str -> str + " #Yellow";
            Function<String, String> empty = str -> str;
            String uml2 = old.umlPersonData(postProcessYellow, person -> Person.deadSorted(people2).getFirst() == person);

            PlantUMLRunner.generateUML(uml2, "./", "old");
            System.out.println("-----------------");



        }

}
