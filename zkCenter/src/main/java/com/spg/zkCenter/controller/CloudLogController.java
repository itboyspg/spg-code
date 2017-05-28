package com.spg.zkCenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.spg.zkCenter.common.MongoConnection;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2017年5月28日 下午9:33:50
 * @version V1.0.0
 */
@Controller
@RequestMapping(value = "cloudLogCtrl")
public class CloudLogController extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(CloudLogController.class);

    @RequestMapping(value = "queryLog", produces =
    { "application/json;charset=utf-8" })
    @ResponseBody
    public String queryLog(@RequestParam String systemName, @RequestParam String keywords, @RequestParam String logLevel)
    {
        Map<String, List<Document>> logResult = new HashMap<String, List<Document>>();

        try
        {
            if (StringUtils.isBlank(systemName) || StringUtils.isBlank(keywords))
            {
                throw new IllegalArgumentException("请输入查询信息！");
            }
            MongoDatabase database = MongoConnection.getDB(systemName);
            MongoIterable<String> collection = database.listCollectionNames();
            Document filter = new Document();
            filter.append("message", MongoConnection.getLikePattern(keywords));
            if (StringUtils.isNotBlank(logLevel))
            {
                filter.append("level", logLevel);
            }

            for (String coll : collection)
            {
                if ("system.indexes".equals(coll))
                {
                    continue;
                }
                FindIterable<Document> result = database.getCollection(coll).find(filter);
                List<Document> newResult = new ArrayList<Document>();
                for (Document document : result)
                {
                    document.remove("_id");
                    newResult.add(document);
                }
                logResult.put(coll, newResult);
            }
        } catch (Exception e)
        {
            LOGGER.error("", e);
            return buildFailedResultInfo(-1, e.getMessage());
        }
        return buildSuccessResultInfo(logResult);
    }

}
