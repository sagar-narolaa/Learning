package com.sagar.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sagar.entity.Catagory;

@Repository
public class CatagoryDaoCriteria {

	@Autowired
	EntityManager em;

	public Catagory getCatagoryById(int id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Catagory> cq = cb.createQuery(Catagory.class);
		Root<Catagory> catagory = cq.from(Catagory.class);
		cq.select(catagory);
		cq.where(cb.equal(catagory.get("id"), id));
		TypedQuery<Catagory> q = em.createQuery(cq);
		return q.getSingleResult();

	}



	public Catagory getCatagoryByAny(String any) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Catagory> cq = cb.createQuery(Catagory.class);
		Root<Catagory> catagory = cq.from(Catagory.class);

		ArrayList<Predicate> conditions = new ArrayList<>();
		conditions.add(cb.like(catagory.get("catagory"), "%" + any + "%"));


		cq.select(catagory);
		cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])));

		TypedQuery<Catagory> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
