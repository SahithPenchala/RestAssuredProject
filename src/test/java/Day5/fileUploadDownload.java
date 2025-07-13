package Day5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class fileUploadDownload
{
    @Test(priority = 1)
    void singleFileUpload()
    {
        File myfile = new File("C:\\Users\\sahit\\OneDrive\\Documents\\Automation\\test1.txt");  //this is th file path where particular file with pickup
        given()
                .multiPart("file",myfile)    //this is for form data mode //this will go along with post request
                .contentType("multipart/form-data")   //this is for content type of that file//these are important for uploading file

                .when()
                .post("http://localhost:8080/uploadFile")// this is post request URl to upload

                .then()
                .statusCode(200)
                .body("fileName",equalTo("test1.txt")) // this is verifying filename reponse
                .body("fileType",equalTo("application/octet-stream"))
                .body("size",equalTo(15))
                .log().all();

    }

   // @Test
    void multipleFilesUpload()
    {
        File myfile = new File("C:\\Users\\sahit\\OneDrive\\Documents\\Automation\\test1.txt");  //this is th file path where particular file with pickup
        File myfile2 = new File("C:\\Users\\sahit\\OneDrive\\Documents\\Automation\\test2.txt");
        given()
                .multiPart("files",myfile)    //this is for form data mode //this will go along with post request
                .multiPart("files",myfile2)
                .contentType("multipart/form-data")   //this is for content type of that file//these are important for uploading file

                .when()
                .post("http://localhost:8080/uploadMultipleFiles")// this is post request URl to upload

                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("test1.txt")) // this is verifying filename reponse
                .body("[1].fileName",equalTo("test2.txt"))
                .body("[0].fileType",equalTo("application/octet-stream"))
                .body("[1].size",equalTo(16))
                .log().all();

    }


    // this is for uploading mutiple files without specifying different file names
    //wont work for all kinds of API's
   // @Test
    void multipleFilesUpload2()
    {
        File myfile = new File("C:\\Users\\sahit\\OneDrive\\Documents\\Automation\\test1.txt");  //this is th file path where particular file with pickup
        File myfile2 = new File("C:\\Users\\sahit\\OneDrive\\Documents\\Automation\\test2.txt");

        File filearr[] = {myfile,myfile2};  //we are passing different file variables in to array

        given()
                .multiPart("files",filearr)    //this is for form data mode //this will go along with post request
                .contentType("multipart/form-data")   //this is for content type of that file//these are important for uploading file

                .when()
                .post("http://localhost:8080/uploadMultipleFiles")// this is post request URl to upload

                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("test1.txt")) // this is verifying filename reponse
                .body("[1].fileName",equalTo("test2.txt"))
                .body("[0].fileType",equalTo("application/octet-stream"))
                .body("[1].size",equalTo(16))
                .log().all();

    }

    @Test(priority = 2)
    void fileDownload()
    {
        given()
                .when()
                .get("http://localhost:8080/downloadFile/test1.txt")
                .then()
                .statusCode(200)
                .log().body();
    }
}
