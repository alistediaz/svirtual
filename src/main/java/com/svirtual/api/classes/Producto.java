package com.svirtual.api.classes;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Producto {
	@NotNull(message = "SKU cannot be null")
	@Min(value = 1000000, message = "SKU should not be less than 1000000")
	@Max(value = 99999999, message = "SKU should not be greater than 99999999")
	private @Id Long Sku;
	@NotBlank(message = "Name cannot be blank") 
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String Name;
	@NotBlank(message = "Brand cannot be blank") 
	@Size(min = 3, max = 50, message = "Brand must be between 3 and 50 characters")
	private String Brand;
	private String Size;
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price should not be less than 1")
	@Max(value = 99999999, message = "Price should not be greater than 99999999")
	private Long Price;
	@NotBlank(message = "Principal image cannot be blank")
	@Size(min = 13, max = 65000, message = "Principal Image must be valid")
	private String UrlImg;
	private ArrayList<String> UrlImgs;
	
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public Long getPrice() {
		return Price;
	}

	public void setPrice(Long price) {
		Price = price;
	}

	public String getUrlImg() {
		return UrlImg;
	}

	public void setUrlImg(String urlImg) {
		UrlImg = urlImg;
	}

	public ArrayList<String> getUrlImgs() {
		return UrlImgs;
	}

	public void setUrlImgs(ArrayList<String> urlImgs) {
		UrlImgs = urlImgs;
	}

	public Long getSku() {
		return Sku;
	}

	public void setSku(Long sku) {
		Sku = sku;
	}

	
	

}
