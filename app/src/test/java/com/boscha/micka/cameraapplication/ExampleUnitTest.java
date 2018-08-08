package com.boscha.micka.cameraapplication;

import com.boscha.micka.cameraapplication.Entities.Camera;
import com.boscha.micka.cameraapplication.Entities.User;
import com.boscha.micka.cameraapplication.Interfaces.ServerApi;
import com.boscha.micka.cameraapplication.NetworkUtils.ApiUtils;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testHttp(){
        User user = new User();
        user.setName("iport");
        ServerApi api = ApiUtils.getAPIService();
        api.getMonitors(user).enqueue(new Callback<Camera>() {
            @Override
            public void onResponse(Call<Camera> call, Response<Camera> response) {
                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Camera> call, Throwable t) {
                showResponse("Error");
            }
        });
    }

    private void showResponse(String string){
        System.out.print(string);
    }
}