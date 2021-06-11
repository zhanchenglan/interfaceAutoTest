package Interfaces;
import common.Md5;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginTest {
          public static Response loginByiMobile(String mobile,String password){
              Md5 md5=new Md5();
              String passwordMd5=md5.EncoderByMd5(password);
              Response res =   given()
                      .contentType("application/json")
                      .body("{\n" +
                              "\"auth_type\":\"mobile_password\",\n" +
                              "\"client_id\":\"d4909d4e5c40467ebdef591e7e161a0a\",\n" +
                              "\"client_secret\":\"cdec037307bb368dd4da842e8829b2e1\",\n" +
                              "\"device_name\":\"app\",\n" +
                              "\"grant_type\":\"password\",\n" +
                              "\"imei\":\"Z1KVL53QTR\",\n" +
                              "\"mobile\":\""+mobile+"\",\n" +
                              "\"password\":\""+passwordMd5+"\",\n" +
                              "\"scope\":\"all\"\n" +
                              "}")
                      .post("https://mi-api-uat.nailtutu.com/oauth/login?lang=zh&timeStamp=20210611112112&env=inland")
                      .then()
                      .extract()
                      .response();

              return res;
          }
}
