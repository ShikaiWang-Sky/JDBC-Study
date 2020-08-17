package com.wang.lesson03;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.*;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            String sql = "SELECT * from users where `id` = ?";

            st = conn.prepareStatement(sql);

            st.setInt(1, 2);    //传递参数

            rs = st.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
