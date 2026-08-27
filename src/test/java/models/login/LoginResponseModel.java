package models.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.user.UserFullInfoModel;

public record LoginResponseModel(@JsonProperty("user") UserFullInfoModel userInfo,
                                 @JsonProperty("access_token") String accessToken) {
}
