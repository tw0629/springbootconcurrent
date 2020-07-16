package com.tian.concurrent.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author David Tian
 * @desc
 * @since 2019-08-17 16:55
 */
@ToString
@Data
@Builder
public class AuthorityThirdUserCacheInfo implements Serializable {

    private static final long serialVersionUID = 4242650439992419039L;

    private String appId;

    private String appName;

    /**
     * identityId:微信为openId,支付宝为uid
     */
    private String identityId;

    private String unionId;

    private String userId;

    private String userName;

    //域名
    private String domain;

    private String phone;

    private String shopCode;

    /**
     * 过期时间戳
     */
    private long expired;

    /**
     * 有效期时间长度
     */
    private long maxAge;

    /**
     * 来源
     */
    private String origin;

}
