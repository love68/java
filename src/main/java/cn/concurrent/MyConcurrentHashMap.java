package cn.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class MyConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("kk", "yy");
        System.out.println(map.get("kk"));
    }
}
