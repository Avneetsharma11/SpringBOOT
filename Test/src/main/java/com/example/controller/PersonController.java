package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Person;
import com.example.service.PersonService;

@Controller
public class PersonController {

	private PersonService personService;

	@Autowired(required = true)

	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public String hello() {
		return "index";
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public @ResponseBody List<Person> listPersons() {
		try {
			return this.personService.listPersons();
		} catch (Exception e) {
			return null;

		}

	}

	// For add and update person both

	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPerson(@RequestBody Person p) {
		try {
			this.personService.addPerson(p);
			return "redirect:/persons";
		} catch (Exception e) {
			return "redirect:/app";
		}

	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePerson(@RequestParam("id") int id) {
		try {
			this.personService.removePerson(id);
			return "index";
		} catch (Exception e) {
			return "redirect:/app";
		}

	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("person", this.personService.getPersonById(id));
			model.addAttribute("listPersons", this.personService.listPersons());
			return "person";
		} catch (Exception e) {
			return "redirect:/app";
		}

	}

}
