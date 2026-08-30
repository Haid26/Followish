package tests.api;

import io.qameta.allure.*;
import models.login.LoginRequestModel;
import models.login.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static testData.TestData.EXPECTED_TOKEN_PATH;

@Epic("Авторизация пользователя")
@Owner("Haid26")
@DisplayName("Тесты на авторизацию пользователя")
@Tag("Api")
public class LoginTests extends TestBaseApi{
    @Test
    @Feature("Авторизация пользователя")
    @Story("Авторизация пользователя через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Успешная авторизация пользователя")
    public void shouldLoginApi() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());

        step("Проверка ответа метода", () -> {
            assertEquals(user.getEmail(), loginResponse.userInfo().email());
            assertThat(user.getAccess().startsWith(EXPECTED_TOKEN_PATH));
        });
    }
}
