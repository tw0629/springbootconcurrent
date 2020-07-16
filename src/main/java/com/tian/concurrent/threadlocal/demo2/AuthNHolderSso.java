package com.tian.concurrent.threadlocal.demo2;

import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author David Tian
 * @desc
 * @since 2019-08-16 10:10
 */
public class AuthNHolderSso {

    private static final ThreadLocal<Map<String, String>> loginThreadLocal = new ThreadLocal();

    public AuthNHolderSso() {
    }

    public static void map(Map<String, String> map) {
        loginThreadLocal.set(map);
    }

    public static void clear() {
        Map<String, String> map = (Map)loginThreadLocal.get();
        if (map != null) {
            map.clear();
        }

    }

    public static Long iid() {
        String iid = get("iid");
        if (StringUtils.isEmpty(iid)) {
            return null;
        } else {
            try {
                return Long.parseLong(iid);
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static String appId() {
        return get("appId");
    }

    public static String appName() {
        return get("appName");
    }

    public static String identityId() {
        return get("identityId");
    }

    public static String unionId() {
        return get("unionId");
    }

    public static String userId() {
        return get("userId");
    }

    public static String userName() {
        return get("userName");
    }

    public static String domain() {
        return get("domain");
    }

    public static String phone() {
        return get("phone");
    }

    public static String expired() {
        return get("expired");
    }

    public static String maxAge() {
        return get("maxAge");
    }

    public static String origin() {
        return get("origin");
    }

    public static String get(String key) {
        Map<String, String> map = getMap();
        return (String)map.get(key);
    }

    public static Map<String, String> getMap() {
        Map<String, String> map = (Map)loginThreadLocal.get();
        return (Map)(map == null ? Maps.newHashMap() : map);
    }

}
