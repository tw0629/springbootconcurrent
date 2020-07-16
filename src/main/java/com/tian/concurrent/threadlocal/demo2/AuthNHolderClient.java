package com.tian.concurrent.threadlocal.demo2;

/**
 * @author David Tian
 * @desc
 * @since 2019-08-26 19:21
 */
public class AuthNHolderClient extends AuthNHolderSso{

    public static String shopCode() {
        return get("shopCode");
    }
}
