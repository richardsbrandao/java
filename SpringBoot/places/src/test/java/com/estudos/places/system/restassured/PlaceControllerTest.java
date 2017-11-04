package com.estudos.places.system.restassured;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.estudos.places.PlacesApplication;
import com.estudos.places.dao.PlaceDao;
import com.estudos.places.domain.Place;
import com.estudos.places.json.BasicErrorResponse;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PlacesApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PlaceControllerTest {

	@Autowired
	private PlaceDao placeDao;
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.requestSpecification = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setPort(this.port )
        .setBasePath("/places/place")
        .build();
		
		placeDao.deleteAll();
		placeDao.save(new Place("Cristo Redentor", "Rio de Janeiro", "Brasil",    new double[]{ -22.9518769, -43.2104991 }));
		placeDao.save(new Place("Praia de Copacabana", "Rio de Janeiro", "Brasil", new double[]{ -22.9671676, -43.1753057 }));
		placeDao.save(new Place("Fontana Di Trevi", "Roma", "Italia",              new double[]{  41.9009325,  12.4833129 }));
	}
	
	
	@Test
	public void test_save_place() {
		Place place = new Place("Golden Gate", "San Francisco", "EUA", new double[]{37.808249, -122.476529});
		
		Place response = given()
			.body(place)
		.post()
		.then()
			.statusCode(200)
			.extract().body().as(Place.class);
		
		assertNotNull( response.getId() );
	}
	
	@Test
	public void test_find_all() {
		List<Place> response = Arrays.asList( given()
			.get()
			.then()
				.statusCode(200)
				.extract().body().as(Place[].class) );
		
		assertThat( response, hasSize(3) );
		
		Place place = response.get(0);
		assertThat( place.getName(), equalTo("Cristo Redentor") );
		
		place = response.get(1);
		assertThat( place.getName(), equalTo("Praia de Copacabana") );
		
		place = response.get(2);
		assertThat( place.getName(), equalTo("Fontana Di Trevi") );
	}
	
	@Test
	public void test_wrong_latitude_longitude() {
		BasicErrorResponse errorResponse = given()
			.header("accept", "application/json")
			.param("lat", "a")
			.param("lon", "23")
		.get("/filter")
		.then()
			.statusCode(400)
			.extract().body().as(BasicErrorResponse.class);
		
		assertThat( errorResponse.getErrors(), hasSize(1) );
		
		assertThat( errorResponse.getErrors().get(0).getField(), equalTo("lat") );
	}
	
	@Test
	public void test_find_near_point() {
		List<Place> places = Arrays.asList( given()
			.header("accept", "application/json")
			.param("lat", "20")
			.param("lon", "35")
		.get("/filter")
		.then()
			.statusCode(200)
			.extract().body().as(Place[].class) );
		
		assertThat( places, hasSize(3) );
		
		Place place = places.get(0);
		assertThat( "Fontana Di Trevi", equalTo(place.getName()) );
		
		place = places.get(1);
		assertThat( "Praia de Copacabana", equalTo(place.getName()) );
		
		place = places.get(2);
		assertThat( "Cristo Redentor", equalTo(place.getName()) );
	}
	
	@Test
	public void test_find_near_other_position() {
		List<Place> places = Arrays.asList( given()
				.header("accept", "application/json")
				.param("lat", "-10")
				.param("lon", "-25")
			.get("/filter")
			.then()
				.statusCode(200)
				.extract().body().as(Place[].class) );
			
			assertThat( places, hasSize(3) );
			
			Place place = places.get(0);
			assertThat( "Praia de Copacabana", equalTo(place.getName()) );
			
			place = places.get(1);
			assertThat( "Cristo Redentor", equalTo(place.getName()) );
			
			place = places.get(2);
			assertThat( "Fontana Di Trevi", equalTo(place.getName()) );
	}
	
}
