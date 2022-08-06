package br.com.brewfantasy.beer.controller;

import br.com.brewfantasy.beer.model.User;
import br.com.brewfantasy.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADM','ROLE_ROOT')")
    @PostMapping
    public ResponseEntity<Map<String,String>> create(@RequestBody User user){
        userService.create(user);
        return ResponseEntity.ok(Map.of("message", "Created"));

    }
}
