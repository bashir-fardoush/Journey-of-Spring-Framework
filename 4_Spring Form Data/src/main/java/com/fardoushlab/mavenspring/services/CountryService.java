package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fardoushlab.mavenspring.config.HibernateConfig;
import com.fardoushlab.mavenspring.exception.ResourceAlreadyExistsException;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;
import com.fardoushlab.mavenspring.model.Country;
import com.fardoushlab.mavenspring.model.Course;
import com.mysql.cj.log.Log;

@Service
public class CountryService {


	private HibernateConfig hibernateConfig;	
	
	public CountryService(HibernateConfig hibernateConfig) {
		super();
		
		this.hibernateConfig = hibernateConfig;
		
	
	}
	
	
	private void addCountry(String countryName) {
		
		var countryObj = new Country();
		countryObj.setCountryName(countryName);
		countryObj.setCountryCode(countryName.substring(0, 3).trim().toUpperCase());
		addCountry(countryObj);
		
	}
	

	@Transactional
	public void addCountry(Country country) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		country.setCountryCode(country.getCountryCode().trim().toUpperCase());
		Random random = new Random();
		int randomInteger = random.nextInt();
		country.setId(randomInteger);
		
		try {			
			session.save(country);
			transection.commit();
			
		}catch(HibernateException e) {
			if(transection != null) {
				transection.rollback();	
			}
			e.printStackTrace();		
			
		}finally{
			session.close();	
		}	
		
		

		
	}
	

	public void checkCountryInDb(Country c) {

		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		var query = session.getEntityManagerFactory().createEntityManager()
			.createQuery("select c from com.fardoushlab.mavenspring.model.Country c where countryCode:= countryCode",Country.class);
		query.setParameter("countryCode", c.getCountryCode());
		
		if(query.getResultStream().findAny().isPresent()) {
		
			transection.rollback();
			session.close();
			throw new ResourceAlreadyExistsException("Country Already exists");
		}
		
		transection.commit();		
		session.close();
		
	}
	
	@Transactional
	public void saveEditedCountry(Country c) {
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
			
		/*var query = session.getEntityManagerFactory().createEntityManager()
				.createQuery("update com.fardoushlab.mavenspring.model.Country c set c.countryName =:cName where countryCode=:cCode", Country.class)
				.setParameter("cCode", c.getCountryCode())
				.setParameter("cName", c.getCountryName());*/
		
		var query = session.createQuery("update Country  set countryName =:cName where countryCode=:cCode");
		query.setParameter("cCode", c.getCountryCode());
		query.setParameter("cName", c.getCountryName());
		
		try {
		int val = query.executeUpdate();
		
			transection.commit();			
		}catch(HibernateException e) {
			
			if(transection!= null) {
				transection.rollback();
				session.close();
			}			
			e.printStackTrace();
		}finally {
			session.close();
		}

	}
	
	public Country getCountryByCode(String countryCode) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		var query = session.getEntityManagerFactory().createEntityManager()
				.createQuery("select c from com.fardoushlab.mavenspring.model.Country c where countryCode =: countryCode",Country.class);
		
		query.setParameter("countryCode", countryCode);
		
		var countryList = new ArrayList<Country>();
		
		try {
			 countryList = (ArrayList<Country>) query.getResultList();
		}catch(HibernateException e) {
			
			if(transection!= null ) {
				transection.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		
		 
	//	 query.getSingleResult()
		
		if(countryList.size() <= 0) {
			throw  new ResourceAlreadyExistsException("No country found");
		}
		return countryList.get(0);
		
	
				
	}
	
	public void deleteCountryByCode(String countryCode) {		
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
	/*var query = session.getEntityManagerFactory().createEntityManager()
			.createQuery("delete c from com.fardoushlab.mavenspring.model.Country"
					+ " c where countryCode:= countryCode",Country.class)
			.setParameter("countryCode", countryCode);*/
	
		var query = session.createQuery("delete Country  where countryCode=:cCode");
		query.setParameter("cCode", countryCode);
	
		try {			
			query.executeUpdate();
			 
		}catch(HibernateException e) {
			
			if(transection!= null ) {
				transection.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}
	
	
		
	}
	
	 
	public List<Country> getAll() {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		var query = session.getEntityManagerFactory()
				.createEntityManager()
				.createQuery("select c from com.fardoushlab.mavenspring.model.Country c", Country.class);
		var countries = new ArrayList<Country>();
		try {
			countries = (ArrayList<Country>) query.getResultList();
			
		}catch(HibernateException e) {
			if(transection != null) {
				transection.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return countries;
	
	}
	

	

}
/*******************************JDBC CONNECTION COUNTRY SERVICE************************/
/*

private final JdbcTemplate jdbcTemplate;
	
public CountryService(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
	String createTable = "create table if not exists country(id bigint not null AUTO_INCREMENT PRIMARY KEY, country_code varchar(5), country_name varchar(100) );";
	this.jdbcTemplate.execute(createTable);
	
}


private void addCountry(String countryName) {
	
	var countryObj = new Country();
	countryObj.setCountryName(countryName);
	countryObj.setCountryCode(countryName.substring(0, 3).trim().toUpperCase());
	addCountry(countryObj);
	
}



public void addCountry(Country country) {
	checkCountryInDb(country);
	country.setCountryCode(country.getCountryCode().trim().toUpperCase());
	
	var countryAddQuery = "insert into country(country_code,country_name) values(?, ?)";		
	int resultset = jdbcTemplate.update(countryAddQuery, country.getCountryCode(), country.getCountryName());
	
	if(resultset < 1) {	
		throw new RuntimeException("Failed to create country");
	}

	
}


public void checkCountryInDb(Country c) {
	
	var checkCountryQuery ="select count(*) as count from country c where c.country_code = ?; ";		
	Map<String, Object> map = jdbcTemplate.queryForMap(checkCountryQuery, c.getCountryCode());
	
	if(Integer.parseInt(map.get("count").toString()) > 0  ) {
		throw new ResourceAlreadyExistsException("Country already exists");
	}		
	
}

public void saveEditedCountry(Country c) {
	var updateQuery = "update country set country_name = ? where country_code= ?; ";		
	int result = jdbcTemplate.update(updateQuery, c.getCountryName(), c.getCountryCode());
	
	if(result < 1) {
		throw new RuntimeException("Unable to update country");
		
	}	

}

public Country getCountryByCode(String countryCode) {
	
	var selectQuery = "select * from country where country_code = ? limit 1;";
		
	var countries  = new ArrayList<Country>();		
	jdbcTemplate.queryForList(selectQuery, countryCode).stream().forEach(row->{
		
		var country = new Country();
		country.setId(Integer.parseInt(row.get("id").toString()));
		country.setCountryCode(row.get("country_code").toString());
		country.setCountryName(row.get("country_name").toString());
		
		countries.add(country);
		
	});
	

	if(countries.size() <= 0) {
		throw  new ResourceAlreadyExistsException("No country found");
	}
	
	return countries.get(0);
			
}

public void deleteCountryByCode(String countryCode) {		
	
	var deleteQuery = "delete from country where country_code = ?;";		
	int result  = jdbcTemplate.update(deleteQuery, countryCode);
	
	if(result < 1) {
		
		throw new RuntimeException("Unable to delete this country");
	}
	
	
}

 
public List<Country> getAll() {
	
	var selectAllQuery = "select * from country;";
	var countries  = new ArrayList<Country>();
	
	jdbcTemplate.queryForList(selectAllQuery).stream().forEach(row->{
		
		var country = new Country();
		country.setId(Integer.parseInt(row.get("id").toString()));
		country.setCountryCode(row.get("country_code").toString());
		country.setCountryName(row.get("country_name").toString());
		
		countries.add(country);
		
	});
	
	
	return countries;
}

*/


/************************************COUNTRY SERVICE TO INITIALLY SAVE COUNTRIES IN LIST************************************************/


/*
 	
 
	private List<Country> countries = new ArrayList<Country>();
	private static final String[] COUNTRY_NAMES = new String[] {"Bangladesh","India","Pakistan","Japan"};
	
		public CountryService() {
		super();
		
		final AtomicReference<Integer> atomicId = new AtomicReference<Integer>();
		atomicId.set(0);
		
		Stream.of(COUNTRY_NAMES).forEach(country->{
			
			addCountry(country);
				
		});
		
	}
	
	

	
	
	private void addCountry(String countryName) {
		
		var countryObj = new Country();
		
		countryObj.setId(countries.size()+1);
		countryObj.setCountryName(countryName);
		countryObj.setCountryCode(countryName.substring(0, 3).trim().toUpperCase());
		addCountry( countryObj)
		
		
	}
	public void addCountry(Country country) {
		checkCountryInList(country);
		country.setCountryCode(country.getCountryCode().trim().toUpperCase());
		country.setId(countries.size()+1 );
		countries.add(country);
	}
	
 
 
 	private void checkCountryInList(Country c) 
		
		if (countries
				.stream()
				.filter(country-> country.getCountryCode().equals(c.getCountryCode().trim().toUpperCase()))
				.findAny().isPresent()) {
			
			throw new ResourceAlreadyExistsException("Country exists in list");
		}
		
	
	}
	
 
 public void saveEditedCountry(Country c) {
		
		countries
		.stream()
		.filter(country-> country.getCountryCode().equals(c.getCountryCode()))
		.findAny().get().setCountryName(c.getCountryName());
		

	}
	
 public Country getCountryByCode(String countryCode) {
		
		return countries.stream()
				.filter(country-> country.getCountryCode().equals(countryCode.trim().toUpperCase()))
				.findAny()
				.orElseThrow(()-> new ResourceNotFoundException("Country not found with this code"));
		
				
	}
	
 public void deleteCountryByCode(String countryCode) {		
		
		for (int i = 0 ; i<countries.size(); i++) {
			if(countries.get(i).getCountryCode().equals(countryCode)) {
				countries.remove(i);
				break;				
			}
		}
		
		
	}
	
	
 public List<Country> getAll() {
		
		return countries;
	}
 */



