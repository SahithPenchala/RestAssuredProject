package Day6;

//POJO(Java Object)----Serialization---->JSON Object----Deserialization---->POJO

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Day2.pojo_postRequest;
//import io.restassured.mapper.ObjectMapper;
import org.testng.annotations.Test;

public class serializationDeserialization {

    //POJO to JSON(Serialization)
  //  @Test
    void convertPOJOToJSON() throws JsonProcessingException {
        //Created java object using pojo class
        student stu = new student();      //pojo
        stu.setName("Sand");
        stu.setLocation("India");
        stu.setPhone("239023920");
        String coursesArr[] = {"C","C++"};;
        stu.setCourses(coursesArr);

        //convert java object---->JSON object(Serialization)
        ObjectMapper obj = new ObjectMapper();
        String jsondata = obj.writerWithDefaultPrettyPrinter().writeValueAsString(stu);   //with i am converting pojo in to json and returning value same as json
        System.out.println(jsondata);
    }

    //JSON to POJO(De-Serialization)
    @Test
    void convertJSONToPOJO() throws JsonProcessingException {
        //Created JSON object to pojo class

        String jsonData = "{\n" +
                "  \"name\" : \"Sand\",\n" +
                "  \"location\" : \"India\",\n" +
                "  \"phone\" : \"239023920\",\n" +
                "  \"courses\" : [ \"C\", \"C++\" ]\n" +
                "}";

        //convert JSON Data---->POJO object(De-Serialization)
        ObjectMapper stuObj = new ObjectMapper();

        student stuPOJO = stuObj.readValue(jsonData, student.class);  //convert JSON to POJO
        System.out.println("Name:"+stuPOJO.getName());
        System.out.println("Location:"+stuPOJO.getLocation());
        System.out.println("Phone:"+stuPOJO.getPhone());
        System.out.println("Get Course 1:"+stuPOJO.getCourses()[0]);
        System.out.println("Get Course 2:"+stuPOJO.getCourses()[1]);
    }
}
