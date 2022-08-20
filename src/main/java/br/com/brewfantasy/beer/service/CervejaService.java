package br.com.brewfantasy.beer.service;

import br.com.brewfantasy.beer.model.Cerveja;

import java.util.List;

public interface CervejaService {
    List<Cerveja> findAll();
    List<Cerveja> top5();
    void atualizaTop5();
    Cerveja findById(Long id);
    void save(Cerveja cerveja);
    void delete(Long id);


}
