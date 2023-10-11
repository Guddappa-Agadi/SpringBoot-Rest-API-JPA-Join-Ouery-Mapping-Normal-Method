package com.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository cr;

	@GetMapping("/get/JoinQuery")
	public List<Category> findCategoriesByProductName(String product_name) {
		return cr.findCategoriesByProductName(product_name);
	}

	@PostMapping("/add")
	public ResponseEntity<Category> saveAllData(@RequestBody Category c) {
		return new ResponseEntity<>(cr.save(c), HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Category>> getAllData() {
		List<Category> lst = cr.findAll();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Category> getAllDataById(@PathVariable("id") int category_id) {
		Optional<Category> lst = cr.findById(category_id);
		if (lst.isPresent()) {
			return new ResponseEntity<>(lst.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/get/{id}")
	public ResponseEntity<Category> updateAllDataById(@PathVariable("id") int category_id, @RequestBody Category c) {
		Optional<Category> lst = cr.findById(category_id);
		if (lst.isPresent()) {
			lst.get().setCategory_name(c.getCategory_name());
			lst.get().setCategory_stocks(c.getCategory_stocks());
			lst.get().setProduct(c.getProduct());
			return new ResponseEntity<>(lst.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@DeleteMapping("/get/{id}")
	public ResponseEntity<Category> deleteAllDataById(@PathVariable("id") int category_id) {
		Optional<Category> lst = cr.findById(category_id);
		if (lst.isPresent()) {
			cr.deleteById(category_id);
			return new ResponseEntity<>(lst.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping("/get/pages")
	public List<Category> getByPages(@RequestParam int page, @RequestParam int size, @RequestParam String field) {
		Pageable p = PageRequest.of(page, size, Direction.ASC, field);
		Page<Category> pa = cr.findAll(p);
		return pa.toList();
	}
}
