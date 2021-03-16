package com.sagar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.Catagory;
import com.sagar.service.CatagoryService;

@RestController
@RequestMapping({ "/catagories" })
public class CatagoryController {
	@Autowired
	private CatagoryService catagoryService;

	@GetMapping
	public List<Catagory> getAllCatagories() {
		return catagoryService.getAllCatagories();
	}

	@PostMapping
	public Catagory saveCatagory(@RequestBody Catagory catagory) {
		return catagoryService.saveCatagory(catagory).get();
		// return getAllCatagories();
	}

	@DeleteMapping("/deleteMulti")
	public List<Catagory> deleteListofCatagorys(@RequestBody List<Catagory> list) {
		catagoryService.deleteMultipleCatagories(list);
		return getAllCatagories();
	}

	@PutMapping
	public List<Catagory> update(@RequestBody Catagory catagory) {
		catagoryService.updateCatagory(catagory);
		return getAllCatagories();
	}

	@DeleteMapping
	public List<Catagory> delete(@RequestParam("id") int id) {
		catagoryService.deleteCatagory(id);
		return getAllCatagories();
	}

	/*
	 * @PostMapping({ "/saveMulti" }) public List<Catagory>
	 * saveListofCatagorys(@RequestBody List<Catagory> list) {
	 * catagoryService.saveMultipleCatagories(list); return getAllCatagories(); }
	 */

	/*
	 * public List<Catagory> getAllCatagories1() {
	 * 
	 * return getAllCatagories();
	 * 
	 * //return catagoryService.CatagorysWithPageAndSort(pageIndex, sizeOfPage,
	 * sortingOrder, new String[] { sortBy }); }
	 */

	/*
	 * @DeleteMapping({ "/deleteAll" }) public String deleteAll() {
	 * catagoryService.deleteAllCatagories(); return "Erased All Catagorys"; }
	 */

//	@GetMapping({ "/pagintion/{index}/{sizeOfPage}" })
//	public List<Catagory> allCatagorysWithPage(@PathVariable int index, @PathVariable int sizeOfPage) {
//		return catagoryService.CatagorysWithPagination(index, sizeOfPage);
//	}

	/*
	 * @GetMapping({ "/findByAny/{searchKeyword}" }) public List<Catagory>
	 * findByAny(@PathVariable String searchKeyword) { return
	 * userService.findByAnytg(searchKeyword); }
	 */

//	@GetMapping({ "/findByFname/{fname}" })
//	public List<Catagory> findByFname(@PathVariable String fname) {
//		return userService.findByAnytg(fname);
//	}
//
//	@GetMapping("/findByAny/{any}")
//	public Catagory getBooksFromCatagoryName(@PathVariable String any) {
//		return userService.getCatagoryByAny(any);
//	}
//	@GetMapping("/findById/{id}")
//	public Catagory getBooksFromCatagoryId(@PathVariable int id) {
//		return userService.getCatagoryById(id);
//	}
}
