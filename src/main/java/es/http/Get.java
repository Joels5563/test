package es.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * GET请求
 *
 * @author joels
 * @create 2017-05-26 10:39
 **/
public class Get {
    private static final String LOCAL_URL = "http://localhost:9200";

    public static String get(String uri, String requestBody) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(LOCAL_URL + uri)
                .build();

        Response response;
        String responseBody = "";
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("服务器端错误: " + response);
            }
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    public static void main(String[] args) {
        System.out.println(get("/megacorp/employee/_search","{\n" +
                "  \"aggs\": {\n" +
                "    \"all_interests\": {\n" +
                "      \"terms\": { \"field\": \"interests\" }\n" +
                "    }\n" +
                "  }\n" +
                "}"));
    }
}
