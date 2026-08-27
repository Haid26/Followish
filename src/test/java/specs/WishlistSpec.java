package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;
import static specs.BaseSpec.baseNotFoundResponseSpec;
import static specs.BaseSpec.baseRequestSpec;

public class WishlistSpec {
    public static RequestSpecification wishlistRequestSpec = baseRequestSpec;
    public static ResponseSpecification successfulWishlistCreationResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .expectBody(matchesJsonSchemaInClasspath("schemas/wishlists/wishlist_response_schema.json"))
            .expectBody("name", notNullValue())
            .build();
    public static ResponseSpecification successfulWishlistEditionResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/wishlists/wishlist_response_schema.json"))
            .expectBody("name", notNullValue())
            .build();
    public static ResponseSpecification successfulWishlistGetListResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/wishlists/successful_wishlist_getlist_response_schema.json"))
            .build();
    public static ResponseSpecification successfulWishlistDeletionResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("id", notNullValue())
            .build();
    public static ResponseSpecification wishlistNotFoundResponseSpec = baseNotFoundResponseSpec;
}
