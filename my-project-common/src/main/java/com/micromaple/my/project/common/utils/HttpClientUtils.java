package com.micromaple.my.project.common.utils;

import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * HttpClient 工具类
 * Title: HttpClientUtils
 * Description:
 *
 * @author Micromaple
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_CONTENTTYPE = "application/json;charset=UTF-8";
    public static final String REQUEST_HEADER_CONSUMES = "application/json";

    //设置连接超时时间，单位毫秒。
    public static final int CONNECTTIMEOUT = 18000;
    //请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
    public static final int SOCKETTIMEOUT = 60000;
    //设置从connect Manager获取Connection 超时时间，单位毫秒
    public static final int CONNECTIONREQUESTTIMEOUT = 18000;


    /**
     * GET 请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return createRequest(url, GET, null, false, null, 0);
    }

    /**
     * GET 请求 - 代理
     *
     * @param url       请求地址
     * @param useProxy  是否需要代理
     * @param proxyHost 代理地址
     * @param proxyPort 代理端口
     * @return
     */
    public static String doGet(String url, boolean useProxy, String proxyHost, int proxyPort) {
        return createRequest(url, GET, null, useProxy, proxyHost, proxyPort);
    }

    /**
     * GET 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie) {
        return createRequest(url, GET, cookie, false, null, 0);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return createRequest(url, POST, null, false, null, 0, params);
    }

    /**
     * POST 请求 - 代理
     *
     * @param url       请求地址
     * @param useProxy  是否需要代理
     * @param proxyHost 代理地址
     * @param proxyPort 代理端口
     * @param params    请求参数（可选）
     * @return
     */
    public static String doPost(String url, boolean useProxy, String proxyHost, int proxyPort, BasicNameValuePair... params) {
        return createRequest(url, POST, null, useProxy, proxyHost, proxyPort, params);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, String cookie, BasicNameValuePair... params) {
        return createRequest(url, POST, cookie, false, null, 0, params);
    }

    /**
     * POST 请求 - 文本 方式
     *
     * @param url            请求地址
     * @param jsonBodyParams 请求参数 (JSON 格式)
     * @return
     */
    public static String doPostForRow(String url, String jsonBodyParams) {
        return createRequestForRow(url, jsonBodyParams, false, null, 0);
    }

    /**
     * POST 请求 - 文本 方式 - 代理
     *
     * @param url            请求地址
     * @param jsonBodyParams 请求参数
     * @param useProxy       是否需要代理
     * @param proxyHost      代理地址
     * @param proxyPort      代理端口
     * @return
     */
    public static String doPostForRow(String url, String jsonBodyParams, boolean useProxy, String proxyHost, int proxyPort) {
        return createRequestForRow(url, jsonBodyParams, useProxy, proxyHost, proxyPort);
    }

    /**
     * POST 请求
     *
     * @param url       请求地址
     * @param pMap      请求参数
     * @param useProxy  是否需要代理
     * @param proxyHost 代理地址
     * @param proxyPort 代理端口
     * @return
     */
    public static Map<String, Object> doPost(String url, Map<String, String> pMap, boolean useProxy, String proxyHost, int proxyPort) {
        String str = HttpClientUtils.doPost(url, useProxy, proxyHost, proxyPort, generatNameValuePair(pMap));
        Map<String, Object> rtnMap = Maps.newHashMap();
        try {
            rtnMap = MapperUtils.json2map(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * 创建请求
     *
     * @param url           请求地址
     * @param requestMethod 请求方式 GET/POST
     * @param cookie        cookie
     * @param useProxy      是否需要代理
     * @param proxyHost     代理地址
     * @param proxyPort     代理端口
     * @param params        请求参数 仅限于 POST 请求用
     * @return
     */
    private static String createRequest(String url, String requestMethod, String cookie, boolean useProxy, String proxyHost, int proxyPort, BasicNameValuePair... params) {
        //创建 HTTPClient 客户端
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient(useProxy, proxyHost, proxyPort);
        String result = null;

        try {
            //请求结果
            result = null;

            //请求方式
            HttpGet httpGet = null;
            HttpPost httpPost = null;

            //响应
            CloseableHttpResponse httpResponse = null;

            // GET 请求
            if (GET.equals(requestMethod)) {
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("Cookie", cookie);

                httpResponse = httpClient.execute(httpGet);
            }

            // POST 请求
            else if (POST.equals(requestMethod)) {
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("Cookie", cookie);

                //有参数进来
                if (params != null && params.length > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }

                httpResponse = httpClient.execute(httpPost);
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 创建 ROW POST 请求
     *
     * @param url           请求地址
     * @param jsonBodyParam 请求参数 (JSON 格式)
     * @param useProxy      是否需要代理
     * @param proxyHost     代理地址
     * @param proxyPort     代理端口
     * @return
     */
    private static String createRequestForRow(String url, String jsonBodyParam, boolean useProxy, String proxyHost, int proxyPort) {
        //创建 HTTPClient 客户端
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient(useProxy, proxyHost, proxyPort);
        String result = null;
        try {
            //请求结果
            result = null;

            //请求方式
            HttpPost httpPost = null;

            //响应
            CloseableHttpResponse httpResponse = null;

            httpPost = new HttpPost(url);
            httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
            httpPost.setHeader("Content-Type", REQUEST_HEADER_CONTENTTYPE);
            httpPost.setHeader("consumes", REQUEST_HEADER_CONSUMES);

            httpPost.setEntity(new StringEntity(jsonBodyParam, "UTF-8"));

            httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 创建 HttpClient 客户端
     *
     * @param useProxy  是否代理
     * @param proxyHost 代理地址
     * @param proxyPort 代理端口
     * @return
     */
    private static CloseableHttpClient getHttpClient(boolean useProxy, String proxyHost, int proxyPort) {
        CloseableHttpClient httpClient = null;
        if (useProxy) {
            //设置代理IP、端口、协议
            HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");

            //把代理设置到请求配置
            //setConnectTimeout：设置连接超时时间，单位毫秒。
            //setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
            //setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(CONNECTTIMEOUT)
                    .setSocketTimeout(SOCKETTIMEOUT)
                    .setConnectionRequestTimeout(CONNECTIONREQUESTTIMEOUT)
                    .setProxy(proxy)
                    .build();

            //创建 HTTPClient 客户端
            httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        } else {
            //setConnectTimeout：设置连接超时时间，单位毫秒。
            //setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
            //setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(CONNECTTIMEOUT)
                    .setSocketTimeout(SOCKETTIMEOUT)
                    .setConnectionRequestTimeout(CONNECTIONREQUESTTIMEOUT)
                    .build();

            //创建 HTTPClient 客户端
            httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        }

        return httpClient;
    }

    /**
     * MAP类型数组转换成BasicNameValuePair类型
     *
     * @param properties MAP类型数组
     * @return BasicNameValuePair类型数组
     */
    private static BasicNameValuePair[] generatNameValuePair(Map<String, String> properties) {
        BasicNameValuePair[] basicNameValuePairs = new BasicNameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            basicNameValuePairs[i++] = new BasicNameValuePair(entry.getKey(), entry.getValue());
        }
        return basicNameValuePairs;
    }
}