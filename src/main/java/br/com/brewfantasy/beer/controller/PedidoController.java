package br.com.brewfantasy.beer.controller;

import br.com.brewfantasy.beer.model.ImpostoVO;
import br.com.brewfantasy.beer.model.NotaVO;
import br.com.brewfantasy.beer.model.PedidoVO;
import br.com.brewfantasy.beer.service.NotaFiscalService;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private static Logger log = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NotaFiscalService notaFiscalService;

    @PostMapping
    public void fechaPedido(@RequestBody PedidoVO pedidoVO){
            log.info("Starting beer method", pedidoVO);
            ImpostoVO impostoVO = restTemplate.postForObject("http://FINANCEIRO", pedidoVO, ImpostoVO.class);
            // NotaVO notaVO = notaFiscalService.emiteNotaFiscalRapida();
            // System.out.println(notaVO);
            NotaVO notaVO1 = notaFiscalService.emiteOutraNotaFiscal(pedidoVO.getId());
            System.out.println(notaVO1);

    }

}
