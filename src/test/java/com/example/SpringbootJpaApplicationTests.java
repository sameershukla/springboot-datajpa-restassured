package com.example;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.model.Passport;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootJpaApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SpringbootJpaApplicationTests {

	@Autowired
	private PersonRepository personRepository;

	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
		Passport passport = new Passport();
		passport.setCountry("India");
		passport.setNumber("YDY30303");
		Person person = new Person();
		person.setName("Sameer Shukla");
		person.setPassport(passport);
		personRepository.save(person);
	}

	/**
	 * Check Status Code 200Ok
	 */
	@Test
	@Ignore
	public void testGetPersonById() {

		given().contentType("application/json").when().get("/person/" + 1).then().statusCode(HttpStatus.SC_OK);
	}

	/**
	 * Check Status Code 200Ok
	 */

	@Test
	@Ignore
	public void testGetPersons() {

		given().contentType("application/json").when().get("/persons").then().statusCode(HttpStatus.SC_OK);
	}

	/**
	 * Validate Response Body
	 */
	@Test
	@Ignore
	public void testGetPersonBody() {
		given().contentType("application/json").when().get("/person/" + 1).then().statusCode(200).body("name",
				equalTo("Sameer Shukla"), "phone.size", equalTo(2));
	}

	/**
	 * Handle 404 scenarios
	 */
	@Test
	@Ignore
	public void handle404NotFound() {
		given().contentType("application/json").when().get("/persons/100000").then()
				.statusCode(HttpStatus.SC_NOT_FOUND);
	}

	/**
	 * Handle 500 Internal Server Error
	 */
	@Test
	@Ignore
	public void handleAllInternalServerErrors() {
		given().contentType("application/json").when().get("/person/abcd").then()
				.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

}
