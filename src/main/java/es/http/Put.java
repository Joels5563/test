package es.http;

import okhttp3.*;

import java.io.IOException;

/**
 * PUT请求
 *
 * @author joels
 * @create 2017-05-26 10:48
 **/
public class Put {
    private static final String LOCAL_URL = "http://localhost:9200";

    public static String put(String uri, String requestBody) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(LOCAL_URL + uri)
                .put(RequestBody.create(MediaType.parse("application/json"), requestBody))
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
        System.out.println(put("/megacorp/employee/1","{\n" +
                "    \"first_name\" : \"John\",\n" +
                "    \"last_name\" :  \"Smith\",\n" +
                "    \"age\" :        25,\n" +
                "    \"about\" :      \"I love to go rock climbing\",\n" +
                "    \"interests\": [ \"sports\", \"music\" ]\n" +
                "}"));
    }
}
