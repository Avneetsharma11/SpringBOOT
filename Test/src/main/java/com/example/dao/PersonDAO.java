package com.example.dao;

import java.util.List;

import com.example.model.Person;

public interface PersonDAO {

	public void addPerson(Person p) throws Exception;

	public void updatePerson(Person p) throws Exception;

	public List<Person> listPersons() throws Exception;

	public Person getPersonById(int id) throws Exception;

	public void removePerson(int id) throws Exception;
}
