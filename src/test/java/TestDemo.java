
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Epic("vava app自动化测试报告")
@Feature("vava app登录模块测试用例")
@Owner("jina")
@Link("http://10.30.0.161:1080/bug-browse-1.html")
public class TestDemo {

    @BeforeAll
    public static void initAll(){
        System.out.println("init all test");
    }

    @Test
    @Story("登录成功用例")
    @Description("输入正确的账号密码，登录成功")
    @Issue("701")
    public  void test1(){
        System.out.println("登录测试test1");
    }

    @Test
    @Story("登录失败用例")
    @Description("输入错误的账号密码，登录失败")
    public  void test2(){
        System.out.println("test2");
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("tear down all test");
    }

}
