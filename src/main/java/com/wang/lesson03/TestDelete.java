package com.wang.lesson03;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用?占位符代替参数
            String sql = "delete from users where `id` = ?";

            st = conn.prepareStatement(sql);   //预编译SQL：先写SQL，不执行

            //手动给参数赋值
            st.setInt(1, 5);

            //执行
            int i = st.executeUpdate();
            if (i >0)
                System.out.println("删除成功！");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,null);
        }
    }
}
