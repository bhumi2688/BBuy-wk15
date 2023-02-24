package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {
    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }
    // get all list of stores
    @Test
    public void test001(){
        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);

    }
    //post new and retrieve id
    @Test
    public void test002(){
        StorePojo pojo = new StorePojo();
        pojo.setName("sweety");
        pojo.setType("salty");
        pojo.setAddress("hawana 22");
        pojo.setAddress2("hawai 342");
        pojo.setCity("hohoho");
        pojo.setState("hahahaahha");
        pojo.setZip("AWS23");
        pojo.setLat(12);
        pojo.setLng(32);
        pojo.setHours("Mon: 10-3; Wed: 9-2; Fri: 10-4");

                Response response = given()
                .log().all()
                .header("Content-type","application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        int idNumber = response.then().extract().path("id");

        System.out.println(idNumber);

    }
    // update id
    @Test
            public void test003() {
        StorePojo pojo = new StorePojo();
        pojo.setName("squisy");
        pojo.setType("lovely");
        pojo.setCity("creamy");
        pojo.setLng(43);

        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","8921")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);

    }
    //delete it
    @Test
    public void test004(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","8921")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);

    }
    //retrive id and validate
    @Test
    public void test005(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","8921")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}
