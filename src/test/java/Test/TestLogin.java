package Test;

import Interfaces.LoginTest;
import common.MysqlConnection;
import io.restassured.response.Response;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;


public class TestLogin {

    @ParameterizedTest
    @CsvSource({
            "13536764015, 123456",
            "18475465437, 123456",
            "18475465438, 123456"
    })
    public void testLogin(String mobile,String password){
//       String mobile="13536764015";
//       String password="123456";
       Response loginResponse = LoginTest.loginByiMobile(mobile,password);
       Integer stateCode = loginResponse.path("stateCode");
       String access_token=loginResponse.path("data.access_token");

       assertTrue (stateCode.equals(200));
        System.out.println(access_token);
    }

    @ParameterizedTest
    @CsvFileSource(resources ="/loginParameter.yaml",numLinesToSkip = 1)
    public void loginTest2(String mobile,String password){
       Response loginResponse = LoginTest.loginByiMobile(mobile,password);
       Integer stateCode = loginResponse.path("stateCode");
       String access_token=loginResponse.path("data.access_token");

      // assertTrue (stateCode.equals(200));
        assertAll("stateCode",
                () -> assertEquals("200", stateCode.toString()),
                () -> assertNotNull(access_token)
        );

    }

    @Test
    public void loginTest3() {
        Connection conn = MysqlConnection.createConnection();

        String sql = "SELECT userId, mobile, password FROM userCount";
        ResultSet rs = MysqlConnection.getData(sql, conn);
        String mobile = null;
        String password = null;
        try {
            while (rs.next()) {
                // 通过字段检索
                mobile = rs.getString("mobile");
                password = rs.getString("password");
                System.out.println("mobile:" + mobile);
                System.out.println("password:" + password);

                Response loginResponse = LoginTest.loginByiMobile(mobile, password);
                Integer stateCode = loginResponse.path("stateCode");
                String access_token = loginResponse.path("data.access_token");
                System.out.println("stateCode:" + stateCode);
                System.out.println("access_token:" + access_token);

                assertTrue(stateCode.equals(200));
                assertAll("stateCode",
                        () -> assertEquals("200", stateCode.toString()),
                        () -> assertNotNull(access_token)
                );
            }
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        }

        MysqlConnection.closeDataBase(conn, rs);

    }


}
