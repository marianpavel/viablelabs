package ro.marianpavel.viablelabs.Interfaces;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.marianpavel.viablelabs.POJO.GenericPOJO;
import ro.marianpavel.viablelabs.POJO.HumanPOJO;

public interface RestApi {

    @GET("api")
    Observable<GenericPOJO<HumanPOJO[]>> getHumans(@Query("page") int page, @Query("results") int results);
}
