package com.sagar.service;

import java.util.List;
import java.util.Optional;

import com.sagar.entity.Catagory;

public interface CatagoryService {
	List<Catagory> getAllCatagories();

	Optional<Catagory> saveCatagory(Catagory Catagory);

	void updateCatagory(Catagory Catagory);

//	List<Catagory> CatagoriesWithPageAndSort(int pageIndex, int size, String order, String... sortBy);

	void deleteMultipleCatagories(List<Catagory> Catagories);

	void saveMultipleCatagories(List<Catagory> Catagories);

	List<Catagory> findByAnytg(String any);

//	List<Catagory> catagoriesWithPagination(int index, int size);
	
	Catagory getCatagoryByAny(String any);

	Catagory getCatagoryById(int id);

	void deleteAllCatagories();

	void deleteCatagory(int id);
}
