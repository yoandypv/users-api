package com.sacavix.users.controller;

import com.sacavix.users.persistence.projections.PersonFullLocation;
import com.sacavix.users.persistence.projections.PersonLocation;
import com.sacavix.users.persistence.projections.PersonLocationDTO;
import com.sacavix.users.persistence.projections.PersonLocationDTO2;
import com.sacavix.users.persistence.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Tuple;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/interface_open")
    public PersonFullLocation getSampleInterfaceOpenProjection() {
        return this.personRepository.getPersonFullLocation(2L);
    }

    @GetMapping("/interface_closed")
    public PersonLocation getSampleInterfaceCloseProjection() {
        return this.personRepository.getPersonLocation(2L);
    }

    @GetMapping("/class_based")
    public PersonLocationDTO getSampleClassBasedProjection() {

        Tuple t = this.personRepository.getPersonLocationDTO(2L);
        return new PersonLocationDTO(t.get(0, String.class),
                t.get(1, String.class),t.get(2, String.class));
    }

    @GetMapping("/class_based_named_query")
    public PersonLocationDTO2 getSampleClassBasedNqProjection() {
        return this.personRepository.getPersonLocationDTO2(2L);
    }

    @GetMapping("/dynamically")
    public Object getSampleClassBasedDinamically() {
        return this.personRepository.getPersonLocationDynamically(2L, PersonLocation.class);
    }

}
