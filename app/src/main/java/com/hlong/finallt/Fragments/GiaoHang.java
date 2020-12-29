package com.hlong.finallt.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hlong.finallt.Categories;
import com.hlong.finallt.CategoriesAdapter;
import com.hlong.finallt.QueryStore;
import com.hlong.finallt.R;
import com.hlong.finallt.SaleFood;
import com.hlong.finallt.SaleFoodAdapter;
import com.hlong.finallt.StoreAdapter1;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GiaoHang  extends AppCompatActivity {
    RecyclerView categoryCycle, saleFoodCycle, hotpickCycle;
    SaleFoodAdapter saleFoodAdapter;
    CategoriesAdapter categoriesAdapter;
    FusedLocationProviderClient fusedLocationProviderClient;
    ImageView img_get_location;
    TextView tvLocation;
    LinearLayout ln_select_location;
    SliderLayout sliderLayout;
    private final static int PLACE_PICKER_REQUEST = 1;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;

    RecyclerView rv;
    ArrayList<FoodStore> arrayList;
    StoreAdapter1 storeAdapter;
    QueryStore dsCuaHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaohang_activity);
        tvLocation = findViewById(R.id.tv_location);
        ln_select_location = findViewById(R.id.select_location);

        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSlideViews();
        categoryAdd(); //Category muc chon

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        ln_select_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(GiaoHang.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        getStore();
        hotsalePickAdd();
    }

    private void hotsalePickAdd() {
        List<SaleFood> hotpick = new ArrayList<>();
        hotpick.add(new SaleFood("Bánh tráng cô Tiên", "THCS Kim Đồng", R.drawable.ic_banhtrangtron));
        hotpick.add(new SaleFood("Trà sữa Phúc Long", "Phúc Long N.V.Linh", R.drawable.ic_trasua_phuclong));
        hotpick.add(new SaleFood("Gà rán JollyBean", "72 Mai Am", R.drawable.fried_chicken));
        hotpick.add(new SaleFood("Bánh tráng cô Tiên", "THCS Kim Đồng", R.drawable.ic_banhtrangtron));
        hotpick.add(new SaleFood("Trà sữa Phúc Long", "Phúc Long N.V.Linh", R.drawable.ic_trasua_phuclong));
        hotpick.add(new SaleFood("Gà rán KFC", "KFC BigC", R.drawable.fried_chicken));
        hotpick.add(new SaleFood("Bánh tráng cô Tiên", "THCS Kim Đồng", R.drawable.ic_banhtrangtron));
        hotpick.add(new SaleFood("Trà sữa Phúc Long", "Phúc Long N.V.Linh", R.drawable.ic_trasua_phuclong));
        hotpick.add(new SaleFood("Gà chiên phô mai", "THCS Kim Đồng", R.drawable.fried_chicken));
        setHotPickRecycler(hotpick);
    }

    private void setHotPickRecycler(List<SaleFood> hotpick) {
        hotpickCycle = findViewById(R.id.hot_pick_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        hotpickCycle.setLayoutManager(layoutManager);
        saleFoodAdapter = new SaleFoodAdapter(this, hotpick);
        hotpickCycle.setAdapter(saleFoodAdapter);
    }

    private void getStore() {
        dsCuaHang = new QueryStore(GiaoHang.this);
        arrayList = new ArrayList<FoodStore>();
        arrayList = dsCuaHang.getAll();
        rv = findViewById(R.id.hot_sale_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        storeAdapter = new StoreAdapter1(GiaoHang.this, arrayList);
        rv.setAdapter(storeAdapter);
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(GiaoHang.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        tvLocation.setText(addresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permission to be grandted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (ActivityCompat.checkSelfPermission(GiaoHang.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(GiaoHang.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

            }
        }
    }

    private void categoryAdd() {
        List<Categories> categories = new ArrayList<>();
        categories.add(new Categories("Tất cả", R.drawable.ic_all));
        categories.add(new Categories("Trà Sữa", R.drawable.milk_tea_more));
        categories.add(new Categories("Cơm", R.drawable.cafe));
        categories.add(new Categories("Ăn Vặt", R.drawable.milk_tea));
        categories.add(new Categories("Gà rán", R.drawable.fried_chicken));
        categories.add(new Categories("Pizza", R.drawable.asiafood1));
        categories.add(new Categories("Bánh xèo", R.drawable.asiafood1));
        categories.add(new Categories("Bánh kem", R.drawable.cake));
        setCategoriesRecycle(categories);
    }

    private void setCategoriesRecycle(List<Categories> categoriesList) {
        categoryCycle = findViewById(R.id.categories_recycle);
        categoriesAdapter = new CategoriesAdapter(this, categoriesList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryCycle.setLayoutManager(gridLayoutManager);

        categoryCycle.setAdapter(categoriesAdapter);
    }

    private void setSlideViews() {
        for (int i = 0; i <= 3; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(GiaoHang.this);
            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.slide1);

                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.slide2);

                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.slide3);

                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.slide4);

                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(GiaoHang.this, "This is Slider" + (finalI + 1), Toast.LENGTH_SHORT);
                }
            });
            sliderLayout.addSliderView(sliderView);
        }


    }
}
