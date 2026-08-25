package models.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserFullInfoModel(int id,
                                String name,
                                String email,
                                int isEmailConfirm,
                                @JsonProperty("created_at") String createdAt,
                                boolean isAdmin,
                                String userlink,
                                String avatar,
                                String description,
                                boolean isPremiumAlways,
                                String premiumDateEnd,
                                boolean isBusiness,
                                String pushToken,
                                String executedPresentsSetting,
                                String fromSanta) {
}
