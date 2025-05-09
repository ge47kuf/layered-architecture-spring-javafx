package ClientApp.controller;
import commonApp.model.Person;
import commonApp.util.PersonSortingOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class PersonController {
    private final WebClient webClient;
    private final List<Person> persons;
    public PersonController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.persons = new ArrayList<>();
    }
    public void addPerson(Person person, Consumer<List<Person>> personsConsumer) {
        // TODO Part 2: Make an http post request to the server
        webClient.post().uri("persons").bodyValue(person)
                .retrieve().bodyToMono(Person.class).onErrorStop()
                .subscribe(newPerson -> {
                    persons.add(person);
                    personsConsumer.accept(persons);
                });
    }
    public void updatePerson(Person person, Consumer<List<Person>> personsConsumer) {
        // TODO Part 2: Make an http put request to the server
        webClient.put().uri("persons/" + person.getId()).bodyValue(person)
                .retrieve().bodyToMono(Person.class).onErrorStop()
                .subscribe(newPerson -> {
                    persons.replaceAll(oldPerson -> {
                        if (oldPerson.getId().equals(newPerson.getId())) {
                            return newPerson;
                        } else {
                            return oldPerson;
                        }
                    });
                    personsConsumer.accept(persons);
                });
    }
    public void deletePerson(Person person, Consumer<List<Person>> personsConsumer) {
        // TODO Part 2: Make an http delete request to the server
        webClient.delete().uri("persons/" + person.getId())
                .retrieve().bodyToMono(Person.class).onErrorStop()
                .subscribe(x -> {
                    persons.remove(person);
                    personsConsumer.accept(persons);
                });
    }
    public void getAllPersons(PersonSortingOptions sortingOptions, Consumer<List<Person>> personsConsumer) {
        // TODO Part 2: Make an https get request to the server
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("persons")
                        .queryParam("sortField", sortingOptions.getSortField())
                        .queryParam("sortingOrder", sortingOptions.getSortingOrder())
                        .build())
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Person>>() {})
                .onErrorStop().subscribe(newPerson -> {
                    persons.clear();
                    persons.addAll(newPerson);
                    personsConsumer.accept(persons);
                });
    }
}
