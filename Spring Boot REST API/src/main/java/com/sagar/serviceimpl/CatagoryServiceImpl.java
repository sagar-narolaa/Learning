package com.sagar.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.cache.SimpleCache;
import com.sagar.dao.CatagoryDao;
import com.sagar.dao.CatagoryDaoCriteria;
import com.sagar.entity.Catagory;
import com.sagar.service.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	CatagoryDao catagoryDao;
	@Autowired
	CatagoryDaoCriteria catagoryDao1;

	@Autowired
	SimpleCache<Integer, Catagory> cache;
	
	public CatagoryServiceImpl() {
	}
	
	@Override	
	public List<Catagory> getAllCatagories() {
		//List<Catagory> catagories = cache.getAll();
		return  catagoryDao.findAll();
	}
	
	@Override
	public Optional<Catagory> saveCatagory(Catagory catagory) {
		catagoryDao.save(catagory);
		Catagory catagoryy=catagoryDao.findByName(catagory.getName()).get();
		cache.put(catagoryy.getId(),catagoryy);
		catagoryDao.save(catagory);
		return Optional.ofNullable(cache.get(catagoryy.getId()).get());
	}
	
	@Override
	public void updateCatagory(Catagory catagory) {
		cache.put(catagory.getId(), catagory);
		catagoryDao.save(catagory);
	}
	
	@Override
	public void deleteCatagory(int id) {
		cache.remove(id);
		catagoryDao.deleteById(id);
	}

	@Override
	public void deleteAllCatagories() {
		cache.removeAll();
		catagoryDao.deleteAllInBatch();
	}

	@Override
	public Catagory getCatagoryById (int id) {
		return cache.get(id).get();
	}
	
	@Override
	public void saveMultipleCatagories(List<Catagory> catagories) {
		Map<Integer, Catagory> map = catagories.stream().collect(Collectors.toMap(Catagory::getId, (catagory) -> catagory));
		catagories.forEach((catagory)->cache.put(catagory.getId(), catagory));
		
		catagoryDao.saveAll(catagories);
	}
	
	@Override
	public void deleteMultipleCatagories(List<Catagory> catagories) {
		catagoryDao.deleteInBatch(catagories);
	}
	
//	@Override
//	public List<Catagory> catagoriesWithPagination(int index, int size) {
//		Pageable catagoriesWithLimit = PageRequest.of(index, size);
//		Page<Catagory> catagories = catagoryDao.findAll(catagoriesWithLimit);
//		return catagories.getContent();
//	}
//	
//	@Override
//	public List<Catagory> catagoriesWithPageAndSort(int pageIndex, int size, String order, String... sortBy) {
//		Pageable catagoriesWithSort = order.toUpperCase().equals("ASC")
//				? PageRequest.of(pageIndex - 1, size, Sort.by(sortBy).ascending())
//				: PageRequest.of(pageIndex - 1, size, Sort.by(sortBy).descending());
//
//		List<Catagory> catagories = catagoryDao.findAll(catagoriesWithSort).getContent();
//		return catagories;
//	}
	
	@Override
	public List<Catagory> findByAnytg(String param) {
		List<Catagory> catagories = catagoryDao.findByAny(param);
		return catagories;
	}


	@Override
	public Catagory getCatagoryByAny(String any) {

		return catagoryDao1.getCatagoryByAny(any);
	}



	/*
	 * @Override public CatagoryDetails loadCatagoryByCatagoryname(String username) throws
	 * CatagorynameNotFoundException { CatagoryDetailss catagory=
	 * catagoryDao1.findByCatagoryName(username).get(0); return new CatagoryDetail(catagory); }
	 */
}