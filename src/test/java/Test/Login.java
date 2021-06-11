package Test;

import Interfaces.LoginTest;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class Login {

    @Test
    public void loginTest(){
       String mobile="13536764015";
       String password="123456";
       Response loginResponse = LoginTest.loginByiMobile(mobile,password);
       Integer stateCode = loginResponse.path("stateCode");
       String access_token=loginResponse.path("data.access_token");
       assert(stateCode.equals(200));
        System.out.println(access_token);
    }

}
