package com.ditutala.controllers;

import com.ditutala.data.dto.PersonDTO;
import com.ditutala.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private final  PersonServices services;

    public PersonController(PersonServices services) {
        this.services = services;

    }

    @GetMapping( value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO findById(@PathVariable Long id) {
        return  services.findById(id);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> findAll() {
        return services.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return  services.save(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return   services.update(person);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id  ) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
