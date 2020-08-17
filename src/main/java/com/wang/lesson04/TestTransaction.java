package com.wang.lesson04;

import com.wang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //关闭数据库的自动提交，自动会开启事务
            conn.setAutoCommit(false);  //开启事务

            String sql_1 = "update account set money = money - 100 where name = 'A'";
            st = conn.prepareStatement(sql_1);
            st.executeUpdate();

            int x = 1 / 0;  //报错，只执行了上面的SQL，此处报错下面的SQL不执行

            String sql_2 = "update account set money = money + 100 where name = 'B'";
            st = conn.prepareStatement(sql_2);
            st.executeUpdate();

            //业务完毕，提交数据
            conn.commit();

            System.out.println("成功！");

        } catch (SQLException throwables) {
            /*
            显示定义失败回滚，可以不写
            try {
                conn.rollback();    //如果失败则回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            */
            //如果失败，默认回滚
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
}
