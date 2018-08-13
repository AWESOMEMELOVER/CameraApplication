package com.boscha.micka.cameraapplication.NetworkUtils;

import android.util.Log;

import com.boscha.micka.cameraapplication.Instances.RetrofitClient;
import com.boscha.micka.cameraapplication.Interfaces.MonitorsApi;

/**
 * Created by micka on 8/13/2018.
 */

public class MonitorsUtils {

    private MonitorsUtils() {}


    public static MonitorsApi getAPIService(String BASE_URL) {
        Log.i("@@@BASE URL",BASE_URL);
        return RetrofitClient.getClient(BASE_URL).create(MonitorsApi.class);
    }


}
