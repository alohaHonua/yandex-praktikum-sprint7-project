package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {

    OrderAPI orderAPI = new OrderAPI();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Получение списка заказов без id курьера")
    public void getOrdersTest() {
        orderAPI.getOrdersRequest().then()
                .statusCode(200)
                .body("orders", notNullValue());
    }

}
