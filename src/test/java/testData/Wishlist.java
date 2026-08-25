package testData;

import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.DAYS;

public class Wishlist {
    private String id, name, dateEnd, comment;
    Faker faker = new Faker();

    public void generateRandomName() {
        this.name = faker.hobby().activity();
    }

    public void generateRandomComment() {
        this.comment = faker.oscarMovie().quote();
    }

    public void generateRandomFutureDate() {
        this.dateEnd = faker.timeAndDate().future(730, DAYS, "dd.MM.yyyy");
    }

    @Step("Генерация новых тестовых данных")
    public void generateTestData(){
        generateRandomName();
        generateRandomComment();
        generateRandomFutureDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getComment() {
        return comment;
    }

}
