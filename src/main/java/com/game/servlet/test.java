package com.game.servlet;
import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        // 创建一个 Map 对象
        Map<String, Object> map = new HashMap<>();
        map.put("date", "2024-05-10");
        map.put("age", 30);
        map.put("value", "42");

        // 将 Map 对象转换为 JSON 字符串
        String jsonString = JSON.toJSONString(map);

        MyClass myClass = JSON.parseObject(jsonString, MyClass.class);

        // 打印 Java 对象
        System.out.println(myClass.getDate()); // 输出 2024-05-10
        System.out.println(myClass.getValue()); // 输出 42
    }
}
@Data
class MyClass {
    private LocalDate date;
    private Integer value;

    // Getter 和 Setter 方法
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}