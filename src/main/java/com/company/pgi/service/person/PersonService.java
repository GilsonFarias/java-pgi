package com.company.pgi.service.person;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.Person;
import com.company.pgi.repository.person.IPersonRepository;
import com.company.pgi.service.permissions.IPermissionsService;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository iPersonRepository;

    @Autowired
    private IPermissionsService iPermissionsService;

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Override
    public Person getPersonById(Long id) {
        iPermissionsService.ValidPermission("PEN104");

        var personOp = iPersonRepository.findById(id);
        if (personOp.isEmpty()) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com o ID: " + id);
        }
        return personOp.get();
    }

    @Override
    @Transactional
    public Person savePerson(Person person) {
        iPermissionsService.ValidPermission("PEN101");

        if (person == null)
            throw new ApiCustomException(HttpStatus.NOT_FOUND, 
                "Person not save");

        if(person.getId() != null){
            var p = iPersonRepository.findById(person.getId());
            if(p.isEmpty()){
                throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR, 
                    "Error:[Person not save]: Registro não encontrado.");
            }
        }

        try {
            return iPersonRepository.save(person);

        } catch (DataIntegrityViolationException e) {
        // Violação de integridade de dados
        throw new ApiCustomException(HttpStatus.CONFLICT,
                "Data integrity violation: " + e.getMostSpecificCause().getMessage());
        } catch (JpaSystemException e) {
        // Problemas com JPA
        throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error with the database: " + e.getMostSpecificCause().getMessage());
        } catch (ConstraintViolationException e) {
        // Violações de restrições
            throw new ApiCustomException(HttpStatus.BAD_REQUEST,
                "Constraint violation: " + e.getMessage());
        }catch (Exception e) {
        // Exceções não esperadas
            throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error:[Person not save]: " + e.getMessage());
        }
    }

    @Override
    public String deletePerson(Long id) {
        iPermissionsService.ValidPermission("PEN103");

        var p = iPersonRepository.findById(id);
        if(p.isEmpty()){
            throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error:[Person not save]: Registro não encontrado.");
        }

        try {
            iPersonRepository.deleteById(id);

            return "{ Record deleted }";
            
        } catch (Exception e) {
            //System.err.append(e.getMessage());
            //logger.error("[Gilson:]" + e.getMessage());
            throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Error:[Person not save]: ");// + e.getMessage());
        }
    }

    @Override
    public List<Person> getPresonList() {
        iPermissionsService.ValidPermission("PEN104");

        var personList = iPersonRepository.findAll();

        return personList;
    }
}
