package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Compte;
import com.example.demo.reprostory.CompteDao;

@Service
public class CompteService {
	private CompteDao da;

	@Autowired
	public CompteService(CompteDao da) {
		super();
		this.da = da;
	}

	public void add(Compte c) {
		da.save(c);
	}

	public List<Compte> findAll() {
		// ce n'est pas sql c'est jpa language howa orienter objet
		return da.all();
	}

	public Compte findById(long id) {
		return da.findbyid(id);
	}

	public void delelte(long rib) {
		da.delete(rib);
	}

	public void modifier(Long id) {
		da.update(id);
	}

}
