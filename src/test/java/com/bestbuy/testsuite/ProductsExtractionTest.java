package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }

    //21 Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("Value of limit is : " + limit);
        Assert.assertEquals(10, limit);
    }

    //22 Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("Total is : " + total);
        Assert.assertEquals(51957, total);
    }

    //23 Extract the name of 5th product
    @Test
    public void test023() {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of 5th product is : " + name);
        Assert.assertEquals("Duracell - C Batteries (4-Pack)", name);

    }

    //24 Extract the names of all the products
    @Test
    public void test024() {
        List<String> namesofproducts = response.extract().path("data.name");
        System.out.println("List of names : " + namesofproducts);
        for (String a : namesofproducts) {
            if (a.equals(10)) {
                Assert.assertTrue(true);
            }
        }
    }

    //25 Extract the productId of all the products
    @Test
    public void test025() {
        List<Integer> productId = response.extract().path("data.id");
        System.out.println("The storeId of all stores is : " + productId);
        for (Integer b : productId) {
            if (b.equals(51957)) {
                Assert.assertTrue(true);
            }
        }
    }

    //26 Print the size of the data list
    @Test
    public void test026() {
        List<Integer> sizeOfData = response.extract().path("data");
        int size = sizeOfData.size();
        System.out.println("The size of data list : " + size);

    }

    //27 Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void test027() {
        List<HashMap<String, ?>> productName = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println(productName);

    }

    //28 Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)
    @Test
    public void test028() {
        List<String> model = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The model of the product is : " + model);

    }

    // 29 Get all the categories of 8th products
    @Test
    public void test029() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("The categories of 8th products are : " + categories);
    }

    //30 Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<HashMap<String, ?>> catagories = response.extract().path("data[3].categories");
        System.out.println("The catagories of store : " + catagories);

    }

    //31 Get all the descriptions of all the products
    @Test
    public void test031() {
        List<HashMap<?, ?>> descriptions = response.extract().path("data.description");
        System.out.println("The description of all product is : " + descriptions);
    }

    //32 Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<Integer> id = response.extract().path("data.categories.id");
        System.out.println("The id of all categories is : " + id);
    }

    //33 Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> product = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println(product);
    }

    //34 Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {//not working as
        List<Integer> totalcategories = response.extract().path("data.findAll{it.name='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("The total no of categories is : " + totalcategories);
    }

    //35 Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {
        List<Integer> prdcreatedAt = response.extract().path("data.findAll{it.price <5.49}.createdAt");
        System.out.println("The product createdAt price : " + prdcreatedAt);

    }

    //36 Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
    //Pack)”
    @Test
    public void test036() {
//        List<String>nameofCategories=response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.name");
//        System.out.println("The name of all categories : " + nameofCategories);
        List<HashMap<String, ?>> categories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println(categories);

    }

    //37 Find the manufacturer of all the products
    @Test
    public void test037() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("The manufacturer of all products are : " + manufacturer);
    }

    //38 Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test038() {
        List<String> imge = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("The imge of products is : " + imge);
    }

    //39 Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {  //not working
        List<Integer> categoriescreatedAt = response.extract().path("data.findAll{it.price >5.99}.createdAt");
        System.out.println("The product createdAt price : " + categoriescreatedAt);

    }

    //40 Find the uri of all the products
    @Test
    public void test040() {
        List<String> url = response.extract().path("data.url");
        System.out.println("The url of all the stores : " + url);

    }


}
