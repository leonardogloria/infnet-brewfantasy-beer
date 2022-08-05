package br.com.brewfantasy.beer.repository;

import br.com.brewfantasy.beer.model.Cerveja;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CervejaRepository extends MongoRepository<Cerveja,String> {
    Cerveja findCervejaById(Long id);
    void deleteById(Long id);
}
