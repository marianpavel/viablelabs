package ro.marianpavel.viablelabs.Singletons;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.marianpavel.viablelabs.BuildConfig;
import ro.marianpavel.viablelabs.Interfaces.RestApi;

/**
 * Singleton RestClient
 */
public class RestClient {

    private static RestClient ourInstance;
    private RestApi api;

    /**
     * Singleton behavior
     * @return RestClient instance
     */
    public static RestClient getInstance() {
        if (ourInstance == null) {
            ourInstance = new RestClient();
            ourInstance.createClient();
        }

        return ourInstance;
    }

    private void createClient() {

        /*
          Set timeout to the object
         */
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        /*
        Logging interceptor for logcat
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        /*
        application/json header
         */
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@android.support.annotation.NonNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = builder.build();

        /*
        We are using Gson to consume the rest api in POJO's
         */
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(RestApi.class);
    }

    public RestApi getApi() {
        return api;
    }

    private RestClient() {
    }
}
