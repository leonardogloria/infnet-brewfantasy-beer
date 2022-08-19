package br.com.brewfantasy.beer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoVO {
    private Long id;
    private List<Long> beers;
}
