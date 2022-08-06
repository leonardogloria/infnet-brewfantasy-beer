package br.com.brewfantasy.beer.controller;

import br.com.brewfantasy.beer.dto.LoginDTO;
import br.com.brewfantasy.beer.dto.TokenDTO;
import br.com.brewfantasy.beer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody  LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPass());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken(authenticate);
        TokenDTO dto = TokenDTO.builder().type("Bearer").token(token).build();
        return ResponseEntity.ok(dto);

    }
}
