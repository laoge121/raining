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
     *
     * @param object
     * @return
     */
    public static RainResponse responseSuccess(Object object) {
        RainResponseBody rainResponseBody = new RainResponseBody();
        rainResponseBody.setBody(KryoUtil.writeObjectToString(object));
        rainResponseBody.setClassName(object.getClass().getName());
        rainResponseBody.setClassURI(object.getClass().getTypeName());
        RainResponseHead rainResponseHead = new RainResponseHead();
        rainResponseHead.setCode(0);
        rainResponseHead.setMessage("");
        RainResponse rainResponse = new RainResponse();
        rainResponse.setResponseBody(rainResponseBody);
        rainResponse.setResponseHead(rainResponseHead);
        return rainResponse;
    }

    /**
     * response 失败
     *
     * @param object
     * @return
     */
    public static RainResponse responseError(String object) {
        RainResponseBody rainResponseBody = new RainResponseBody();
        rainResponseBody.setBody(object);
        rainResponseBody.setClassName("String");
        rainResponseBody.setClassURI("java.lang.String");
        RainResponseHead rainResponseHead = new RainResponseHead();
        rainResponseHead.setCode(-1);
        rainResponseHead.setMessage(object);
        RainResponse rainResponse = new RainResponse();
        rainResponse.setResponseBody(rainResponseBody);
        rainResponse.setResponseHead(rainResponseHead);
        return rainResponse;
    }
}
