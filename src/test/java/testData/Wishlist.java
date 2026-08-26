package testData;

import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.util.Random;

import static java.util.concurrent.TimeUnit.DAYS;

public class Wishlist {
    private String name, dateEnd, comment, theme, nameVisibleStatus, reservePrivacyStatus, viewPrivacyStatus, linkKey;
    private int id;
    private boolean isProfileLinkVisible;
    Faker faker = new Faker();
    Random random = new Random();

    public void generateRandomName() {
        this.name = faker.hobby().activity();
    }

    public void generateRandomComment() {
        int random = faker.number().numberBetween(0, 2);
        switch (random) {
            case (0):
                this.comment = null;
                break;
            case (1):
                this.comment = "";
                break;
            case (2):
                this.comment = faker.oscarMovie().quote();
                break;
        }

    }

    public void generateRandomFutureDate() {
        if(random.nextBoolean())
            this.dateEnd = faker.timeAndDate().future(730, DAYS, "dd.MM.yyyy");
        else
            this.dateEnd = null;
    }

    public void generateRandomNameVisibleStatus() {
        this.nameVisibleStatus = faker.options().option("yesWithNames", "yesWithoutNames", "no");
    }

    public void generateRandomReservePrivacyStatus() {
        this.reservePrivacyStatus = faker.options().option("all", "friends");
    }

    public void generateRandomViewPrivacyStatus() {
        this.viewPrivacyStatus = faker.options().option("all", "friends", "justMe");
    }

    public void generateRandomTheme() {
        this.theme = faker.options().option("barbie", "night", "wave", "mandarin", "lavender", "forest", "ocean", null);
    }

    public void generateRandomIsProfileLinkVisible() {
        this.isProfileLinkVisible = random.nextBoolean();
    }

    @Step("Генерация новых тестовых данных")
    public void generateTestData() {
        generateRandomName();
        generateRandomComment();
        generateRandomFutureDate();
        generateRandomNameVisibleStatus();
        generateRandomViewPrivacyStatus();
        generateRandomReservePrivacyStatus();
        generateRandomIsProfileLinkVisible();
        generateRandomTheme();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getComment() {
        return comment;
    }

    public String getTheme() {
        return theme;
    }

    public String getNameVisibleStatus() {
        return nameVisibleStatus;
    }

    public String getReservePrivacyStatus() {
        return reservePrivacyStatus;
    }

    public String getViewPrivacyStatus() {
        return viewPrivacyStatus;
    }

    public String getLinkKey() {
        return linkKey;
    }

    public void setLinkKey(String linkKey) {
        this.linkKey = linkKey;
    }

    public boolean isProfileLinkVisible() {
        return isProfileLinkVisible;
    }
}
