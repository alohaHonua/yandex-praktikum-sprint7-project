package order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {

    OrderAPI orderAPI = new OrderAPI();

    @Test
    @DisplayName("Получение списка заказов без id курьера")
    public void getOrdersTest() {
        orderAPI.getOrdersRequest().then()
                .statusCode(200)
                .body("orders", notNullValue());
    }

}
