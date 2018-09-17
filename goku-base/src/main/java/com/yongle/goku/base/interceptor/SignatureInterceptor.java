package com.yongle.goku.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.Constants;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.vo.ResultVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;


/**
 * @author weinh
 */
public class SignatureInterceptor {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private HttpServletRequest request;

    public ResultVO checkSignature(ProceedingJoinPoint pjp) {
        ResultVO vo = new ResultVO(ErrorEnum.ERROR);
        try {
            Object[] args = pjp.getArgs();
            JSONObject jsonObject = new JSONObject();
            switch (getReqType(pjp)) {
                case Constants.REQ_JSON:
                    for (Object arg : args) {

                        if (arg.getClass().isAnnotationPresent(ResponseBody.class)) {
                            jsonObject = (JSONObject) JSONObject.toJSON(arg);
                        }
                    }
                    break;
                case Constants.REQ_PARAMS:
                    Enumeration parameterNames = request.getParameterNames();
                    while (parameterNames.hasMoreElements()) {
                        String param = parameterNames.nextElement().toString();
                        jsonObject.put(param, request.getParameter(param));
                    }
                    break;
                default:
                    break;
            }
            if (jsonObject.isEmpty()) {
                log.warn("提交参数为空，请检查");
            }
            if (request.getHeader(Constants.TIMESTAMP) == null) {
                //时间撮不存在或为空
                return new ResultVO(ErrorEnum.TIMESTAMP_EMPTY);
            } else if (Math.abs(System.currentTimeMillis() -
                    Long.parseLong(request.getHeader(Constants.TIMESTAMP)))
                    > Constants.DEVIATION_TIME) {
                //时间撮和当前时间相差10分钟以上
                return new ResultVO(ErrorEnum.TIMESTAMP_ERROR);
            }
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String param = headerNames.nextElement().toString();
                jsonObject.put(param, request.getHeader(param));
            }
            log.info("提交参数与header：{}", jsonObject);
            if (!createSignature(jsonObject).equals(jsonObject.getString(Constants.SIGNATURE))) {
                return new ResultVO(ErrorEnum.SIGNATURE_ERROR);
            }
            vo = (ResultVO) pjp.proceed();
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }
        return vo;
    }

    private String createSignature(JSONObject jsonObject) {
        List<String> list = new ArrayList<>();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            if (!key.equals(Constants.SIGNATURE)) {
                list.add(key + jsonObject.getString(key));
            }
        }

        String[] array = list.toArray(new String[]{});
        //字典序排序
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        for (String string : array) {
            //key1value1key2value2...串起来
            sb.append(string);
        }
        return new Md5Hash(sb.toString()).toString();
    }

    private String getReqType(ProceedingJoinPoint pjp) throws Exception {
        String targetName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] arguments = pjp.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String type = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    type = method.getAnnotation(Signature.class).type();
                    break;
                }
            }
        }
        return type;
    }
}
