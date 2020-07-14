package com.person.test;

import com.person.pojo.Man;
import com.person.pojo.Person;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//反射机制
public class Test {

    public static void main(String[] args) {
        //   String className="Person";

        //文本路径
        String configPath = "src/main/java/com/person/config/person.properties";
        // testWithConfig(configPath);


        //   filed();
        declaredFiled();
    }


    //通过反射机制拿到Person对象
    @org.junit.Test
    public  void test() {
        try {
            Class aClass = Class.forName("com.person.pojo.Person");
            Constructor constructors = aClass.getConstructor();
            Person person = (Person) constructors.newInstance();
            person.setAge(12);
            person.setName("李薏");
            System.out.println(person.getName());

        } catch (Exception e) {
            System.err.println("msg:" + e.getMessage());
            e.printStackTrace();
        }


    }


    //读取properties文本中的 "com.person.pojo.Man"路径 ，然后通过反射机制 进行获取实例对象 ，调用方法

    public static void testWithConfig(String configPath) {
        File file = new File(configPath);
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("line:" + line);
                //获取类对象
                Class<?> aClass = Class.forName(line);
                //构造器
                Constructor constructor = aClass.getConstructor();
                //构造器实例化
                Man child = (Man) constructor.newInstance();
                //调用实例对象的方法
                child.doSomeThings();


            }


        } catch (Exception e) {
            System.err.println("msg:" + e.getMessage());
            e.printStackTrace();
        } finally {
            //  bufferedReader.close();
            if (bufferedReader != null) {
                try {
                    //关闭
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //拿到属性: getField 只能获取public的，包括从父类继承来的字段。
    @org.junit.Test
    public  void filed() {
        try {
            Man man = new Man("张三", 22, "男");
            Field gender = man.getClass().getField("gender");
            gender.set(man,"男性");
            System.out.println(man.getGender());


        } catch (Exception e) {
            System.err.println("msg:" + e.getMessage());
            e.printStackTrace();
        }


    }

    //getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。
    //  m(注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
    public static void declaredFiled() {
        Person person = new Person();
        person.setName("李薏");
        person.setAge(20);

        try {
            Field p = person.getClass().getDeclaredField("age");
            p.setAccessible(true);
            //  p.set(person,"张三");
            //   Field p1 = person.getClass().getField("name");

            p.set(person, 20);
            System.out.println(person.getAge());

        } catch (Exception e) {
            System.err.println("msg:" + e.getMessage());
            e.printStackTrace();
        }
    }

    //调用方法
    @org.junit.Test
    public  void testInvoke(){
        Person person = new Person();

        try {
            Method setName = person.getClass().getMethod("setName", String.class);
            setName.invoke(person,"何月");

            System.out.println(person.getName());
        } catch (Exception e) {
            System.err.println("msg:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
