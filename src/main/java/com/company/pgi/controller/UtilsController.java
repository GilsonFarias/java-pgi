package com.company.pgi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.model.dto.AddSchemaDto;
import com.company.pgi.utils.CreateSchema;

@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    private CreateSchema createSchema;

    @PutMapping("/addSchema")
    public ResponseEntity<String> AddSchema( @RequestBody AddSchemaDto addSchemaDto) {
        
        var result = createSchema.addSquema(addSchemaDto);

        System.err.println(result.texto());

        if(result.sucesso()){
            return ResponseEntity.ok(result.texto());
        }
        else{
            throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR, result.texto());
        }

    }

    
}
