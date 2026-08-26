package api;

import io.qameta.allure.Step;
import models.common.NotFoundResponseModel;
import models.wishlist.*;

import static io.restassured.RestAssured.given;

import static specs.WishlistSpec.*;

public class WishlistsApiClient {
    @Step("запрос создания вишлиста")
    public WishlistResponseModel create(WishlistRequestModel body, String token){
        return given(wishlistRequestSpec)
                .body(body)
                .headers("Authorization",
                        "Bearer " + token)
                .when()
                .post("/wishlists")
                .then()
                .spec(successfulWishlistCreationResponseSpec)
                .extract()
                .as(WishlistResponseModel.class);
    }

    @Step("запрос на получение списка вишлистов")
    public WishlistGetListResponseModel getList(String token){
        return given(wishlistRequestSpec)
                .headers("Authorization",
                        "Bearer " + token)
                .when()
                .get("/wishlists")
                .then()
                .spec(successfulWishlistGetListResponseSpec)
                .extract()
                .as(WishlistGetListResponseModel.class);
    }

    @Step("запрос редактирования вишлиста")
    public WishlistResponseModel edit(WishlistRequestModel body,String linkKey, String token){
        return given(wishlistRequestSpec)
                .body(body)
                .headers("Authorization",
                        "Bearer " + token)
                .when()
                .put("/wishlists/"+linkKey)
                .then()
                .spec(successfulWishlistEditionResponseSpec)
                .extract()
                .as(WishlistResponseModel.class);
    }

    @Step("запрос удаления вишлиста")
    public WishlistDeleteModel delete(WishlistDeleteModel body, String token){
        return given(wishlistRequestSpec)
                .body(body)
                .headers("Authorization",
                        "Bearer " + token)
                .when()
                .delete("/wishlists/"+body.id())
                .then()
                .spec(successfulWishlistDeletionResponseSpec)
                .extract()
                .as(WishlistDeleteModel.class);
    }

    @Step("запрос редактирования несуществующего вишлиста")
    public NotFoundResponseModel editNotFound(WishlistRequestModel body, String linkKey, String token){
        return given(wishlistRequestSpec)
                .body(body)
                .headers("Authorization",
                        "Bearer " + token)
                .when()
                .put("/wishlists/"+linkKey)
                .then()
                .spec(wishlistNotFoundResponseSpec)
                .extract()
                .as(NotFoundResponseModel.class);
    }

    @Step("запрос удаления несуществующего вишлиста")
    public NotFoundResponseModel deleteNotFound(WishlistDeleteModel body, String token){
        return given(wishlistRequestSpec)
                .body(body)
                .headers("Authorization",
                        "Bearer " + token)
                .when()
                .delete("/wishlists/"+body.id())
                .then()
                .spec(wishlistNotFoundResponseSpec)
                .extract()
                .as(NotFoundResponseModel.class);
    }

}
