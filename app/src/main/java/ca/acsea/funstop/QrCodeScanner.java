package ca.acsea.funstop;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.renderscript.Sampler;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QrCodeScanner extends AppCompatActivity implements Serializable {
    SurfaceView cameraPreview;
    TextView txtResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    private FunStopSub funStopSub;
    private MyPoint myPoint;
    public static final int RequestCameraPermissionID = 1001;
    String previousActivity;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    int points;
    String qrValue;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults){
        switch (requestCode){
            case RequestCameraPermissionID:{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(QrCodeScanner.this,
                                new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_sacnner);
        final Intent intent = getIntent();
        previousActivity = intent.getStringExtra("previous");
        cameraPreview = (SurfaceView)findViewById(R.id.cameraView);
        txtResult = (TextView)findViewById(R.id.txtResult);
        fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        System.out.println("Current points: "+points);
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640,480)
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(QrCodeScanner.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2){

            }
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder){
                cameraSource.stop();
            }

        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>(){
            @Override
            public void release() {
            }
            @Override
            public void receiveDetections(Detector.Detections < Barcode > detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    barcodeDetector.release();
                    //cameraPreview.setVisibility(View.INVISIBLE);

                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            cameraSource.stop();
                            txtResult.setText(qrcodes.valueAt(0).displayValue);
                            qrValue = qrcodes.valueAt(0).displayValue;
                            if(previousActivity.equals("MyPoints")){
                               // checkQrValue();
                                //detected = false;
                                //save(sharedPreferences);
                                Intent intent1  = new Intent(QrCodeScanner.this, MyPoint.class);
                                intent1.putExtra("qrValue", qrValue);
                                intent1.putExtra("source", "QrCodeScanner");
                                startActivity(intent1);
                                finish();
                            }else if(previousActivity.equals("FunStopSub")){
                                Intent intent1  = new Intent(QrCodeScanner.this, FunStopSub.class);
                                intent1.putExtra("qrValue", qrValue);
                                intent1.putExtra("source", "QrCodeScanner");
                                startActivity(intent1);
                                finish();
                            }

                        }
                    });
                }
            }
        });
    }
}

