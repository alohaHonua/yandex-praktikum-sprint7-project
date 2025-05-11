package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderAPI {

    @Step("Send POST request to /api/v1/orders")
    public Response createOrderRequest(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Send GET request to /api/v1/orders")
    public Response getOrdersRequest() {
        return given()
                .get("/api/v1/orders");
    }


}
