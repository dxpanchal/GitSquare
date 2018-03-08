package app.gitsquare.com.gitsquare.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by admin on 23-11-2016.
 */

public class APIinterFace {

    public static interface getContributers {
        @GET("retrofit/contributors")
        Call<ResponseBody> responce(
                @Query("code") String code
        );

    }

}