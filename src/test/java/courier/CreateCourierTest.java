package courier;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    CourierAPI courierAPI = new CourierAPI();
    Courier courier = new Courier("Simba", "6789", "Симба");
    Courier courierNoPassword = new Courier("Simba","", "Симба");
    Courier courierNoLogin = new Courier("","6789", "Симба");
    Courier courierSameLogin = new Courier("Simba", "9678", "Симбастьян");

    @Test
    @DisplayName("Создание курьера, успешный запрос возвращает код 201")
    public void createCourierSuccessfulTest() {
        courierAPI.createCourierRequest(courier).then()
                .assertThat().statusCode(201);
    }

    @Test
    @DisplayName("Создание курьера, успешный запрос возвращает ok: true")
    public void createCourierReturnsOkTest() {
        courierAPI.createCourierRequest(courier).then()
                .assertThat().extract()
                .path("ok", String.valueOf(equalTo("true")));
    }

    @Test
    @DisplayName("Создание курьера, нельзя создать двух одинаковых курьеров")
    public void createDuplicateCourierIsNotAllowedTest(){
        courierAPI.createCourierRequest(courier);
        courierAPI.createCourierRequest(courier).then()
                .assertThat().statusCode(409);
    }

    @Test
    @DisplayName("Создание курьера, нельзя создать курьера без пароля")
    public void createCourierRequiredFieldPasswordTest(){
        courierAPI.createCourierRequest(courierNoPassword).then()
                .assertThat().statusCode(400)
                .extract().path("message", String.valueOf(equalTo("Недостаточно данных для создания учетной записи")));
    }

    @Test
    @DisplayName("Создание курьера, нельзя создать курьера без логина")
    public void createCourierRequiredFieldLoginTest(){
        courierAPI.createCourierRequest(courierNoLogin).then()
                .assertThat().statusCode(400)
                .extract().path("message", String.valueOf(equalTo("Недостаточно данных для создания учетной записи")));
    }

    @Test
    @DisplayName("Создание курьера, нельзя создать курьера с существующим логином")
    public void createCourierDuplicateLoginIsNotAllowedTest(){
        courierAPI.createCourierRequest(courier);
        courierAPI.createCourierRequest(courierSameLogin).then()
                .assertThat().statusCode(409)
                .extract().path("message", String.valueOf(equalTo("Этот логин уже используется. Попробуйте другой.")));
    }

    @After
    public void deleteCourier() {
        if (courier != null) {
            courierAPI.deleteCourierRequest(courier);
        } else {
            courierAPI.deleteCourierRequest(courierNoPassword);
        }
    }
}


