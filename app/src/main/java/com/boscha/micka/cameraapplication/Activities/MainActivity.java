package com.boscha.micka.cameraapplication.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.boscha.micka.cameraapplication.Entities.Camera;
import com.boscha.micka.cameraapplication.Entities.User;
import com.boscha.micka.cameraapplication.Interfaces.ServerApi;
import com.boscha.micka.cameraapplication.NetworkUtils.ApiUtils;
import com.boscha.micka.cameraapplication.R;
import com.synnapps.carouselview.CarouselView;


import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.cv_carousel)
    CarouselView carouselView;

    ServerApi serverApi;
    Subscription subscription;

    List<Camera> cameraList;


    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        serverApi = ApiUtils.getAPIService();









    }

    @Override
    protected void onStart() {
        super.onStart();

        User user = new User();
        user.setUser("iport");




    }




    private void getMonitors(User user){
         serverApi.getMonitors(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cameras -> cameraList = cameras,
                        throwable -> {},
                        ()->{
                            showResponce(cameraList);
                        })
                ;

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
