package ro.marianpavel.viablelabs.Interfaces;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ro.marianpavel.viablelabs.POJO.HumanPOJO;

public interface RestApi {

    @GET("?page={page}&results=20")
    Observable<List<HumanPOJO>> getHumans(@Path("page") int page);
}
