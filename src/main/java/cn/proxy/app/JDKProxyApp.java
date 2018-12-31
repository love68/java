package cn.proxy.app;

import cn.proxy.JDKProxy.ProxyFactory;
import cn.proxy.staticproxy.UserDao;
import cn.proxy.staticproxy.imp.UserDaoImp;

/**
 * Created by hasee on 2017/11/14.
 */
public class JDKProxyApp {
    public static void main(String [] args){
        UserDao userDao = new UserDaoImp();
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        UserDao proxy = (UserDao) proxyFactory.getProxyInstance();
        proxy.sing();
    }
}
