package com.example.security2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestHttpClient {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/test/one?test=1");
        httpGet.setHeader("Authorization", "Basic Mzk5MzkzOjExMTE=");

        try {
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            //对响应主体做一些有用的事情
            //并确保他完全被消耗掉
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
