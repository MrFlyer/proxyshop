package top.mrflyer.shop.proxyshop;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class getSent {

    private static Logger logger = LoggerFactory.getLogger(getSent.class);
    private String URL = "";
    public String getinfo(int count) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String timeNow = dateTime.format(formatter);
//        System.out.println("已经触发" + count + "次" + "时间为： " + timeNow);

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(URL);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null){
                String response = EntityUtils.toString(httpEntity);
//                System.out.println(response);
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
                String errMsg = jsonObject.get("errMsg").getAsString();
                logger.info("已经触发" + count + "次" + "时间为： " + timeNow + " errMsg:" + errMsg);
            return errMsg;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("报错内容" + e);
        }
        return null;
    }
}
