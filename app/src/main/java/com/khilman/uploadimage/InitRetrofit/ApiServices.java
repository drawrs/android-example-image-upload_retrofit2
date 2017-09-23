package com.khilman.uploadimage.InitRetrofit;

import com.khilman.uploadimage.ResponseUpload;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by root on 9/22/17.
 */

public interface ApiServices {
    @Multipart
    @POST("upload.php")
    Call<ResponseUpload> uploadPhoto(
            @Part("description") String description,
            @Part MultipartBody.Part photo
            );
}
