package br.com.brewfantasy.beer.controller;

import br.com.brewfantasy.beer.model.Cerveja;
import br.com.brewfantasy.beer.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class CervejaController {
    @Autowired
    CervejaService cervejaService;
    @GetMapping
    public ResponseEntity<List<Cerveja>> findAll(){
        List<Cerveja> all = cervejaService.findAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cerveja> findById(@PathVariable Long id){
        Cerveja byId = cervejaService.findById(id);
        return ResponseEntity.ok(byId);
    }
    @PostMapping
    public ResponseEntity<Map<String,String>> create(@RequestBody Cerveja cerveja){
        cervejaService.save(cerveja);
        return ResponseEntity.ok(Map.of("message","saved"));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,String>> update(@PathVariable Long id) {
        cervejaService.delete(id);
        return ResponseEntity.ok(Map.of("message","deleted"));
    }

}
