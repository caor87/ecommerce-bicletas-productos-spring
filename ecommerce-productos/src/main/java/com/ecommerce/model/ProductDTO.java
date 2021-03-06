package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "productos")
public class ProductDTO {
	
	@Id
	private Integer id;
	private String name_pro;
	private String category;
	private String description;
	private String image;
	private Double unit_price;
	private int amount_prod;
	private String colour;
	private int cantidad;

}
