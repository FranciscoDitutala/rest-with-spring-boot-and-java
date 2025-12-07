package com.ditutala.services;
import com.ditutala.data.dto.PersonDTO;
import com.ditutala.exception.ResourceNotFoundException;
import static com.ditutala.mapper.ObjectMapper.parserObject;
import static com.ditutala.mapper.ObjectMapper.parserObjects;
import com.ditutala.model.Person;
import com.ditutala.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class);

    @Autowired
    private final PersonRepository personRepository;

    public PersonServices(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }
    public PersonDTO findById(Long id) {
        logger.info("Finding person with id {}", id);
        var toReturn = personRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!" ) );
        return  parserObject(toReturn, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons");
        return parserObjects(personRepository.findAll(), PersonDTO.class ) ;
    }

    public PersonDTO save(PersonDTO person) {
        logger.info("Saving person {}", person);
        return  parserObject(personRepository.save(parserObject(person, Person.class)), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating person {}", person);
        Person entity = personRepository.findById(person.getId())
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!" ) );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parserObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete(Long Id) {
        logger.info("Deleting person {}", Id);
        Person entity =   personRepository.findById(Id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!" ) );
        personRepository.delete(entity);
    }
}
