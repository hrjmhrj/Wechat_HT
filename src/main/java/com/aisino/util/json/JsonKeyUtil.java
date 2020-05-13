package com.aisino.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;

/**
 * @ClassName JsonKeyUtil
 * @Author Liujx
 * @Date 2019/10/11
 * @Description
 */
public class JsonKeyUtil {

    public static final String JSONOBJECT_KEY = "JSONObject";

    public static final String JSONARRAY_KEY = "JSONArray";

    /**
     * 修改json的key为大写或者小写
     *
     * @param transferMode 当值为true时，说明是转小写，false为转大写
     * @return
     * @throws
     * @Description
     * @Date 2019-3-7下午3:07:32
     */
    public static JSONObject transferJsonKey(Object object, boolean transferMode) {
        String s = JSON.toJSONString(object, new PascalNameFilter() {
            @Override
            public String process(Object source, String name, Object value) {
                String pascalName = "";
                if (name != null && name.length() != 0) {
                    if (transferMode) {
                        pascalName = name.toLowerCase();
                    } else {
                        pascalName = name.toUpperCase();
                    }
                    return pascalName;
                } else {
                    return name;
                }
            }
        });
        return JSONObject.parseObject(s);
    }

    /**
     * JSONArray key大小写转换
     *
     * @param jsonArray
     * @param transferMode
     * @return
     * @throws
     * @Description
     * @Date 2019-3-7下午3:28:30
     */
    public static JSONArray transferJsonArray(JSONArray jsonArray, boolean transferMode) {
        JSONArray array = new JSONArray();
        if (null != jsonArray && jsonArray.size() > 0) {
            for (Object object : jsonArray) {
                if (object.getClass().toString().endsWith(JSONOBJECT_KEY)) {
                    array.add(transferJsonKey(object, transferMode));
                } else if (object.getClass().toString().endsWith(JSONARRAY_KEY)) {
                    array.add(transferJsonArray((JSONArray) object, transferMode));
                }
            }
        }
        return array;
    }
}
