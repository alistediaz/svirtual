package com.svirtual.api.classes;

public class ProductoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductoException(Long sku) {
    super("Could not find employee " + sku);
  }
	

}
