package com.reactnativetimologin;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = TimoLoginModule.NAME)
public class TimoLoginModule extends ReactContextBaseJavaModule {
    public static final String NAME = "TimoLogin";

    public TimoLoginModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(double a, double b, Promise promise) {
        promise.resolve(a * b);
    }

    @ReactMethod
    public void checkCompanyExpiry(String company, String endpoint, Promise promise) {
      OkHttpClient client = new OkHttpClient.Builder().fastFallback(true).build();
      Request request = new Request.Builder().url(endpoint).build();
      Call call = client.newCall(request);
      try {
        Response response = call.execute();
        promise.resolve(response.body().string());
      } catch (IOException e) {
        e.printStackTrace();
        promise.reject("Custom Error", e);
      }
    }
}
