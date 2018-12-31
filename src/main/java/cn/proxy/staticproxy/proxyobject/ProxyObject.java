package cn.proxy.staticproxy.proxyobject;

import cn.proxy.staticproxy.UserDao;
import cn.proxy.staticproxy.imp.UserDaoImp;

/**
 * Created by hasee on 2017/11/14.
 */
public class ProxyObject implements UserDao {

    private UserDao userDao;

    public ProxyObject(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void sing() {
        System.out.print("大家好！！");
        userDao.sing();
        System.out.print("谢谢大家");
    }
}
