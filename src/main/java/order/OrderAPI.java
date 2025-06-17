package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import url.BaseUrl;

import static io.restassured.RestAssured.given;

public class OrderAPI extends BaseUrl {

    private final static String CREATE_ORDER_PATH = "/api/v1/orders";
    private final static String GET_ORDER_PATH = "/api/v1/orders";

    @Step("Send POST request to /api/v1/orders")
    public Response createOrderRequest(Order order) {
        setUrl();
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH);
    }

    @Step("Send GET request to /api/v1/orders")
    public Response getOrdersRequest() {
        setUrl();
        return given()
                .get(GET_ORDER_PATH);
    }


}
