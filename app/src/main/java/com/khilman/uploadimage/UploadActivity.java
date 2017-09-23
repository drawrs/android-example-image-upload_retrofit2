package com.khilman.uploadimage;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.khilman.uploadimage.InitRetrofit.ApiServices;
import com.khilman.uploadimage.InitRetrofit.InitLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity implements ProgressRequestBody.UploadCallbacks {

    @BindView(R.id.imgPreview)
    ImageView imgPreview;
    @BindView(R.id.videoPreview)
    VideoView videoPreview;
    @BindView(R.id.txtPercentage)
    TextView txtPercentage;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btnUpload)
    Button btnUpload;
    private String filePath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);

        Intent i = getIntent();
        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");

        Log.d("File fath", "" + filePath);

        File file = new File(filePath);

        Log.d("File fath", "" + file);
        if (file.exists()){
            try {
                InputStream ims = new FileInputStream(file);
                imgPreview.setImageBitmap(BitmapFactory.decodeStream(ims));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }


    @OnClick(R.id.btnUpload)
    public void onViewClicked() {

        ApiServices api = InitLibrary.getInstance();

        File file = new File(filePath);
        // Progress bar
        ProgressRequestBody fileBody = new ProgressRequestBody(file ,this);

        //RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(), fileBody);
        Call<ResponseUpload> call = api.uploadPhoto("helllo", photo);
        call.enqueue(new Callback<ResponseUpload>() {
            @Override
            public void onResponse(Call<ResponseUpload> call, Response<ResponseUpload> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UploadActivity.this, "Berhasil dikirim", Toast.LENGTH_SHORT).show();
                    ResponseUpload data = response.body();
                    String message = data.getMsg();
                    String result = data.getResult();
                    // displaying on Log
                    Log.d("response", "" + data.getMsg());
                    Log.d("response", "" + data.getResult());
                    Log.d("response", "" + data.getFilename());

                    if (result.equals("true")){
                        Toast.makeText(UploadActivity.this, "" +message , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UploadActivity.this, "" +message , Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(UploadActivity.this, "Gagal mengirim request", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseUpload> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onProgressUpdate(int percentage) {
        // set current progress
        txtPercentage.setText(percentage + " %");
        progressBar.setProgress(percentage);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {
        // do something on upload finished
        // for example start next uploading at queue
        txtPercentage.setText("100 %");
        progressBar.setProgress(100);
    }

}
