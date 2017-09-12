package com.laoge.raining.server.invoke;

import com.laoge.raining.server.util.BeanUtil;
import com.laoge.raining.server.util.GsonUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        String className = path.substring(path.lastIndexOf("."), path.length());

        Object object = BeanUtil.getBean(className);

        //将json数据进转化
        Map<String, Object> param = GsonUtils.getInstance().fromJson(paramJson, Map.class);

        Method[] methods = object.getClass().getMethods();
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                method = m;
            }
        }
        Object[] args = new Object[methods.length];
        Parameter[] parameters = method.getParameters();
        int i = 0;
        for (Parameter parameter : parameters) {
            args[i] = param.get(parameter.getName());
            i++;
        }

        try {
            Object result = this.invokeMethod(object, methodName, args);

            return GsonUtils.object2Json(result);
        } catch (Exception e) {
            logger.error("方法执行异常{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行特地的方法
     *
     * @param owner
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {

        Class ownerClass = owner.getClass();

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(owner, args);
    }


}
