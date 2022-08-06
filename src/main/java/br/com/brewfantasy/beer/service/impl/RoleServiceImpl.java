package br.com.brewfantasy.beer.service.impl;

import br.com.brewfantasy.beer.model.Role;
import br.com.brewfantasy.beer.repository.RoleRepository;
import br.com.brewfantasy.beer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;
    @Override
    public Role getById(Long id) {
        Optional<Role> byId = this.repository.findById(id);
        return byId.get();
    }
}
