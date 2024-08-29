import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestObject.RequestUser;
import responseObject.ResponseToken;
import responseObject.ResponseUser;

public class CreateUserTest {

    public String baseURI="https://demoqa.com";
    public RequestUser requestBody;

    @Test
    public void testMethod(){

        System.out.println("=========== Step 1. Create account : ===========");
        createAccount();

        System.out.println("=========== Step 2. Generate token : ===========");
        generateToken();




    }
    public void createAccount(){
        //1.definesc configurarea clientului:
//        1:RestAssured.baseURI="https://demoqa.com"; -> l-am mutat ca si variabila globala pentru a putea fi accesat din orice request


        //2. definim un request:
//        RequestSpecification request= RestAssured.given(); //2.vrem sa construim un request

//        String requestBody="{
//        "userName": "string",
//                "password": "string"}";

        //3.adaugam un request body
        //3: request.body(requestBody)

        //4.executam requestul de tip Post la un endpoint specific:
//       4. Response response =request.post("Account/v1/User");
        // pe un response, facem validari pe status ->OK si status code -> 200
//        System.out.println(response.getStatusCode()); -> ne zice codul statusului, daca nu e cel asteptat, inseamna ca are ceva legatura cu body ul (pentru ca daca da 400 si tu asteptai 200, ceva e gresit)
//        response.getBody().prettyPrint(); -> prettyPrint are deja un systemOut in interior, nu mai este nevoie sa mai punem noi ( asa nu ne dubleaza raspunsul asteptat)


        RequestSpecification request= RestAssured.given();
        request.contentType(ContentType.JSON);
        request.baseUri(baseURI);

        requestBody =  new RequestUser("src/test/resources/createUser.json");


        //adaugam request body la request:
        System.out.println(requestBody);
        request.body(requestBody);


        //executam requestul de tip Post la un endpoint specific:
        Response response =request.post("/Account/v1/User");

        //validam response status code:
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),201);

        Assert.assertTrue(response.getStatusLine().contains("Created"));


        System.out.println(response.getStatusCode());
        ResponseUser responseBody= response.getBody().as(ResponseUser.class);
        Assert.assertTrue(responseBody.getUsername().equals(requestBody.getUserName()));
        System.out.println(responseBody);

    }

    public void generateToken(){
        RequestSpecification request= RestAssured.given();
        request.contentType(ContentType.JSON);
        request.baseUri(baseURI);

        request.body(requestBody);
        Response response =request.post("/Account/v1/GenerateToken");

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertTrue(response.getStatusLine().contains("OK"));

        ResponseToken responseBody=response.getBody().as(ResponseToken.class);

        System.out.println(responseBody.getToken());

        System.out.println(responseBody);

        System.out.println(response.getHeaders());

    }
}
