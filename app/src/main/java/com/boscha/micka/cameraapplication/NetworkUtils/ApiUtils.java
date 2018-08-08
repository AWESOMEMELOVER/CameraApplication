package com.boscha.micka.cameraapplication.NetworkUtils;

import com.boscha.micka.cameraapplication.Instances.RetrofitClient;
import com.boscha.micka.cameraapplication.Interfaces.MonitorsApi;
import com.boscha.micka.cameraapplication.Interfaces.ServerApi;

/**
 * Created by micka on 8/8/2018.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://z.iport.net.ua:81/";

    public static ServerApi getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ServerApi.class);
    }



}
