package com.theironyard.invoicify.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.repositories.CompanyRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private CompanyRepository companyRepo; 
	
	public AdminController(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo; 
	}
	
	@GetMapping("/companies")
	//shows a list of companies sorted by name with the number of invoices for the company
	//have a form available to add a new company
	public ModelAndView showCompanies() {
		ModelAndView mv = new ModelAndView("/companies/companies");
		mv.addObject("companies", companyRepo.findAll());
		return mv;
	}
	
	//creates a new company via form and redirects to admin/companies and shows the full list of companies with new addition
	@PostMapping("/companies")
	public ModelAndView createCompanies(Company company) {
		ModelAndView mv = new ModelAndView();
		try {
			companyRepo.save(company);
			mv.setViewName("redirect:/admin/companies");
		} catch (DataIntegrityViolationException dive) {
			mv.setViewName("redirect:/admin/companies");
		}
		return mv;
	}
	
	
	
	
}
