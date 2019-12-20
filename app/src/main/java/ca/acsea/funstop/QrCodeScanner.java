package ca.acsea.funstop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;

public class QrCodeScanner extends AppCompatActivity implements Serializable {

    FirebaseUser currentUser;
    DatabaseReference ref;

    public QrCodeScanner() {
    }
    public QrCodeScanner(FirebaseUser user, DatabaseReference ref) {

        this.currentUser=user;

    }
    SurfaceView cameraPreview;
    TextView txtResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    public static final int RequestCameraPermissionID = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.ref=FirebaseDatabase.getInstance().getReference();
        this.currentUser= FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("on create method"+ref);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_sacnner);
        Intent intent = getIntent();
        cameraPreview = (SurfaceView)findViewById(R.id.cameraView);
        txtResult = (TextView)findViewById(R.id.txtResult);
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
                System.out.println("oncreate release()");
                System.out.println("working in release method"+ref.child("users").child(currentUser.getUid()));
            }
            @Override
            public void receiveDetections(Detector.Detections < Barcode > detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();

                if (qrcodes.size() != 0) {

                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {

                            txtResult.setText(qrcodes.valueAt(0).displayValue);
                            System.out.println("working in receiveDetections method"+ref.child("users").child(currentUser.getUid()));
                            boolean s=true;
                            ref.child("users").child(currentUser.getUid()).child("QR").child(qrcodes.valueAt(0).displayValue).setValue(s);
                            QrCodeScanner.super.onBackPressed();

                        }
                    });



                }
            }
        });
    }

}