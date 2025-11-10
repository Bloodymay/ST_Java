package ru.stqa.mantis.manager;

import okhttp3.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.CookieManager;
import java.rmi.RemoteException;

public class HttpSessionHelper extends HelperBase{
    OkHttpClient client;
    public HttpSessionHelper(AppManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();

    }


    public void login(String username, String pwd) throws IOException {
        RequestBody formBody = new FormBody.Builder().add("username", username).add("password", pwd).build();
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl")))
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            System.out.println(response.body().string());
        }
    }

    public boolean isLoggedIn() throws IOException {
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");

        }

    }
}
