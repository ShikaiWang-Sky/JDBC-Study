package com.wang.lesson03;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用?占位符代替参数
            String sql = "insert into users(id,`name`,`password`,`email`,`birthday`) values(?, ?, ?, ?, ?)";

            st = conn.prepareStatement(sql);   //预编译SQL：先写SQL，不执行

            //手动给参数赋值
            st.setInt(1, 5);
            st.setString(2, "wang");
            st.setString(3, "456789");
            st.setString(4, "456789@qq.com");
            //注意点：sql.Date   SQL     java.sql.Date()传入时间戳获得SQL的Date
            //      util.Date   Java    new Date().getTime()获得时间戳
            st.setDate(5, new java.sql.Date(new Date().getTime()));

            //执行
            int i = st.executeUpdate();
            if (i >0)
                System.out.println("插入成功！");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,null);
        }
    }
}
