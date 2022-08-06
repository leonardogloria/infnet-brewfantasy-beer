package br.com.brewfantasy.beer.service;

import br.com.brewfantasy.beer.model.User;

import java.util.Optional;

public interface UserService {
    void create(User user);
    Optional<User> loadByUserName(String username);
    Optional<User> findById(Long id);


}
