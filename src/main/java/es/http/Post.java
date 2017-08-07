package es.http;

import okhttp3.*;

import java.io.IOException;

/**
 * POST请求
 *
 * @author joels
 * @create 2017-05-26 10:48
 **/
public class Post {
    private static final String LOCAL_URL = "http://localhost:9200";

    public static String post(String uri, String requestBody) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(LOCAL_URL + uri)
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody))
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
        System.out.println(post("/website/blog/","{\n" +
                "  \"title\": \"My second blog entry\",\n" +
                "  \"text\":  \"Still trying this out...\",\n" +
                "  \"date\":  \"2014/01/01\"\n" +
                "}"));
    }
}
