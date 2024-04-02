package com.game.dao.base;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;


public class Druid {
    //定义一个静态的数据源
    static DataSource dataSource=null;
    //在静态代码块中读取文件,创建数据源
    static {
        Properties properties = new Properties();
        try (InputStream input = Druid.class.getClassLoader().getResourceAsStream("config/druid.properties")) {
            if (input != null) {
                properties.load(input);
                dataSource=DruidDataSourceFactory.createDataSource(properties);
            } else {
                throw new FileNotFoundException("druid.properties file not found in the classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //定义方法，返回连接
    public static Connection getConnection() throws SQLException {
        System.out.println("准备获取连接");
        return dataSource.getConnection();
    }

    //定义方法，取消连接的引用
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,Connection connection){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.out.println("连接引用已终止");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

