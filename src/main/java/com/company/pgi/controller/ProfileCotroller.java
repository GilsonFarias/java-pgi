package com.company.pgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Profile;
import com.company.pgi.service.profile.IProfileService;

@RestController
@RequestMapping("api/profile")
public class ProfileCotroller {

    @Autowired
    private IProfileService iProfileService;

    @GetMapping("/list")
    public ResponseEntity<List<Profile>> getMethodName() {

        var list = iProfileService.getProfilesList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {

        var profile = iProfileService.getProfileById(id);

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/listByCompany/{id_company}")
    public ResponseEntity<List<Profile>> getMethodName(@PathVariable Long id_company) {

        var list = iProfileService.getProfilesListByCompany(id_company);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/insert")
    public ResponseEntity<Profile> insertProfile(@RequestBody Profile profile) {

        var profileResult = iProfileService.saveProfile(profile);

        return ResponseEntity.ok(profileResult);
    }
}
