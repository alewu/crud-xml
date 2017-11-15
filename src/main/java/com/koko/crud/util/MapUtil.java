package com.koko.crud.util;

import com.koko.crud.common.response.Msg;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    private Map<String, Object> data = new HashMap<>();

    public static Map<String, Object> getMap(String... varargs) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < varargs.length; i++) {
            map.put("" +i, varargs[i]);
        }
        return map;
    }

    public static void main(String[] args) {
        String aop = "ceo";
        String ioc = "aaa";
        Map<String, Object> map = MapUtil.getMap(aop,ioc);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }

    }


    //链式操作返回信息
    public MapUtil put(String key, Object value){
        this.getData().put(key, value);
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
