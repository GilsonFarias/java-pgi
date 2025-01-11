package com.company.pgi.service.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pgi.dto.RequestBase;
import com.company.pgi.model.ApiError;
import com.company.pgi.model.Person;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.repository.person.IPersonRepository;
import com.company.pgi.service.permissions.IPermissionsService;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository iPersonRepository;

    @Autowired
    private IPermissionsService iPermissionsService;

    @Override
    public Optional<Person> getPersonById(Long id) {
        return iPersonRepository.findById(id);
    }

    @Override
    public ResponseBase<Person> savePerson(Person person) {
        ResponseBase<Person> responseBase = new ResponseBase<>();
        responseBase.setApiError(new ArrayList<>());
        try {
            // keyCode: "PEN101", Incluir pessoa"
            boolean permission = iPermissionsService.ValidPermission("PEN101");

            if (!permission) {
                throw new IllegalAccessException("Permissão negada para executar esta ação.");
            }

            Person rs = iPersonRepository.save(person);

            //responseBase.setData(rs);

            return responseBase;

        } catch (IllegalAccessException e) {
            responseBase.setData(null);
            responseBase.setStatusCode("404");
            responseBase.setMessage(e.getMessage());
            responseBase.getApiError().add(new ApiError(404, e.getMessage()));
            return responseBase;
        }
    }

    @Override
    public void deletePerson(Long id) {
        iPersonRepository.deleteById(id);
    }

    @Override
    public ResponseBase<Person> getPresonList(RequestBase<Person> requestBase) {
        ResponseBase<Person> responseBase = new ResponseBase<>();
        responseBase.setApiError(new ArrayList<>());

        try {
            // keyCode: "PEN104", Visualizar Pessoa"
            boolean permission = iPermissionsService.ValidPermission("PEN104");

            if (!permission) {
                throw new IllegalAccessException("Permissão negada para executar esta ação.");
            }

            List<Person> personList = iPersonRepository.findAll();

            responseBase.setData(personList);
            responseBase.setStatusCode("200");
            responseBase.setMessage("ok");

            return responseBase;

        } catch (IllegalAccessException e) {
            responseBase.setData(null);
            responseBase.setStatusCode("404");
            responseBase.setMessage(e.getMessage());
            responseBase.getApiError().add(new ApiError(404, e.getMessage()));
            return responseBase;
        }

    }
}
