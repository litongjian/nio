package game.nio.server.service;

import com.alibaba.fastjson.JSONObject;
import game.nio.annotation.ProcessMethod;
import game.nio.annotation.ProcessService;
import game.nio.expr.ProcessFiledException;
import game.nio.expr.SystemException;
import game.nio.global.IService;
import game.nio.po.ReqData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.server.service
 * 概要：
 */
@ProcessService(uri="base")
public class ServerLoginService implements IService {

    private final Map<String,Method> processMap = new ConcurrentHashMap<>();

    public JSONObject process(ReqData param) throws ProcessFiledException, SystemException {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method:methods){
            if(method.isAnnotationPresent(ProcessMethod.class)){
                ProcessMethod pm = method.getAnnotation(ProcessMethod.class);
                Class<?> rt = method.getReturnType();
                Parameter[] params = method.getParameters();
                if (rt.isInstance(JSONObject.class)&&params!=null&&params.length==1&&params[0].getType().isInstance(ReqData.class)){
                    processMap.put(pm.uri(),method);
                }
            }
        }
        Method method = processMap.get(param.methodURI());
        if (method!=null){
            try {
                Object result = method.invoke(this,param);
                return (JSONObject)result;
            } catch (IllegalAccessException | InvocationTargetException e) {
            }
        }
        throw new SystemException();
    }
}
