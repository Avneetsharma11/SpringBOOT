package com.example.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PersonDAO;
import com.example.model.Person;
import com.example.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired(required = true)
	private PersonDAO personDAO;

	
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override

	public void addPerson(Person p) throws Exception {
		if(p.getId() == 0){
			this.personDAO.addPerson(p);
		}else{
			this.personDAO.updatePerson(p);
		}
		
	}

	@Override

	public void updatePerson(Person p) throws Exception {
		this.personDAO.updatePerson(p);
	}

	@Override

	public List<Person> listPersons() throws Exception {
		return this.personDAO.listPersons();
	}

	@Override

	public Person getPersonById(int id) throws Exception {
		return this.personDAO.getPersonById(id);
	}

	@Override

	public void removePerson(int id) throws Exception {
		this.personDAO.removePerson(id);
	}

}
