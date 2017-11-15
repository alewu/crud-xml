package com.koko.crud.test.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class HttpTest {
    @Test
    public void test(){
        CloseableHttpClient  httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://192.168.1.153:80/rent/app/landlord/bill/findWeBills?renterIds=1");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
