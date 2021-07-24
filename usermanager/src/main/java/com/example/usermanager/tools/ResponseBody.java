package com.example.usermanager.tools;

import lombok.Data;

/**
 * ClassName:ResponseBody
 * Package:com.example.usermanager.tools
 * Description:
 *
 * @Author:HP
 * @date:2021/7/24 9:20
 */
@Data
public class ResponseBody<T> {
    private int status;
    private String message;
    private T data;

    public ResponseBody(int status,String message,T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
