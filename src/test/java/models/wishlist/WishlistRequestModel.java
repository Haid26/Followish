package models.wishlist;

public record WishlistRequestModel(String name,
                                   String dateEnd,
                                   String comment,
                                   String nameVisibleStatus,
                                   String viewPrivacyStatus,
                                   String reservePrivacyStatus,
                                   boolean isProfileLinkVisible,
                                   String theme
) {
}
