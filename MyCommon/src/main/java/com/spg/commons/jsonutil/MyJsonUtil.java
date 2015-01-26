/**
 * 
 */
package com.spg.commons.jsonutil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * 项目名称：MyCommon
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年8月28日 下午2:43:53
 * 
 * @version V1.0.0
 * 
 */
public final class MyJsonUtil
{
    private static final Logger LOGGER = LogManager.getLogger();

    private MyJsonUtil()
    {

    }

    /**
     * json格式化
     * 
     * @param origJson
     *            需要格式化的json字符串
     * @return
     */
    public static String formatJson(String origJson)
    {
        if (null == origJson || origJson.isEmpty())
        {
            throw new IllegalArgumentException("param error, param is null!");
        }
        if (!isGoodJson(origJson))
        {
            throw new IllegalArgumentException("bad json, please check it!");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(origJson);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    /**
     * 判断字符串是否为合法的json格式
     * 
     * @param json
     * @return
     */
    public static boolean isGoodJson(String json)
    {
        if (null == json || json.isEmpty())
        {
            return false;
        }
        try
        {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e)
        {
            LOGGER.error("bad json: " + json);
            return false;
        }
    }
}
