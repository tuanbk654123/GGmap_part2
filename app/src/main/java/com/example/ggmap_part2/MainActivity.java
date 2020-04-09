package com.example.ggmap_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Spinner spinnerType;
    ArrayList<String>dsTpye;
    ArrayAdapter<String>adapterType;
    ProgressDialog progressDialog;
    //20.964504, 105.826307 Linh Dam
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
      
    }

    private void addEvent() {
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                XuLyDoiCheDoHienThi( position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
// xet xem bao h load xong map
     /*   mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
            progressDialog.dismiss();
            //Hiển thị vị trí ở đây
               *//* LatLng LinhDam = new LatLng(20.964504, 105.826307);
                mMap.addMarker(new MarkerOptions().position(LinhDam).title("Linh Dàm").snippet("My Home"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LinhDam,13));*//*
            }
        });*/
    }

    private void XuLyDoiCheDoHienThi(int position) {
        switch (position){
            case 0:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
        }
    }

    private void addControl() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spinnerType = (Spinner) findViewById(R.id.spinner);
        dsTpye = new ArrayList<>();
        dsTpye.addAll(Arrays.asList(getResources().getStringArray(R.array.arrayType)));
        adapterType = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,dsTpye);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông Báo");
        progressDialog.setMessage("Đang tải Map");
        progressDialog.setCanceledOnTouchOutside(false);
progressDialog.show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        LatLng LinhDam = new LatLng(20.964504, 105.826307);
        mMap.addMarker(new MarkerOptions().position(LinhDam).title("Linh Dàm").snippet("My Home"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LinhDam,13));
    }
}
