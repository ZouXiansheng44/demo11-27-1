package com.example.demo.VO;

import lombok.Data;

@Data
public class ResultVO<T>
{

    /**消息码 */
    private Integer code;
    /**提示信息 */
    private String msg;
    /**具体数据 */
    private T data;

}
