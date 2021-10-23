package com.example.demo.reprostory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Compte;

@Repository
public class CompteDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Compte c) {
		em.persist(c);
	}

	public List<Compte> all() {
		// ce n'est pas sql c'est jpa language howa orienter objet
		return em.createQuery("select c from Compte c", Compte.class).getResultList();

	}

	public Compte findbyid(Long l) {
		return em.find(Compte.class, l);
	}

	@Transactional
	public void delete(long rib) {
		em.remove(findbyid(rib));
	}

	@Transactional
	public void update(long rib) {

		Compte d = findbyid(rib);
		System.out.println(d);
		// em.persist(d);
		em.persist(d);
	}

}
