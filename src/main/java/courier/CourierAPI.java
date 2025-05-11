package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierAPI {

    @Step("Send POST request to /api/v1/courier")
    public Response createCourierRequest(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourierRequest(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Send DELETE request to /api/v1/courier/:id")
    public Response deleteCourierRequest(Courier courier) {
        Integer courierId = loginCourierRequest(courier).then().extract().path("id");
        if (courierId != null) {
            return given()
                    .delete("/api/v1/courier/" + courierId);
        }
        return given()
                .delete("/api/v1/courier/");
    }
}
