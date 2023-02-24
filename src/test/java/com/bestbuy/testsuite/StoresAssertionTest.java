package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);
    }

    //1 Verify if the total limit is equal to 1561
    @Test
    public void test01() {
        response.body("total", equalTo(1561));
    }

    //2 Verify if the store of the limit is equal to 10
    @Test
    public void test02() {
        response.body("limit", equalTo(10));
    }

    //3 Check the single 'Name' in the ArrayList(Inver Grove Heights)
    @Test
    public void test03() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    //4 Check the multiple 'Names' in ArrayList(Roseville,Burnsville,Maplewood)
    @Test
    public void test04() {
        response.body("data.name", hasItem("Roseville"));
        response.body("data.name", hasItem("Burnsville"));
        response.body("data.name", hasItem("Maplewood"));
    }

    //5 Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void test05() {
        response.body("data[2].services[3].storeservices", hasKey("storeId"));
    }

    //6 Check hash map values 'createdAt' inside storeservices map where store name =Roseville
    @Test
    public void test06() {
        response.body("data[2].services[0].storeservices", hasKey("createdAt"));
    }

    //7 Verify the state = MN of forth store
    @Test
    public void test07() {
        response.body("data[3].state", equalTo("MN"));
    }

    //8 Verify the store name = Rochester of 9th store
    @Test
    public void test08() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    //9 Verify the storeid = 11 for the 6th store
    @Test
    public void test09() {
        response.body("data[5].id", equalTo(11));
    }

    //10 Verify the serviceid = 4 for the 7th store of forth service
    @Test
    public void test010() {
        response.body("data[6].services[3].id", equalTo(4));
    }


}
