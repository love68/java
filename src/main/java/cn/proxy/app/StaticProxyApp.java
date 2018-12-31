package cn.proxy.app;

import cn.proxy.staticproxy.UserDao;
import cn.proxy.staticproxy.imp.UserDaoImp;
import cn.proxy.staticproxy.proxyobject.ProxyObject;

/**
 * Created by hasee on 2017/11/14.
 */
public class StaticProxyApp {


    public static void main(String [] args){
        UserDao userDao = new UserDaoImp();
        ProxyObject proxy = new ProxyObject(userDao);
        proxy.sing();
    }

}
