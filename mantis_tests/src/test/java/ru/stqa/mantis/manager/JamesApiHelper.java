package ru.stqa.mantis.manager;

import okhttp3.*;
import org.openqa.selenium.os.ExternalProcess;
import ru.stqa.mantis.model.Credentials;

import java.io.IOException;
import java.net.CookieManager;
import java.time.Duration;
import java.util.Locale;

public class JamesApiHelper extends HelperBase{
    OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json");
    public JamesApiHelper(AppManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(Credentials credentials) throws InterruptedException {
        RequestBody body = RequestBody.create(String.format("{\"password\":\"%s\"}", credentials.password()), JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s@localhost", manager.property("api.baseUrl"), credentials.username()))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            System.out.println(response.body().string());
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
