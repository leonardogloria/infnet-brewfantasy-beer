package br.com.brewfantasy.beer.controller;

import br.com.brewfantasy.beer.model.ImpostoVO;
import br.com.brewfantasy.beer.model.PedidoVO;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
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
    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public void fechaPedido(@RequestBody PedidoVO pedidoVO){
        System.out.println(pedidoVO);
        ImpostoVO impostoVO = restTemplate.postForObject("http://FINANCEIRO", pedidoVO, ImpostoVO.class);
        System.out.println(impostoVO);

    }

}
