package models.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserFullInfoModel(int id,
                                String name,
                                String email) {
}
