package com.boscha.micka.cameraapplication.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boscha.micka.cameraapplication.Entities.Camera;
import com.boscha.micka.cameraapplication.Entities.User;
import com.boscha.micka.cameraapplication.Instances.RetrofitClient;
import com.boscha.micka.cameraapplication.Interfaces.MonitorsApi;
import com.boscha.micka.cameraapplication.Interfaces.ServerApi;
import com.boscha.micka.cameraapplication.NetworkUtils.ApiUtils;
import com.boscha.micka.cameraapplication.NetworkUtils.MonitorsUtils;
import com.boscha.micka.cameraapplication.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.synnapps.carouselview.CarouselView;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.cv_carousel)
    CarouselView carouselView;

    ServerApi serverApi;
    MonitorsApi monitorsApi;

    Subscription subscription;
    Call<List<Camera>> call;
    List<Camera> cameraList;
    boolean isDownloadedMonitorsList = false;
    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        serverApi = ApiUtils.getAPIService();


        /*call = serverApi.getMonitors(user);


        call.enqueue(new Callback<List<Camera>>() {
            @Override
            public void onResponse(Call<List<Camera>> call, Response<List<Camera>> response) {
                setCameraList(response.body());
                showResponce(cameraList);
                isDownloadedMonitorsList = true;
                carouselView.setPageCount(cameraList.size());
            }

            @Override
            public void onFailure(Call<List<Camera>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });*/








    }

    @Override
    protected void onStart() {
        super.onStart();
        User user = new User();
        user.setUser("iport");

        serverApi.getMonitors(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cameras -> cameraList = cameras,
                        throwable -> {},
                        ()->{

                            carouselView.setImageListener((position, imageView) -> {
                                monitorsApi = MonitorsUtils.getAPIService(cameraList.get(position).getZmUrl());
                                monitorsApi.getImageByCameraUrl(cameraList.get(position).getId())
                                        .subscribeOn(Schedulers.io())
                                        .delay(1,TimeUnit.SECONDS)
                                        .repeat()
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(body -> downloadImage(body,imageView),
                                                throwable -> throwable.printStackTrace());
                            });
                            carouselView.setPageCount(cameraList.size());
                        }
                );

       /* if(call.isExecuted()) {
            carouselView.setPageCount(cameraList.size());
            Log.i("@@@TEST", String.valueOf(carouselView.getPageCount()));
            carouselView.setImageListener((position, imageView) -> {
                serverApi.getImageByCameraUrl(cameraList.get(position).getZmUrl(), cameraList.get(position).getId())
                        .subscribeOn(Schedulers.io())
                        .delay(1, TimeUnit.SECONDS)
                        .repeat()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(body -> downloadImage(body, imageView),
                                throwable -> throwable.printStackTrace());
            });
        }*/
    }



    private void showResponce(List<Camera> cameras){
        for(Camera camera:cameras)
           Log.i("@@@CAMERA",camera.toString());

    }


    private void downloadImage(ResponseBody body,ImageView imageView){
        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = body.byteStream();

            Bitmap bitmap = BitmapFactory.decodeStream(in);

            imageView.setImageBitmap(bitmap);

        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
