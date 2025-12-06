package com.ditutala.services;

import com.ditutala.exception.ResourceNotFoundException;
import com.ditutala.model.Person;
import com.ditutala.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class);

    @Autowired
    private final PersonRepository personRepository;

    public PersonServices(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }
    public Person findById(Long id) {
        logger.info("Finding person with id {}", id);
        return  personRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!" ) );
    }

    public List<Person> findAll() {
        logger.info("Finding all persons");
        return personRepository.findAll();
    }

    public Person save(Person person) {
        logger.info("Saving person {}", person);
        return  personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating person {}", person);
        Person entity = personRepository.findById(person.getId())
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!" ) );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return  personRepository.save(entity);
    }

    public void delete(Long Id) {
        logger.info("Deleting person {}", Id);
        Person entity =   personRepository.findById(Id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!" ) );
        personRepository.delete(entity);
    }


}
