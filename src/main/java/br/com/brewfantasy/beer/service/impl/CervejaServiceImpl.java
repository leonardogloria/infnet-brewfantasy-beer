package br.com.brewfantasy.beer.service.impl;

import br.com.brewfantasy.beer.model.Cerveja;
import br.com.brewfantasy.beer.repository.CervejaRepository;
import br.com.brewfantasy.beer.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CervejaServiceImpl implements CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;
    @Override
    public List<Cerveja> findAll() {
        return this.cervejaRepository.findAll();
    }

    @Override
    public Cerveja findById(Long id) {
        return this.cervejaRepository.findCervejaById(id);
    }

    @Override
    public void save(Cerveja cerveja) {
        this.cervejaRepository.save(cerveja);
    }

    @Override
    public void delete(Long id) {
        this.cervejaRepository.deleteById(id);

    }
}
