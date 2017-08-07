package es.http;

import okhttp3.*;

import java.io.IOException;

/**
 * http请求工具
 *
 * @author joels
 * @create 2017-05-26 11:21
 **/
public class HttpUtil {
    private static final String LOCAL_URL = "http://localhost:9200";

    public static String delete(String uri) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(LOCAL_URL + uri)
                .delete()
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
}
