package br.com.brewfantasy.beer.service;

import br.com.brewfantasy.beer.model.NotaVO;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscalService {
    @Autowired
    RestTemplate restTemplate;
    @CircuitBreaker(name = "notaService", fallbackMethod = "notaFallBack")
    public NotaVO emiteNotaFiscalRapida(){
        return restTemplate.getForObject("http://NOTA-FISCAL/circuit", NotaVO.class);
    }
    public NotaVO notaFallBack(Exception ex){
        System.out.println(" Recovered from  " +  ex.getMessage().toString());
        return new NotaVO();
    }
    @Retry(name = "notaServiceRetry")
    public NotaVO emiteOutraNotaFiscal(Long id){
        return restTemplate.getForObject("http://NOTA-FISCAL/" + id, NotaVO.class);
    }

}
