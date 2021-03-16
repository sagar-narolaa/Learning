package com.sagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sagar.Utils.StringUtils;
import com.sagar.entity.Product;

@Repository
public class ProductDaoCriteria {
	private final String DEFAULT_SORT_BY = "id";

	@Autowired
	private EntityManager em;

	public List<Product> getproductsWithSearch(String sortBy, String sortOrder, int pageSize, String searchString,
			int catagoryId, int pageIndex) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		Root<Product> product = cq.from(Product.class);

		Path<Integer> catagory_Id = product.join("catagory").get("id");

		cq.select(product);

		if (catagoryId == 0) {

			cq.orderBy(cb.asc(product.get(sortBy)));

		} else if (StringUtils.check(searchString, sortBy)) {

			cq.where(cb.equal(catagory_Id, catagoryId));

					if (sortOrder.equals("ASC")) 
						cq.orderBy(cb.asc(product.get(DEFAULT_SORT_BY)));
					 else 
						cq.orderBy(cb.desc(product.get(DEFAULT_SORT_BY)));
			

		} else {

			ArrayList<Predicate> conditions = new ArrayList<>();
			conditions.add(cb.like(product.get("name"), "%" + searchString + "%"));
			conditions.add(cb.like(product.get("description"), "%" + searchString + "%"));

			Predicate findByCatagoryId = cb.equal(catagory_Id, catagoryId);
			
			cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])), cb.and(findByCatagoryId));

			if (sortOrder.equals("ASC")) 
				 cq.orderBy(cb.asc(product.get(sortBy)));
			 else 
				 cq.orderBy(cb.desc(product.get(sortBy)));
			
		}

		TypedQuery<Product> query = em.createQuery(cq);

		return query.setFirstResult(pageSize * (pageIndex - 1)).setMaxResults(pageSize).getResultList();
	}

}
