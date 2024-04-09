package com.game.dao.base;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseDao<T> {
    public String tableName;

    public BaseDao() {}

    public BaseDao(String tableName) {
        this.tableName = tableName;
    }

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
                if (fieldType == Integer.class) {
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
     * @param map        插入的数据 "String, Object"
     * @return 是否插入成功
     */
    public boolean insert(Map<String, Object> map) {

        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO "+tableName+" ");
        // 拼接key
        sqlBuilder.append("(");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append(",");
        }
        // 删除末尾多余的逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(")");
        // 拼接value的？
        sqlBuilder.append(" VALUES (");
        sqlBuilder.append("?,".repeat(map.size()));
        // 删除末尾多余的逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(")");
        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {

            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
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
     * @param map 删除条件 "String, Object"
     * @return 删除的行数
     */
    public int delete(Map<String, Object> map) {
        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("DELETE FROM "+tableName+" WHERE ");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append("=? AND ");
        }
        // 闭合and
        sqlBuilder.append("1=1");

        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
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
     * @param map        查询条件 "String, Object"
     * @return 查询结果，需要检查为空
     */

    public List<T> query(Class<T> clazz,Map<String, Object> map, int start, int end) {
        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM "+tableName+" WHERE ");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append("=? AND ");
        }
        // 闭合and
        sqlBuilder.append("1=1");
        if (start>=0 && end >=start){
            sqlBuilder.append(" LIMIT ?,?");
        }


        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
            // 设置值
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                preparedStatement.setObject(conut, entry.getValue());
                conut++;
            }
            // 设置limit（分页）
            if (start>=0 && end >=start) {
                preparedStatement.setObject(conut++, start);
                preparedStatement.setObject(conut++, end);
            }
            resultSet = preparedStatement.executeQuery();

            List<T> list = new ArrayList<>();
            // 将结果集转换为对象
            //list = resultSetToList(resultSet, (Class<T>) this.getClass());
            while (resultSet.next()) {
                // 根据泛型类的实际类型创建对象实例
                T object = clazz.newInstance();
                // 从 ResultSet 中读取数据，并设置到对象的属性中
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    // 使用反射设置对象的属性值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(object, value);
                }
                list.add(object);
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


    public int update(Map<String, Object> map, Map<String, Object> condition) {
        // 构建 SQL 查询语句
        // 构建？
        StringBuilder sqlBuilder = new StringBuilder("UPDATE "+tableName+" SET ");
        for (String key : map.keySet()) {
            sqlBuilder.append(key).append("=?,");
        }
        // 删除末尾多余的逗号和空格
        sqlBuilder.delete(sqlBuilder.length() - 1, sqlBuilder.length());
        // 闭合and
        sqlBuilder.append(" WHERE ");
        for (String key : condition.keySet()) {
            sqlBuilder.append(key).append("=? AND ");
        }
        // 闭合and
        sqlBuilder.append("1=1");

        System.out.println("预制sql： " + sqlBuilder.toString());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数
            int conut = 1;
            // 设置更新内容
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                preparedStatement.setObject(conut, entry.getValue());
                conut++;
            }
            // 设置更新条件
            for (Map.Entry<String, Object> entry : condition.entrySet()) {
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

    public int statistics(){
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) AS total_rows FROM "+tableName);
        System.out.println("预制sql： " + sqlBuilder.toString());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Druid.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            if (resultSet.next()) {
                // 获取结果集中的数据条数
                int totalRows = resultSet.getInt("total_rows");
                System.out.println("Total rows in the table: " + totalRows);
                return totalRows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
