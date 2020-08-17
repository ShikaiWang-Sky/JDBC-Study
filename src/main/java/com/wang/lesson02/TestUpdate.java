package com.wang.lesson02;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();  //获取数据库连接
            st = conn.createStatement();      //获得sql的执行对象
            String sql = "update users set `name` = 'wang_sky' where id = 4";

            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            JdbcUtils.release(conn, st, rs);
        }
    }
}
