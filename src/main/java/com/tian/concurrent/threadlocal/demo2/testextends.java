package com.tian.concurrent.threadlocal.demo2;

import com.tian.concurrent.model.AuthorityThirdUserCacheInfo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Tian
 * @desc
 * @since 2019-08-26 19:48
 */
public class testextends {


    @Test
    public void test1(){

        Map map = new HashMap();
        map.put("appId", "1");
        map.put("appName", "2");
        map.put("identityId", "3");
        map.put("unionId", "4");
        map.put("userId", "5");
        map.put("userName", "6");
        map.put("domain", "7");
        map.put("phone", "8");
        map.put("shopCode", "9");
        map.put("expired", "10");
        map.put("maxAge", "11");
        map.put("origin", "12");
        AuthNHolderSso.map(map);

        Map<String, String> mapSso = AuthNHolderSso.getMap();
        System.out.println("======AuthNHolderCSso.getMap=====>"+AuthNHolderSso.getMap());
        System.out.println("======AuthNHolderClient.getMap=====>"+AuthNHolderClient.getMap());

        mapSso.put("shopCode","13");
        AuthNHolderClient.map(map);
        System.out.println("======2AuthNHolderCSso.getMap=====>"+AuthNHolderSso.getMap());
        System.out.println("======2AuthNHolderClient.getMap=====>"+AuthNHolderClient.getMap());

        AuthNHolderSso.clear();

        String appId = AuthNHolderSso.get("appId");
        System.out.println("======appId=====>"+appId);
        String shopCode = AuthNHolderSso.get("shopCode");
        System.out.println("======shopCode=====>"+shopCode);
        String shopCode2 = AuthNHolderClient.get("shopCode");
        System.out.println("======shopCode2=====>"+shopCode2);


        AuthNHolderClient.clear();
    }
}
