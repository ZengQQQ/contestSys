package com.game.dao.base;

import com.game.bean.ConditionBean;
import com.game.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class BaseDao<T> extends ReflectionUtils {
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

    public boolean insert(T object){
        Map<String,Object> map = mapFields(object);
        return insert(map);
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

    public int delete(T object){
        Map<String,Object> map = mapFields(object);
        return delete(map);
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

    public List<T> query(T object, int start, int end){
        Class<T> clazz= (Class<T>) object.getClass();
        Map<String,Object> map = mapFields(object);
        return query(clazz,map,start,end);
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

    public T querySingle(T object){
        Class<T> clazz= (Class<T>) object.getClass();
        Map<String,Object> map = mapFields(object);
        return querySingle(clazz,map);
    }

    public T querySingle(Class<T> clazz, Map<String, Object> map) {
        // 构建 SQL 查询语句
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
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
            int count = 1;
            // 设置值
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                preparedStatement.setObject(count, entry.getValue());
                count++;
            }

            resultSet = preparedStatement.executeQuery();

            // 如果结果集中存在数据，则将其转换为对象并返回
            // todo 应该根据类属性搜寻map中的值
            // 或者保证数据库中列与实体类完整对应
            // todo 此处修正user缺少u_info 造成的错误； 检查所有实体类的对应关系
            if (resultSet.next()) {
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
                return object;
            } else {
                // 如果结果集中没有数据，则返回null
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Druid.close(resultSet, preparedStatement, connection);
        }
    }










    /**
     * 用于比较两个map参数是否长度一致
     * @param map1 1
     * @param map2 2
     */
    public static void compareMaps(Map<?, ?> map1, Map<?, ?> map2) {
        if (map1.size() -1!= map2.size()) {
            throw new IllegalArgumentException("left_query参数错误：两个map的大小不一样");
        }
    }
    public static <T> void removeDuplicates(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i);
                size--;
                i--; // 每次移除一个元素后，需要回退一步，重新检查当前位置
            }
        }
    }

    public List<T> joinQuery(Class<T> clazz,String mainTable,List<Object> objects,int start,int end){
        Map<String,ConditionBean> conditions = new HashMap<>();
        Map<String,String> joinConditions = new HashMap<>();
        Map<String,Map<String,Object>> maps = new HashMap<>();
        for(Object object:objects){
            ConditionBean tem = mapFieldsKey(object);
            conditions.put(tem.getTableName(), tem);
            maps.put(tem.getTableName(),mapFields(object));
        }
        ArrayDeque<ConditionBean> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(new ConditionBean(mainTable, conditions.get(mainTable).getKeys()));
        conditions.remove(mainTable);
        while (true){
            if (conditions.isEmpty()){
                break;
            }
            ConditionBean leftTable=arrayDeque.pop();
            conditions.remove(leftTable.getTableName());
            for (Map.Entry<String, ConditionBean> entry : conditions.entrySet()){
                Set<String> intersection = new HashSet<>(entry.getValue().getKeys());
                intersection.retainAll(leftTable.getKeys());
                if(!intersection.isEmpty()){
                    arrayDeque.add(entry.getValue());
                    for(String tem :intersection){
                        StringBuilder conditionText = new StringBuilder();
                        conditionText.append(leftTable.getTableName());
                        conditionText.append(".");
                        conditionText.append(tem);
                        conditionText.append("=");
                        conditionText.append(entry.getKey());
                        conditionText.append(".");
                        conditionText.append(tem);
                        joinConditions.put(entry.getKey(),conditionText.toString());
                    }
                }
            }
        }
        return joinQuery(clazz,mainTable,maps,joinConditions,start,end);
    }


    public List<T> joinQuery(Class<T> clazz,String mainTable,List<Object> objects,Map<String,String> joinConditions,int start,int end){
        Map<String,ConditionBean> conditions = new HashMap<>();
        Map<String,Map<String,Object>> maps = new HashMap<>();
        for(Object object:objects){
            ConditionBean tem = mapFieldsKey(object);
            conditions.put(tem.getTableName(), tem);
            maps.put(tem.getTableName(),mapFields(object));
        }
        return joinQuery(clazz,mainTable,maps,joinConditions,start,end);
    }


    public List<T> joinQuery(Class<T> clazz,String mainTable,Map<String,Map<String, Object>> map,Map<String,String> joinCondition, int start, int end) {
        // 构建 SQL 查询语句
        // 构建？
        try {
            compareMaps(map,joinCondition);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(this.tableName);
        sqlBuilder.append(".* FROM ");
        sqlBuilder.append(mainTable);

        for (String key: joinCondition.keySet()){
            sqlBuilder.append(" JOIN ");
            sqlBuilder.append(key);
            sqlBuilder.append(" ON ");
            sqlBuilder.append(joinCondition.get(key));

        }
        sqlBuilder.append(" WHERE ");
        for (String key: map.keySet()) {
            for (String key1 : map.get(key).keySet()) {
                sqlBuilder.append(key).append(".").append(key1).append("=? AND ");
            }
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
            for (Map.Entry<String, Map<String,Object>> map1 : map.entrySet()){
                for (Map.Entry<String, Object> entry : map1.getValue().entrySet()) {
                    preparedStatement.setObject(conut, entry.getValue());
                    conut++;
                }
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



    public List<T> leftQuery(Class<T> clazz,String mainTable,List<Object> objects,int start,int end){
        Map<String,ConditionBean> conditions = new HashMap<>();
        Map<String,String> joinConditions = new HashMap<>();
        Map<String,Map<String,Object>> maps = new HashMap<>();
        for(Object object:objects){
            ConditionBean tem = mapFieldsKey(object);
            conditions.put(tem.getTableName(), tem);
            maps.put(tem.getTableName(),mapFields(object));
        }
        ArrayDeque<ConditionBean> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(new ConditionBean(mainTable, conditions.get(mainTable).getKeys()));
        conditions.remove(mainTable);
        while (true){
            if (conditions.isEmpty()){
                break;
            }
            ConditionBean leftTable=arrayDeque.pop();
            conditions.remove(leftTable.getTableName());
            for (Map.Entry<String, ConditionBean> entry : conditions.entrySet()){
                Set<String> intersection = new HashSet<>(entry.getValue().getKeys());
                intersection.retainAll(leftTable.getKeys());
                if(!intersection.isEmpty()){
                    arrayDeque.add(entry.getValue());
                    for(String tem :intersection){
                        StringBuilder conditionText = new StringBuilder();
                        conditionText.append(leftTable.getTableName());
                        conditionText.append(".");
                        conditionText.append(tem);
                        conditionText.append("=");
                        conditionText.append(entry.getKey());
                        conditionText.append(".");
                        conditionText.append(tem);
                        joinConditions.put(entry.getKey(),conditionText.toString());
                    }
                }
            }
        }
        return leftQuery(clazz,mainTable,maps,joinConditions,start,end);
    }


    public List<T> leftQuery(Class<T> clazz,String mainTable,List<Object> objects,Map<String,String> joinConditions,int start,int end){
        Map<String,ConditionBean> conditions = new HashMap<>();
        Map<String,Map<String,Object>> maps = new HashMap<>();
        for(Object object:objects){
            ConditionBean tem = mapFieldsKey(object);
            conditions.put(tem.getTableName(), tem);
            maps.put(tem.getTableName(),mapFields(object));
        }
        return leftQuery(clazz,mainTable,maps,joinConditions,start,end);
    }

    /**
     *  左连接查询,使用什么Dao就查该Dao对应的 table name 的主数据
     * @param clazz 获取主要数据格式
     * @param mainTable 主表
     * @param map 查询的数据Map<String,Map<String,Object>>
     * @param joinCondition 连接条件<表名，条件（需要带表名例如 team.tid=...）>
     * @param start 开始
     * @param end 结束
     * @return 返回list
     */
    public List<T> leftQuery(Class<T> clazz,String mainTable,Map<String,Map<String, Object>> map,Map<String,String> joinCondition, int start, int end) {
        // 构建 SQL 查询语句
        // 构建？
        try {
            compareMaps(map,joinCondition);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(this.tableName);
        sqlBuilder.append(".* FROM ");
        sqlBuilder.append(mainTable);

        for (String key: joinCondition.keySet()){
            sqlBuilder.append(" LEFT JOIN ");
            sqlBuilder.append(key);
            sqlBuilder.append(" ON ");
            sqlBuilder.append(joinCondition.get(key));

        }
        sqlBuilder.append(" WHERE ");
        for (String key: map.keySet()) {
            for (String key1 : map.get(key).keySet()) {
                sqlBuilder.append(key).append(".").append(key1).append("=? AND ");
            }
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
            for (Map.Entry<String, Map<String,Object>> map1 : map.entrySet()){
                for (Map.Entry<String, Object> entry : map1.getValue().entrySet()) {
                    preparedStatement.setObject(conut, entry.getValue());
                    conut++;
                }
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

    /**
     *
     * @param object1 更新成什么元素
     * @param object2 被更新的元素
     * @return 1 对 0 错
     */
    public int update(T object1,T object2){
        Map<String,Object> map1 = mapFields(object1);
        Map<String,Object> map2 = mapFields(object2);
        return update(map1,map2);
    }

    /**
     *
     *
     * @param map 更新成什么元素
     * @param condition 被更新的元素
     * @return 1 对 0 错
     */
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

    public int statistics(T object){
        Map<String, Object> map= mapFields(object);
        Class<T> clazz = (Class<T>) object.getClass();
        return statistics(clazz,map);
    }

    public int statistics(Class<T> clazz,Map<String, Object> map){
        return query(clazz,map,-1,-1).size();
    }

    public int statistics(List<Object> list){
        return list.size();
    }

    public static List<Object> formList(Object ... eles){
        return new ArrayList<>(Arrays.asList(eles));
    }



}
