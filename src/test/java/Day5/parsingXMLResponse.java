package Day5;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class parsingXMLResponse {

    @Test
    void testXMLResponse()
    {
      //Approach 1:Without returning response in the variable

        given()

                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
                .then()
                .statusCode(200)
              //  .contentType(ContentType.XML)
                .header("Content-Type","application/xml")
                .body("pets.pet.category.id",equalTo("1"))        //in this particular node is this value availble or not
                .body("pets.pet[1].id",equalTo("6"));  //we will parse the index to fetch exact value and check which index it is taking
                                                                              //In xpath it will start with / here it will with .

        //Approach 2 : returning the response in to a variable

       Response res = given()

                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending");

           Assert.assertEquals(res.getStatusCode(),200);
         Assert.assertEquals(res.header("Content-Type") ,"application/xml");
           String petId = res.xmlPath().get("pets.pet[1].id").toString();   //here for xml we use xml path to get the value and u have to convert it to string and store it in variable
           Assert.assertEquals(petId,"6");

    }

    @Test
    void testXMLResponseBody()
    {

        Response res = given()

                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending");

        XmlPath xmlObj = new XmlPath(res.asString());  //here we have to convert response in to string format

        //verify total number of pets

        List<String> pets = xmlObj.getList("pets.pet");     //we will capture all info in the form of list
        Assert.assertEquals(pets.size(),"10");   //this will give info of all pets and verifying total pets from the response

        //verify specific value is present or not in the response

        List<String> petNames = xmlObj.getList("pets.pet.name");

        boolean status=false;
        for (String petName : petNames)
        {
            System.out.println(petName);
            if (petName.equals("Romie"))
            {
                status = true;
                break;
            }
        }
        Assert.assertEquals(status,true);


    }
}
