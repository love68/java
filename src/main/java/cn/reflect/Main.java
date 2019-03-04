package cn.reflect;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    Class<?> person = null;

    @Before
    public void init() throws Exception {
        String string = "cn.reflect.Person";
        person = Class.forName(string);
    }

    /**
     * 调用无参构造
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        System.out.println(person);
        Person p = (Person) person.newInstance();//默认调用无参构造，如果没有无参构造，报错
        System.out.println(p.getId());
        System.out.println(p.getName());
    }

    /**
     * 调用有参构造，非私有
     */
    @Test
    public void test1() throws Exception {
        Constructor c = person.getConstructor(Long.class, String.class);
        Person p = (Person) c.newInstance(33L, "lisi");
        System.out.println(p.getName());
        System.out.println(p.getId());
    }

    /**
     * 调用有参构造，私有
     */
    @Test
    public void test11() throws Exception {
        Constructor c = person.getDeclaredConstructor(String.class);//获取私有的构造方法
        c.setAccessible(true);//取消限制
        Person p = (Person) c.newInstance( "lisi");
        System.out.println(p.getName());
        System.out.println(p.getId());
    }

    /**
     * 访问非私有的成员变量
     */
    @Test
    public void test2() throws Exception {
        Person p = (Person)person.newInstance();
        System.out.println(p.getName());

        Field name = person.getField("name");
        name.set(p,"lisi");
        System.out.println(p.getName());
    }

    /**
     * 访问私有的成员变量
     */
    @Test
    public void test3() throws Exception {
        Person p = (Person)person.newInstance();
        System.out.println(p.getId());

        Field id = person.getDeclaredField("id");
        id.setAccessible(true);
        id.set(p,66L);
        System.out.println(p.getId());
    }

    /**
     * 访问私有的方法
     */
    @Test
    public void test4() throws Exception {
        Person p = (Person)person.newInstance();
        Method method =  person.getDeclaredMethod("getSomeThing",String.class,Integer.class);
        method.setAccessible(true);
        String result = (String) method.invoke(p,"xxxxxxx",333);
        System.out.println(result);
    }

    @Test
    public void test5() throws IOException {
        System.out.println(person.getClassLoader());
        //path 不以’/'开头时默认是从此类所在的包下取资源，
        // 以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
        InputStream in = person.getResourceAsStream("text.txt");

        System.out.println(in.read());
    }


}
