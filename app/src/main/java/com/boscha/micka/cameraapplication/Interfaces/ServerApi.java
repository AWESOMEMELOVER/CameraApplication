package com.boscha.micka.cameraapplication.Interfaces;

import android.graphics.Bitmap;

import com.boscha.micka.cameraapplication.Entities.Camera;
import com.boscha.micka.cameraapplication.Entities.User;

import java.io.InputStream;
import java.net.URI;
import java.util.List;


import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by micka on 8/8/2018.
 */

public interface ServerApi {

    @POST("/rest/getMonitors")
    Observable<List<Camera>> getMonitors(@Body User user);




}
