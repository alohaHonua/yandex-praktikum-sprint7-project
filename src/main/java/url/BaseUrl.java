package url;

import io.restassured.RestAssured;

public abstract class BaseUrl {
    private final static String URL = "https://qa-scooter.praktikum-services.ru/";

    protected void setUrl() {
        RestAssured.baseURI = URL;
    }

}
