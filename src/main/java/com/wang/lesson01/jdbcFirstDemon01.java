package com.wang.lesson01;

import java.sql.*;

//我的第一个jdbc程序
public class jdbcFirstDemon01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");     //固定写法，加载驱动

        //2.用户信息和URL
        //URL ?前的内容是jdbc:使用的数据库厂商://主机名:端口号/数据库的名称
        //URL ?后的内容 useUnicode=true&characterEncoding=utf8&useSSL=true
        //第一个参数：使用unicode编码，可以显示中文
        //第二个参数：设定字符集为utf-8
        //第三个参数：使用安全的的连接
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";

        //3.连接成功，数据库对象 Connection代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);   //al + enter

        //4.执行SQL的对象 Statement执行SQL的对象
        Statement statement = connection.createStatement();

        //5.执行SQL的对象 去 执行SQL，可能存在结果，返回查看结果
        String sql = "select * from users";

        ResultSet resultSet = statement.executeQuery(sql);  //返回的结果集，结果集中封装了我们全部查询出来的结果

        while (resultSet.next()) {
            System.out.println(("id=" + resultSet.getObject("id")));
            System.out.println(("name=" + resultSet.getObject("name")));
            System.out.println(("password=" + resultSet.getObject("password")));
            System.out.println(("email=" + resultSet.getObject("email")));
            System.out.println(("birthday=" + resultSet.getObject("birthday")));
            System.out.println("====================================");
        }

        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
