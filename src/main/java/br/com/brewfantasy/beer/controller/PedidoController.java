package br.com.brewfantasy.beer.controller;

import br.com.brewfantasy.beer.model.ImpostoVO;
import br.com.brewfantasy.beer.model.PedidoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @PostMapping
    public void fechaPedido(@RequestBody PedidoVO pedidoVO){
        System.out.println(pedidoVO);
        RestTemplate restTemplate = new RestTemplate();
        ImpostoVO impostoVO = restTemplate.postForObject("http://localhost:8081", pedidoVO, ImpostoVO.class);
        System.out.println(impostoVO);

    }
}
