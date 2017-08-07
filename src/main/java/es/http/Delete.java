package es.http;

import okhttp3.*;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

/**
 * DELETE请求
 *
 * @author joels
 * @create 2017-05-26 10:48
 **/
public class Delete {
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

    public static void main(String[] args) {
        System.out.println(delete("/megacorp/employee/1"));
    }
}
