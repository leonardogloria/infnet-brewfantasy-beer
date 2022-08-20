package br.com.brewfantasy.beer.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "beers")
@Data
public class Cerveja implements Serializable {
    @Id
    private ObjectId _id;
    private Long id;
    private String abv;
    private String beer;
    private String brewery;
    @Field("brewery_id")
    private Long breweryId;
    private String ibu;
    private String label;
    private String ounces;
    private String style;

}