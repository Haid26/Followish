package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static allure.AllureListenerApi.withCustomTemplate;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class BaseSpec {

    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplate())
            .log().all()
            .contentType(JSON);
    public static ResponseSpecification baseNotFoundResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .expectBody(matchesJsonSchemaInClasspath("schemas/common/not_found_response_schema.json"))
            .expectBody("message", notNullValue())
            .build();

}
