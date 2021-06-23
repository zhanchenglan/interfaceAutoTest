package common;

import java.sql.*;

public class MysqlConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //mysql：要连接的数据库名称 DB_URL=“jdbc:mysql://localhost:3306/要连接的数据库名称”
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "12345678";


    //连接数据库
    public static Connection createConnection() {
        Connection conn = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


        } catch (Exception se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        }

        return conn;
    }


    //获取数据
    public static ResultSet getData(String sql, Connection conn) {
        ResultSet rs = null;

        // 执行查询
        System.out.println(" 实例化Statement对象...");
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rs;
    }


    //关闭数据库连接
    public static void closeDataBase(Connection conn, ResultSet rs) {

        try {
            rs.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("断开数据库连接!");
    }
}
