package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    OrderAPI orderAPI = new OrderAPI();
    private String color;

    public CreateOrderTest(String color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getColor() {
        Object[][] color = new Object[][]{
                {"[\"BLACK\"]"},
                {"[\"GREY\"]"},
                {"[\"BLACK\","+" \"GREY\"]"},
                {"[\"\"]"}
        };
        return Arrays.asList(color);
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrderTest() {
        Order order = new Order("Симба", "Булкин", "г. Москва, ул. Кошкина, 1", "7", "+7 777 777 77 77",1, "2025-06-01","", Collections.singletonList(color));
        orderAPI.createOrderRequest(order).then()
                .statusCode(201)
                .body("track", notNullValue());
    }

}
