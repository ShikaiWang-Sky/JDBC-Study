package com.wang.lesson02;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();  //获取数据库连接
            st = conn.createStatement();      //获得sql的执行对象
            String sql = "select * from users where id = 2";

            rs = st.executeQuery(sql);  //查询完毕会返回一个结果集

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            JdbcUtils.release(conn, st, rs);
        }
    }
}
