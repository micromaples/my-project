package com.micromaple.my.project.common.dto;

import com.micromaple.my.project.common.constant.HttpStatus;

import java.util.HashMap;

/**
 * 返回结果
 * Title: BaseResult
 * Description:
 *
 * @author Micromaple
 */
public class BaseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 BaseResult 对象，使其表示一个空消息。
     */
    public BaseResult() {
    }

    /**
     * 初始化一个新创建的 BaseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public BaseResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 BaseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public BaseResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static BaseResult success() {
        return BaseResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static BaseResult success(Object data) {
        return BaseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static BaseResult success(String msg) {
        return BaseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static BaseResult success(String msg, Object data) {
        return new BaseResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static BaseResult error() {
        return BaseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static BaseResult error(String msg) {
        return BaseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static BaseResult error(String msg, Object data) {
        return new BaseResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static BaseResult error(int code, String msg) {
        return new BaseResult(code, msg, null);
    }
}
