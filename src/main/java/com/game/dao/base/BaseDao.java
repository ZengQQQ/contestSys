package com.game.dao.base;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseDao<T> {
    public String tableName;


    public static <T> List<T> resultSetToList(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            T t = clazz.newInstance();
            // 获取实体类的所有属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 获取属性名
                String fieldName = field.getName();
                // 获取属性类型
                Class<?> fieldType = field.getType();
                // 根据属性类型获取 ResultSet 中的值
                Object value = null;
                if (fieldType == int.class) {
                    value = rs.getInt(fieldName);
                } else if (fieldType == String.class) {
                    value = rs.getString(fieldName);
                } else if (fieldType == Date.class) {
                    value = rs.getDate(fieldName);
                } else {
                    // 其他类型自行处理
                    System.out.println("resultSetToList 其他类型");
                }
                // 设置属性值
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }
        return list;
    }

    /**
     * 插入数据
     *
     * @param connection 数据库连接
     * @param map        插入的数据 "String, Object"
     * @return 是否插入成功
     */
    public boolean insert(Connection connection, Map<String, Object> map) {

        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ? ");
        // 拼接key
        sqlBuilder.append("(");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append(",");
        }
        sqlBuilder.append(")");
        // 拼接value的？
        sqlBuilder.append(" VALUES (");
        sqlBuilder.append("?)".repeat(map.size()));
        sqlBuilder.append(")");

        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {


            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            // 表名
            preparedStatement.setObject(1, tableName);
            int conut = 2;
            // 设置值
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                preparedStatement.setObject(conut, entry.getValue());
                conut++;
            }

            // 执行插入
            int res = preparedStatement.executeUpdate();
            return res != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid.close(null, preparedStatement, connection);
        }
    }

    /**
     * 删除数据
     * @param connection 数据库连接
     * @param map 删除条件 "String, Object"
     * @return 删除的行数
     */
    public int delete(Connection connection, Map<String, Object> map) {
        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ? WHERE ");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append("=? AND ");
        }
        // 闭合and
        sqlBuilder.append("1=1");

        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
            // 表名
            preparedStatement.setObject(conut, tableName);
            conut++;
            // 设置值
            for (Object value : map.values()) {
                preparedStatement.setObject(conut, value);
                conut++;
            }

            int res = preparedStatement.executeUpdate();
            if (res == 0) {
                System.out.println("删除失败");
                return 0;
            }
            System.out.println("删除成功");

            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid.close(null, preparedStatement, connection);
        }
    }


    /**
     * 查询数据
     *
     * @param connection 数据库连接
     * @param map        查询条件 "String, Object"
     * @return 查询结果，需要检查为空
     */

    public List<T> query(Connection connection, Map<String, Object> map, int start, int end) {
        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ? WHERE ");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append("=? AND ");
        }
        // 闭合and
        sqlBuilder.append("1=1");
        sqlBuilder.append(" LIMIT ?,?");

        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
            // 表名
            preparedStatement.setObject(conut, tableName);
            conut++;
            // 设置值
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                preparedStatement.setObject(conut, entry.getValue());
                conut++;
            }
            // 设置limit（分页）
            preparedStatement.setObject(conut, start);
            conut++;
            preparedStatement.setObject(conut, end);

            resultSet = preparedStatement.executeQuery();
            List<T> list = null;
            while (resultSet.next()) {
                // 将结果集转换为对象
                list = resultSetToList(resultSet, (Class<T>) this.getClass());
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Druid.close(resultSet, preparedStatement, connection);
        }
    }


    public int update(Connection connection, Map<String, Object> map, Map<String, Object> condition) {
        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ? SET ");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append("=?,");
        }
        // 闭合and
        sqlBuilder.append("1=1 WHERE ");
        for (String key : condition.keySet()) {
            sqlBuilder.append(key).append("=? AND ");
        }
        // 闭合and
        sqlBuilder.append("1=1");

        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
            // 表明
            preparedStatement.setObject(conut, tableName);
            conut++;

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                preparedStatement.setObject(conut, entry.getValue());
                conut++;
            }

            for (Map.Entry<String, Object> entry : condition.entrySet()) {
                preparedStatement.setObject(conut, entry.getValue());
                conut++;
            }

            int res = preparedStatement.executeUpdate();
            if (res == 0) {
                System.out.println("更新失败");
                return 0;
            }
            System.out.println("更新成功");

            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid.close(null, preparedStatement, connection);
        }
    }
}
