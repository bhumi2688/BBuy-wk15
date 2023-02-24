package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ProductsCRUDTest extends TestBase {

    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    //get all list of products
    @Test
    public void test001(){
        given()
                .when()
                .log().all()
                .get()
        .then().log().all().statusCode(200);


    }
    //post new and retrieve
    @Test
    public void test002(){
//
        ProductPojo pojo = new ProductPojo();
        pojo.setName("curlywurly batterys");
        pojo.setManufacturer("wurly ");
        pojo.setModel("WC5967WSS");
        pojo.setType("curvy");
        pojo.setUpc("0456789");
        pojo.setImage("img/abc");
        pojo.setPrice(5.99);
        pojo.setShipping(0);
        pojo.setDescription("east to fit");
        pojo.setUrl("http//www.curly.com");


        Response response =given()
                .log().all()
                .header("Content-Type","application/json")
                .when()
                .body(pojo)
                .post();
                response.then().statusCode(201);
                int idNumber = response.then().extract().path("id");

        System.out.println(idNumber);


    }
    //update id
    @Test
    public void test003(){
      ProductPojo pojo = new ProductPojo();
      pojo.setUrl("www.curvy.com");
      pojo.setShipping(1);
      pojo.setName("wurly batteries");
      pojo.setDescription("slim n trim to fit");

      Response response = given()
              .log().all()
              .header("Content-Type","application/json")
              .pathParam("id","9999685")
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
                .pathParam("id","9999685")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);

    }
    // retrieve id and validate
    @Test
    public void test005(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999685")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }


}
