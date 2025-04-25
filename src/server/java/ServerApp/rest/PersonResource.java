package ServerApp.rest;
import ServerApp.service.PersonService;
import commonApp.model.Person;
import commonApp.util.PersonSortingOptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonResource {
    private final PersonService personService;
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }
    // TODO Part 1: Implement the specified endpoints here
    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        if (person.getId() == null) {
            return ResponseEntity.ok(personService.savePerson(person));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/persons/{personId}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person uP, @PathVariable("personId") UUID pId) {
        if (uP.getId().equals(pId)) {
            return ResponseEntity.ok(personService.savePerson(uP));
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/persons/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("personId") UUID ID) {
        personService.deletePerson(ID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(
            @RequestParam(value = "sortField", required = false) PersonSortingOptions.SortField sortField,
            @RequestParam(value = "sortingOrder", required = false) PersonSortingOptions.SortingOrder sortingOrder) {
        if (sortingOrder == null)
            sortingOrder = PersonSortingOptions.SortingOrder.ASCENDING;

        if (sortField == null)
            sortField = PersonSortingOptions.SortField.ID;

        return ResponseEntity.ok(personService.getAllPersons(new PersonSortingOptions(sortingOrder, sortField)));
    }
}
