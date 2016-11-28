package com.example.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PersonDAO;
import com.example.model.Person;

@Transactional
@Repository
public class PersonDAOImpl implements PersonDAO {

	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(Person p) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(p);
			logger.info("Person Not saved , Person Details=" + p);
		} catch (HibernateException e) {
			throw e;
		}
	}

	@Override
	public void updatePerson(Person p) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.update(p);
			logger.info("Person updated successfully, Person Details=" + p);
		} catch (HibernateException e) {
			logger.info("Person Detail not updated , Person Details=" + p);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Person> personsList = session.createQuery("from Person").list();
			for (Person p : personsList) {
				logger.info("Person List::" + p);
			}
			return personsList;
		} catch (HibernateException e) {
			throw e;
		}
	}

	@Override
	public Person getPersonById(int id) {
		Person p = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			p = (Person) session.load(Person.class, new Integer(id));
			logger.info("Person loaded successfully, Person details=" + p);
			return p;
		} catch (HibernateException e) {
			logger.info("Person loaded successfully, Person details=" + p);
			throw e;
		}
	}

	@Override
	public void removePerson(int id) {
		Person p = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			p = (Person) session.load(Person.class, new Integer(id));
			if (null != p) {
				session.delete(p);
			}
			logger.info("Person deleted successfully, person details=" + p);
		} catch (HibernateException e) {
			logger.info("Person Not deleted , person details=" + p);
			throw e;
		}
	}

}
