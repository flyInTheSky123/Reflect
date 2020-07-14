package com.person.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

//  通过properties  读取文件文件中的 class,method的标签
public class Reflection {
    public static void main(String[] args) {
        //通过txt中的内容进行获取对象，使用方法
        String fileName = "src/main/java/com/person/config/reflection.txt";

        reflection(fileName);
    }

    //  通过properties  读取文件文件中的 class,method的标签
    public static void reflection(String fileName) {
        try {
            File file = new File(fileName);

            Properties properties = new Properties();

            //加载配置文件
            properties.load(new FileInputStream(file));

            //获取配置文件中的标签
            String aClass = (String) properties.get("class");
            String method = (String) properties.get("method");

            Class<?> clazz = Class.forName(aClass);
            Method menthod1 = clazz.getMethod(method);
            Constructor<?> constructor = clazz.getConstructor();
            Object o = constructor.newInstance();

            //该对象o 调用方法
            menthod1.invoke(o);


        } catch (Exception e) {
            System.err.println("msg:" + e.getMessage());
            e.printStackTrace();
        }

    }
}
