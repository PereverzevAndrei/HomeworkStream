import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> warriorsList = persons.stream()
                .filter(g -> g.getSex() == Sex.MAN)
                .filter(a -> a.getAge() >= 18 && a.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(warriorsList.size());

        List<Person> workersList = persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && person.getSex() == Sex.WOMAN ? person.getAge() <= 60 : person.getAge() <= 65)
                .sorted(Comparator.comparing(p -> p.getFamily()))
                .collect(Collectors.toList());
        System.out.println(workersList.size());
        System.out.println(persons.size());

    }

}
