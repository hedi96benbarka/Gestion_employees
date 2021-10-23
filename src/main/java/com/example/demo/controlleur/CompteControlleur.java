package com.example.demo.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Compte;
import com.example.demo.service.CompteService;

@Controller
@RequestMapping("/compte")
public class CompteControlleur {
	final private CompteService compte_service;

	@Autowired
	public CompteControlleur(CompteService compte_service) {
		super();
		this.compte_service = compte_service;
	}

	@GetMapping("/all")
	public ModelAndView getAll(Model model) {
		ModelAndView m = new ModelAndView();
		List<Compte> liste = compte_service.findAll();
		m.addObject("comptes", compte_service.findAll());
		// model.addAttribute("liste", liste);
		m.setViewName("index");
		return m;
	}

	@GetMapping("/showNewCompteForm")
	public String showNewEmployeeForm(Model model, @RequestParam(value = "message", required = false) String message) {
		// create model attribute to bind form data
		model.addAttribute("message", message);
		Compte compte = new Compte();
		model.addAttribute("compte", compte);
		return "new_compte";
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "solde", required = true) float solde,
			@RequestParam(name = "client", required = true) String client, RedirectAttributes redirectAttributes) {
		Compte c = new Compte();
		c.setClient(client);
		c.setSolde(solde);
		redirectAttributes.addAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		System.out.println(redirectAttributes);
		if(c.getClient()=="" && c.getSolde()<0)
		{
			System.out.println("faild");
			return  "redirect:/compte/showNewCompteForm";
		}
		List<Compte> liste = compte_service.findAll();
	//	System.out.println(liste.contains(c)==false);
		/*if(liste.contains(c)==true)
		{
			redirectAttributes.addAttribute("message", "Failed");
			redirectAttributes.addFlashAttribute("message", "Failed");
			redirectAttributes.addFlashAttribute("alertClass", "alert-failed");
			return "redirect:/compte/all";
		}*/
		/*else
		{
			
		}*/
		compte_service.add(c);
		
		return "redirect:/compte/all";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model,
			@RequestParam(value = "message", required = false) String message2) {

		Compte c = compte_service.findById(id);
		model.addAttribute("message2", message2);
		// System.out.println(c);
		model.addAttribute("c", c);
		return "update_compte";
	}

	@PostMapping("/modifier")
	public String editCompte(@RequestParam(name = "rib", required = true) long rib,
			@RequestParam(name = "solde", required = true) float solde,
			@RequestParam(name = "client", required = true) String client, RedirectAttributes redirectAttributes) {

		Compte compteEditer = compte_service.findById(rib);
		compteEditer.setClient(client);
		compteEditer.setSolde(solde);

		compte_service.add(compteEditer);
		redirectAttributes.addAttribute("message2", "Success");
		redirectAttributes.addFlashAttribute("message2", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:/compte/all";

	}

	@GetMapping("/deleteCompte/{id}")
	public String deleteCmployee(@PathVariable(value = "id") long id,@RequestParam(value = "message", required = false) String message3,
			RedirectAttributes redirectAttributes, Model model) {
		Compte c = compte_service.findById(id);
		System.out.println(c);
		this.compte_service.delelte(id);
		model.addAttribute("message3", message3);
		redirectAttributes.addAttribute("message3", "Success");
		redirectAttributes.addFlashAttribute("message3", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/compte/all";
	}
	
	//bech ya3ref mayrj3ch html
	@ResponseBody
	@GetMapping("/delete-ajax")
	public void deleteajax(@RequestParam(name="rib", required = true) Long rib) {
		
		this.compte_service.delelte(rib);
		
		
	}
}
