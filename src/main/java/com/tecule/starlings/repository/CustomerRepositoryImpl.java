package com.tecule.starlings.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecule.starlings.model.Customer;

/**
 * the @Repository annotation means it's a Spring bean class, a bean will created by Spring application context, the
 * bean name is also specified.
 * 
 * @author xiangqian
 *
 */
@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
	/*
	 * ?
	 */
	@PersistenceContext
	private EntityManager entityManager;

	// use spring transactional annotation
	@Override
	@Transactional
	public Customer save(Customer customer) {
		entityManager.persist(customer);
		return customer;
	}
}
