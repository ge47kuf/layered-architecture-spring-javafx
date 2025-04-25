package ServerApp.service;

import commonApp.model.Person;
import commonApp.util.PersonSortingOptions;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class PersonService {
    // do not change this
    private final List<Person> persons;
    public PersonService() {
        this.persons = new ArrayList<>();
    }

    public Person savePerson(Person person) {
        var optionalPerson = persons.stream().filter(existingPerson -> existingPerson
                .getId().equals(person.getId())).findFirst();
        if (optionalPerson.isEmpty()) {
            person.setId(UUID.randomUUID());
            persons.add(person);
            return person;
        } else {
            var existingPerson = optionalPerson.get();
            existingPerson.setFirstName(person.getFirstName());
            existingPerson.setLastName(person.getLastName());
            existingPerson.setBirthday(person.getBirthday());
            return existingPerson;
        }
    }
    public void deletePerson(UUID personId) {
        this.persons.removeIf(person -> person.getId().equals(personId));
    }
    public List<Person> getAllPersons(PersonSortingOptions sortingOptions) {
        // TODO Part 3: Add sorting here
        List<Person> personList = new ArrayList<>(this.persons);
        if (sortingOptions.getSortingOrder() == PersonSortingOptions.SortingOrder.DESCENDING) {
            switch (sortingOptions.getSortField()) {
                case BIRTHDAY -> personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getBirthday)));
                case FIRST_NAME -> personList
                        .sort(Collections.reverseOrder(Comparator.comparing(Person::getFirstName)));
                case LAST_NAME -> personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getLastName)));
                default -> personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getId)));
            }
        } else {
            switch (sortingOptions.getSortField()) {
                case BIRTHDAY -> personList.sort(Comparator.comparing(Person::getBirthday));
                case LAST_NAME -> personList.sort(Comparator.comparing(Person::getLastName));
                case FIRST_NAME -> personList.sort(Comparator.comparing(Person::getFirstName));
                default -> personList.sort(Comparator.comparing(Person::getId));
            }
        }
        /*
        if (sortingOptions.getSortingOrder() == PersonSortingOptions.SortingOrder.ASCENDING) {
            if (PersonSortingOptions.SortField.ID == sortingOptions.getSortField())
                personList.sort(Comparator.comparing(Person::getId));
            if (PersonSortingOptions.SortField.BIRTHDAY == sortingOptions.getSortField())
                personList.sort(Comparator.comparing(Person::getBirthday));
            if (PersonSortingOptions.SortField.LAST_NAME == sortingOptions.getSortField())
                personList.sort(Comparator.comparing(Person::getLastName));
            if (PersonSortingOptions.SortField.FIRST_NAME == sortingOptions.getSortField())
                personList.sort(Comparator.comparing(Person::getFirstName));
        } else {
            if (PersonSortingOptions.SortField.ID == sortingOptions.getSortField())
                personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getId)));
            if (PersonSortingOptions.SortField.FIRST_NAME == sortingOptions.getSortField())
                personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getFirstName)));
            if (PersonSortingOptions.SortField.LAST_NAME == sortingOptions.getSortField())
                personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getLastName)));
            if (PersonSortingOptions.SortField.BIRTHDAY == sortingOptions.getSortField())
                personList.sort(Collections.reverseOrder(Comparator.comparing(Person::getBirthday)));
             */
        return personList;
    }
}
