package com.wang.lesson02;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInject {
    public static void main(String[] args) {
        //login("lisi", "123456");
        login(" 'or '1=1", "' or '1=1");
    }

    //登陆业务
    public static void login(String username, String password) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //SQL
            //select * from users where `name` = 'lisi' and `password` = '123456';
            //select * from users where `name` = '' or '1=1' and `password` = '' or '1 = 1';
            //上面语句中'' or '1 = 1'为null 或 true，保证永远为真
            String sql = "select * from users where `name` = '"
                    + username + "' and `password` = '" + password
                    + "'";

            rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
                System.out.println("=========================");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
