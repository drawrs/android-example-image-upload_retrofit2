package com.khilman.uploadimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.type;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnCapturePicture)
    Button btnCapturePicture;
    String mCurrentPhotoPath;

    // variabel penyimpan nomor request
    private static final int ambil_foto_request_code = 100;
    public static final int type_foto_code = 1;

    // nama folder penyimpanan
    private Uri fileUri; // file url to store image/video
    // nama folder penyimpanan
    private static final String folder_foto = "AplikasiKameraku";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // konfirmasi permission
        if (
                Build.VERSION.SDK_INT >= 23
                        && checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                ) {
            requestPermissions(new String[]{
                    Manifest.permission.RECORD_AUDIO
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.CAMERA
            }, 0);
        } else {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }
        /// pengecekan kamera pada perangkat
        if (!supportCamera()) {
            Toast.makeText(this, "Tidak support kamera", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean supportCamera() {
        // cek aplikasi pada perangkat yg memiliki fitur kamera
        if (getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // proses kode selanjutnya
            return true;
        } else {
            // stop kode
            return false;
        }
    }

    @OnClick(R.id.btnCapturePicture)
    public void onBtnCapturePictureClicked() {
        captureImage();
    }
    /**
     * Checking device has camera hardware or not
     * */

    private void captureImage() {
        /// tampilkan aplikasi yg memiliki fitur foto
        Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // mengambil direktori enyimpanan foto dari metod pengaturan folder
        fileUri = ambilOutputMediaFileUri(type_foto_code);
        // kirim data penyimpanan ke aplikasi foto yg dipiih
        inten.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // tampilkan pilihan aplikasi
        startActivityForResult(inten, ambil_foto_request_code);
    }

    private Uri ambilOutputMediaFileUri(int type_foto_code) {
        // mengambil alamat directory file
        // return Uri.fromFile(ambilOutputMediaFile(type_foto_code));
        return FileProvider.getUriForFile(MainActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                ambilOutputMediaFile());
    }

    private File ambilOutputMediaFile() {
        // atur alamat penyimpanan (SDCard/Pictures/folder_foto)
        File penyimpananMediaDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                , folder_foto
        );
        Log.d("Directory Fileku", penyimpananMediaDir.getAbsolutePath());

        // cek keberadaan folder
        if (!penyimpananMediaDir.exists()) {
            // cek jika tidak bisa membuat folder baru
            if (!penyimpananMediaDir.mkdir()) {
                Toast.makeText(this, "Gagal membuat folder "
                        + folder_foto, Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        // simpan format tanggal saat pengambilan foto
        String waktu = new SimpleDateFormat("yyyyMMdd_hhMss"
                , Locale.getDefault()).format(new Date());
        Log.d("Waktu Pengambilan", waktu);

        // variabel penampung nama file
        File mediaFile;
        // pasang nama foto dengan waktu
        if (type_foto_code == type_foto_code) {
            mediaFile = new File(penyimpananMediaDir.getPath() + File.separator
                    + "IMG" + waktu + ".jpg");
            Log.d("Nama FIle", mediaFile.getAbsolutePath());
        } else {
            return null;
        }
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + mediaFile.getAbsolutePath();
        Log.d("mCurrentPhotoPath : ", mCurrentPhotoPath);
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // jika permintaan adalah code mengambil foto
        if (requestCode == ambil_foto_request_code && resultCode != 0) {
            // tampilkan gambar ke ImageView
            launchUploadActivity();
        } else if (resultCode == RESULT_CANCELED) {
            // jika user membatalkan memilih aplikasi pengambil foto
            Toast.makeText(this, "Batal mengambil foto", Toast.LENGTH_SHORT).show();
        } else {
            // jika gagal menampilkan request
            Toast.makeText(this, "Gagal mengambil foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchUploadActivity() {
        Uri imageUri = Uri.parse(mCurrentPhotoPath);
        Intent i = new Intent(MainActivity.this, UploadActivity.class);
        i.putExtra("filePath", imageUri.getPath());
        startActivity(i);
    }
}
