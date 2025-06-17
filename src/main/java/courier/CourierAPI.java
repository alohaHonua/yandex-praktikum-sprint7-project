package courier;

import url.BaseUrl;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierAPI extends BaseUrl {

    private final static String CREATE_COURIER_PATH = "/api/v1/courier";
    private final static String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    private final static String DELETE_COURIER_PATH = "/api/v1/courier/";

    @Step("Send POST request to /api/v1/courier")
    public Response createCourierRequest(Courier courier) {
        setUrl();
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH);
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourierRequest(Courier courier) {
        setUrl();
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH);
    }

    @Step("Send DELETE request to /api/v1/courier/:id")
    public Response deleteCourierRequest(Courier courier) {
        setUrl();
        Integer courierId = loginCourierRequest(courier).then().extract().path("id");
        if (courierId != null) {
            return given()
                    .delete(DELETE_COURIER_PATH + courierId);
        }
        return given()
                .delete(DELETE_COURIER_PATH);
    }
}
