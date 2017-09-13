package com.laoge.raining.server.invoke;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.laoge.raining.server.util.BeanUtil;
import com.laoge.raining.server.util.JacksonUtil;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 业务接口请求路由业务
 * Created by yuhou on 2017/9/12.
 */
@Service("rainInvokeService")
public class InvokeServiceImpl implements InvokeService.Iface {

    private static final Logger logger = LoggerFactory.getLogger(InvokeServiceImpl.class);

    @Override
    public String invoke(long callTime, String code, String path, String methodName, String paramJson) throws TException {

        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("路径参数异常!");
        }
        String className = path.substring(path.lastIndexOf(".") + 1, path.length());
        className = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
        Object object = BeanUtil.getBean(className);

        JsonNode jsonNode = null;
        Class[] args = null;
        List<Object> paramList = Lists.newArrayList();
        try {

            jsonNode = JacksonUtil.getMapper().readTree(paramJson);

            Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
            for (; iterator.hasNext(); ) {
                Map.Entry<String, JsonNode> map = iterator.next();
                Object obj = JacksonUtil.json2Bean(map.getValue().toString(), Class.forName(map.getKey()));
                paramList.add(obj);
            }
            args = new Class[paramList.size()];
            int i = 0;
            for (Object ob : paramList) {
                args[i] = ob.getClass();
                i++;
            }
        } catch (Exception e) {
            logger.error("解析数据异常!", e);
        }

        try {

            Method method = object.getClass().getMethod(methodName, args);
            Object result = method.invoke(object, paramList.toArray());
            return JacksonUtil.bean2Json(result);
        } catch (Exception e) {
            logger.error("方法执行异常{}", e);
            throw new RuntimeException(e);
        }
    }
}