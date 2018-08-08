package com.boscha.micka.cameraapplication.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

public interface MonitorsApi {


    @GET("/zm/cgi-bin/nph-zms?mode=single&scale=100&maxfps=5&buffer=1000&monitor=28&user=iport&connkey=602221&rand=1511870800")
    Observable<ResponseBody> getImageByCameraUrl();
}
