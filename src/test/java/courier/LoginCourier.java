package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourier {

    CourierAPI courierAPI = new CourierAPI();
    Courier courier = new Courier("Chester2014", "0121", "Честер");

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        courierAPI.createCourierRequest(courier);
    }

    @Test
    @DisplayName("Логин курьера, успешный запрос возвращает id")
    public void loginCourierReturnsIdTest() {
        courierAPI.loginCourierRequest(courier).then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Логин курьера, запрос с некорректным паролем возвращает ошибку")
    public void loginCourierWrongPasswordTest() {
        Courier courierWrongPassword = new Courier("Chester2014", "2101", "Честер");
        courierAPI.loginCourierRequest(courierWrongPassword).then()
                .statusCode(404)
                .assertThat().extract()
                .path("message", String.valueOf(equalTo("Учетная запись не найдена")));
    }

    @Test
    @DisplayName("Логин курьера, запрос с некорректным логином возвращает ошибку")
    public void loginCourierWrongLoginTest() {
        Courier courierWrongLogin = new Courier("Chester2015", "0121", "Честер");
        courierAPI.loginCourierRequest(courierWrongLogin).then()
                .statusCode(404)
                .assertThat().extract()
                .path("message", String.valueOf(equalTo("Учетная запись не найдена")));
    }

    @Test
    @DisplayName("Логин курьера, запрос с несуществующим логином возвращает ошибку")
    public void loginCourierNotExistedLoginTest() {
        Courier courierNotExisted = new Courier("Chester2030", "0121", "Честер");
        courierAPI.loginCourierRequest(courierNotExisted).then()
                .statusCode(404)
                .assertThat().extract()
                .path("message", String.valueOf(equalTo("Учетная запись не найдена")));
    }

    @Test
    @DisplayName("Логин курьера, запрос без поля логин возвращает ошибку")
    public void loginCourierWithoutLoginTest() {
        Courier courierWithoutLogin = new Courier("", "0121", "Честер");
        courierAPI.loginCourierRequest(courierWithoutLogin).then()
                .statusCode(400)
                .assertThat().extract()
                .path("message", String.valueOf(equalTo("Недостаточно данных для входа")));
    }

    @Test
    @DisplayName("Логин курьера, запрос без поля пароль возвращает ошибку")
    public void loginCourierWithoutPasswordTest() {
        Courier courierWithoutPassword = new Courier("Chester2014","","Честер");
        courierAPI.loginCourierRequest(courierWithoutPassword).then()
                .statusCode(400)
                .assertThat().extract()
                .path("message", String.valueOf(equalTo("Недостаточно данных для входа")));
    }

    @After
    public void deleteCourier() {
            courierAPI.deleteCourierRequest(courier);
    }


}
