/**
 * 
 */
package com.spg.common.httputil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.spg.common.pojo.MyHttpResponse;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年12月2日 下午2:29:41
 * @version V1.0.0
 */
public class MyHttpUtil
{

    private static final Log LOGGER = LogFactory.getLog(MyHttpUtil.class);

    /**
     * @description: httpget请求
     * @author: Wind-spg
     * @param url
     * @param params
     * @param connectionTimeout
     *            {@link org.apache.http.params.CoreConnectionPNames#CONNECTION_TIMEOUT}
     * @param soTimeout
     *            {@link org.apache.http.params.CoreConnectionPNames#SO_TIMEOUT}
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static MyHttpResponse doHttpGet(String url, List<NameValuePair> params, int connectionTimeout,
            int soTimeout) throws URISyntaxException, IOException
    {
        LOGGER.debug(String.format("enter function, %s, %s", url, params));

        LOGGER.info(String.format("do http get start:%s", System.currentTimeMillis()));
        // for version 4.3+
        // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // CloseableHttpClient httpClient = httpClientBuilder.build();

        HttpClient httpClient = new DefaultHttpClient();
        // 连接时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
        // 数据传输时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
        // Get请求
        HttpGet httpget = new HttpGet(url);
        try
        {
            // for version 4.3+ 设置请求和传输超时时间
            // RequestConfig requestConfig =
            // RequestConfig.custom().setSocketTimeout(2000)
            // .setConnectTimeout(2000).build();
            // httpget.setConfig(requestConfig);

            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpResponse = httpClient.execute(httpget);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK)
            {
                LOGGER.error("Method failed:" + httpResponse.getStatusLine());
            }

            // 获取返回数据
            HttpEntity entity = httpResponse.getEntity();
            String body = EntityUtils.toString(entity, Charset.forName("UTF-8"));
            if (entity != null)
            {
                EntityUtils.consume(entity);
            }
            LOGGER.info(String.format("do http get end:%s", System.currentTimeMillis()));
            LOGGER.debug(String.format("http get response:%s", body));
            return new MyHttpResponse(statusCode, body, httpget.getURI().toString());
        } catch (ParseException e)
        {
            LOGGER.error("do http get error!", e);
            throw e;
        } catch (UnsupportedEncodingException e)
        {
            LOGGER.error("do http get error!", e);
            throw e;
        } catch (IOException e)
        {
            LOGGER.error("do http get error!", e);
            throw e;
        } catch (URISyntaxException e)
        {
            LOGGER.error("do http get error!", e);
            throw e;
        } finally
        {
            // try
            // {
            // // for 4.3+
            // httpClient.close();
            // } catch (IOException e)
            // {
            // LOGGER.error("close httpclient error!", e);
            // }
            httpget.releaseConnection();
            httpClient.getConnectionManager().shutdown();
        }
    }

    /**
     * @description: 发送httpPost请求
     * @author: Wind-spg
     * @param url
     * @param params
     * @param connectionTimeout
     *            {@link org.apache.http.params.CoreConnectionPNames#CONNECTION_TIMEOUT}
     * @param soTimeout
     *            {@link org.apache.http.params.CoreConnectionPNames#SO_TIMEOUT}
     * @return
     * @throws IOException
     */
    public static MyHttpResponse doHttpPost(String url, List<NameValuePair> params, int connectionTimeout,
            int soTimeout) throws IOException
    {
        LOGGER.debug(String.format("enter function, %s, %s", url, params));
        // // 创建HttpClientBuilder
        // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // // HttpClient
        // CloseableHttpClient client = httpClientBuilder.build();

        LOGGER.info(String.format("do http post start:%s", System.currentTimeMillis()));
        // 直接创建client
        // for version4.3+
        // CloseableHttpClient client = HttpClients.createDefault();
        HttpClient httpClient = new DefaultHttpClient();

        // 连接时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
        // 数据传输时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);

        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
        httpPost.setEntity(postEntity);
        LOGGER.debug(String.format("request line:%s", httpPost.getRequestLine()));
        try
        {
            // 执行post请求
            HttpResponse httpResponse = httpClient.execute(httpPost);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK)
            {
                LOGGER.error("Method failed:" + httpResponse.getStatusLine());
            }

            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
            if (entity != null)
            {
                EntityUtils.consume(entity);
            }
            LOGGER.debug(String.format("http post response:%s", result));
            LOGGER.info(String.format("do http post end:%s", System.currentTimeMillis()));
            return new MyHttpResponse(statusCode, result, httpPost.getURI().toString());
        } catch (IOException e)
        {
            LOGGER.error("do http post error!", e);
            throw e;
        } finally
        {
            // try
            // {
            // // 关闭流并释放资源,for version4.3+
            // client.close();
            // } catch (IOException e)
            // {
            // LOGGER.error("close httpclient error!", e);
            // }
            httpPost.releaseConnection();
            httpClient.getConnectionManager().shutdown();
        }
    }
}
