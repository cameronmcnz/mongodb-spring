package com.mcnz.mongodb;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("/cars")
public class MongoServiceApplication {

	@Autowired
	private CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoServiceApplication.class, args);
	}
	
	  @RequestMapping(value = "/add",method = RequestMethod.POST)
	  public ResponseEntity addCar(@RequestBody Car car) throws URISyntaxException{
	    car = new Car();
	    
	    car.setMake("Lexus");
	    car.setModel("350");
	    car.setYear(1998);
		  
		  Car savedCar = carRepository.save( car );

	    // RESTful services that add items should return the
	    // URI of the newly added item
	    URI location = new URI("/cars/"+savedCar.getId().toString());
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setLocation(location);

	    return new ResponseEntity( responseHeaders, HttpStatus.CREATED);
	  }
	  

	  
	  // DELETE ALL CARS
	  @RequestMapping(method=RequestMethod.DELETE)
	  public ResponseEntity deleteAll() {

	    // ADD CODE TO DELETE ALL CARS
	    carRepository.deleteAll();

	    return new ResponseEntity(HttpStatus.OK);
	  }

}
