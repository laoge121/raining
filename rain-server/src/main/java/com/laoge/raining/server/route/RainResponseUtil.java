package com.laoge.raining.server.route;

import com.laoge.raining.common.route.RainResponse;
import com.laoge.raining.common.route.RainResponseBody;
import com.laoge.raining.common.route.RainResponseHead;
import com.laoge.raining.common.serialize.KryoUtil;

import java.io.Serializable;

/**
 * 返回数据处理
 */
public class RainResponseUtil implements Serializable {

    /**
     * response 成功
     * @param object
     * @return
     */
    public static RainResponse responseSuccess(Object object){
        RainResponseBody rainResponseBody = new RainResponseBody();
        rainResponseBody.setBody(KryoUtil.writeObjectToString(object));
        RainResponseHead rainResponseHead = new RainResponseHead();
        rainResponseHead.setClassName(object.getClass().getName());
        rainResponseHead.setClassRUI(object.getClass().getTypeName());
        RainResponse rainResponse = new RainResponse();
        rainResponse.setResponseBody(rainResponseBody);
        rainResponse.setResponseHead(rainResponseHead);
        return rainResponse;
    }

    /**
     * response 失败
     * @param object
     * @return
     */
    public static RainResponse responseError(String object){
        RainResponseBody rainResponseBody = new RainResponseBody();
        rainResponseBody.setBody(object);
        RainResponseHead rainResponseHead = new RainResponseHead();
        rainResponseHead.setClassName("String");
        rainResponseHead.setClassRUI("java.lang.String");
        RainResponse rainResponse = new RainResponse();
        rainResponse.setResponseBody(rainResponseBody);
        rainResponse.setResponseHead(rainResponseHead);
        return rainResponse;
    }
}
