package com.svirtual.api.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.svirtual.api.classes.Producto;
import com.svirtual.api.classes.ProductoException;

@RestController
public class ProductoController {
	private final ProductoRepository repository;

	  ProductoController(ProductoRepository repository) {
	    this.repository = repository;
	  }
	  
	  @GetMapping("/productos")
	  List<Producto> all() {
	    return repository.findAll();
	  }
	  // end::get-aggregate-root[]

	  @PostMapping("/productos")
	  Producto newEmployee(@Valid @RequestBody Producto nuevoProd) {
	    return repository.save(nuevoProd);
	  }

	  // Single item
	  
	  @GetMapping("/productos/{sku}")
	  Producto one(@PathVariable Long sku) {
	    
	    return repository.findById(sku)
	      .orElseThrow(() -> new ProductoException(sku));
	  }

	  @PutMapping("/productos/{sku}")
	  Producto replaceEmployee(@Valid @RequestBody Producto nuevoProd, @PathVariable Long sku) {
	    
	    return repository.findById(sku)
	      .map(prod -> {
	        prod.setName(nuevoProd.getName());
	        prod.setBrand(nuevoProd.getBrand());
	        return repository.save(prod);
	      })
	      .orElseGet(() -> {
	        nuevoProd.setSku(sku);
	        return repository.save(nuevoProd);
	      });
	  }

	  @DeleteMapping("/productos/{sku}")
	  void deleteEmployee(@PathVariable Long sku) {
	    repository.deleteById(sku);
	  }
	  
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public Map<String, String> handleValidationExceptions(
	    MethodArgumentNotValidException ex) {
	      Map<String, String> errors = new HashMap<>();
	      ex.getBindingResult().getAllErrors().forEach((error) -> {
	          String fieldName = ((FieldError) error).getField();
	          String errorMessage = error.getDefaultMessage();
	          errors.put(fieldName, errorMessage);
	      });
	      return errors;
	  }
	
}
