package br.com.brewfantasy.beer.service.impl;

import br.com.brewfantasy.beer.model.Cerveja;
import br.com.brewfantasy.beer.repository.CervejaRepository;
import br.com.brewfantasy.beer.service.CervejaService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CervejaServiceImpl implements CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;
    @Override
    public List<Cerveja> findAll() {
        List<Cerveja> all = this.cervejaRepository.findAll();
        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        Gauge gauge = Gauge
                .builder("beers_size", all::size)
                .register(Metrics.globalRegistry);
        return all;
    }

    @Override
    @Cacheable(cacheNames = "beers.top5")
    public List<Cerveja> top5() {
        List<Cerveja> all = this.cervejaRepository.findAll();
        ArrayList<Cerveja> list = new ArrayList<>(all.subList(0, 5));
        return list;
    }

    @Override
    @CacheEvict(cacheNames = "beers.top5", allEntries = true)
    public void atualizaTop5() {
        System.out.println("Atualizando o cache");
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
