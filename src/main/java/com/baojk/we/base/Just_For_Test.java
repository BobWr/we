package com.baojk.we.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
public class Just_For_Test {

    public static void main(String[] args) {
        //        String aa = "<h1><a id=\"_0\"></a>你知道吗我是来测试的</h1> <p>9<sup>4</sup></p> <ol> <li>你好</li> <li>饿哦</li> "
        //                        + "<li>他还</li> <li>嘻嘻</li> </ol> ";
        //
        //        System.out.println(aa);
        //        System.out.println(aa.replaceAll("<p .*?>", "\r\n"));

        //        File file = new File("/Users/baojikui/Desktop/0A181029115904.cfw");
        //
        //        try {
        //            FileInputStream fileInputStream = new FileInputStream(file);
        //            InputStreamReader is = new InputStreamReader(fileInputStream, "utf-8");
        //
        //            FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/baojikui/Desktop/test
        // .txt"));
        //            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream,"utf-8");
        //
        //            char[] buf = new char[1000];
        //
        //            while (is.read(buf, 0, 1000) > 0) {
        //                osw.write(buf);
        //            }
        //            osw.flush();
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

//        RestTemplate restTemplate = new RestTemplate();
//        String uri = "http://icanhazip.com";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
//        System.out.println(strbody);
    }
}
