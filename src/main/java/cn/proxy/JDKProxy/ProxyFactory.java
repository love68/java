package cn.proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hasee on 2017/11/14.
 */
public class ProxyFactory {
    public Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.print("方法调用之前！！！");
                Object result = method.invoke(object,args);
                System.out.print("方法调用之后！！！");
                return result;
            }
        });
    }
}
