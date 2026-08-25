package api;

import io.qameta.allure.Step;
import models.login.LoginRequestModel;
import models.login.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.successfulLoginResponseSpec;

public class AuthApiClient {
    @Step("запрос авторизации")
    public LoginResponseModel login(LoginRequestModel body){
        return given(loginRequestSpec)
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                .spec(successfulLoginResponseSpec)
                .extract()
                .as(LoginResponseModel.class);
    }
}
