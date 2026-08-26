package models.wishlist;

import java.util.List;

public record WishlistResponseModel(String name,
                                    String dateEnd,
                                    String comment,
                                    String nameVisibleStatus,
                                    String viewPrivacyStatus,
                                    String reservePrivacyStatus,
                                    boolean isProfileLinkVisible,
                                    String theme,
                                    String linkKey,
                                    int userId,
                                    int id) {
}
