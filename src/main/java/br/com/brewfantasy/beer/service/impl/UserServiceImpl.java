package br.com.brewfantasy.beer.service.impl;

import br.com.brewfantasy.beer.model.Role;
import br.com.brewfantasy.beer.model.User;
import br.com.brewfantasy.beer.repository.UserRepository;
import br.com.brewfantasy.beer.service.RoleService;
import br.com.brewfantasy.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Override
    @CacheEvict(value="users", allEntries=true)
    public void create(User user) {
        String passEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passEncoded);
        Role byId = roleService.getById(2L);
        user.setRoles(Set.of(byId));
        this.userRepository.save(user);
    }

    @Override
    public Optional<User> loadByUserName(String username) {
        return userRepository.findUserByEmail(username);
    }

    @Override
    @Cacheable("users")
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }
}
