package com.thuytrinh.android.collageviewsdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.github.siyamed.shapeimageview.DiamondImageView;
import com.github.siyamed.shapeimageview.HeartImageView;
import com.github.siyamed.shapeimageview.OctogonImageView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.thuytrinh.android.collageviews.MultiTouchListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends Activity {
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContainer = (FrameLayout) findViewById(R.id.container);

        findViewById(R.id.collageBgView).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return true;
            }
        });
/*
        findViewById(R.id.collageView1).setOnTouchListener(new MultiTouchListener());
        findViewById(R.id.collageView2).setOnTouchListener(new MultiTouchListener());
        findViewById(R.id.collageView3).setOnTouchListener(new MultiTouchListener());
        findViewById(R.id.collageView4).setOnTouchListener(new MultiTouchListener());*/
        findViewById(R.id.picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(HomeActivity.this, AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 10);
                startActivityForResult(intent, Constants.REQUEST_CODE);*/
                showColorPicker();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0, l = images.size(); i < l; i++) {
                stringBuffer.append(images.get(i).path + "\n");
                File file = new File(images.get(i).path);
                Uri imageUri = Uri.fromFile(file);

                RoundedImageView roundedImageView = new RoundedImageView(this);

                roundedImageView.setRadius(20);
                roundedImageView.setBorderWidth(10);
                roundedImageView.setBorderColor(getResources().getColor(R.color.multiple_image_select_primary));

                roundedImageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                // or ViewGroup.LayoutParams.WRAP_CONTENT
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                roundedImageView.setOnTouchListener(new MultiTouchListener());


//diamond iamge view

                DiamondImageView diamondImageView=new DiamondImageView(this);
                diamondImageView.setBorderWidth(20);
                diamondImageView.setBorderColor(getResources().getColor(R.color.multiple_image_select_primary));

                diamondImageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                // or ViewGroup.LayoutParams.WRAP_CONTENT
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                diamondImageView.setOnTouchListener(new MultiTouchListener());


                //Heart Image view

                HeartImageView heartImageView=new HeartImageView(this);
                heartImageView.setBorderWidth(20);
                heartImageView.setBorderColor(getResources().getColor(R.color.multiple_image_select_primary));

                heartImageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                // or ViewGroup.LayoutParams.WRAP_CONTENT
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                heartImageView.setOnTouchListener(new MultiTouchListener());





                //Octagon Image view

                OctogonImageView octogonImageView=new OctogonImageView(this);
                octogonImageView.setBorderWidth(20);
                octogonImageView.setBorderColor(getResources().getColor(R.color.multiple_image_select_primary));

                octogonImageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                // or ViewGroup.LayoutParams.WRAP_CONTENT
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                octogonImageView.setOnTouchListener(new MultiTouchListener());







                CollageView collageView = new CollageView(this);
                collageView.setOnTouchListener(new MultiTouchListener());
                collageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                // or ViewGroup.LayoutParams.WRAP_CONTENT
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));

                // collageView.setScaleType(ImageView.ScaleType.CENTE);
                mContainer.addView(collageView);
                mContainer.addView(roundedImageView);
                mContainer.addView(diamondImageView);
                mContainer.addView(heartImageView);
                mContainer.addView(octogonImageView);


                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    roundedImageView.setImageBitmap(mBitmap);
                    diamondImageView.setImageBitmap(mBitmap);
                    heartImageView.setImageBitmap(mBitmap);
                    octogonImageView.setImageBitmap(mBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(this)
                        .load(imageUri)
                        .into(collageView);
               /* Glide.with(this)
                        .load(imageUri)
                        .into((ImageView) findViewById(R.id.custom_view));*/


             octogonImageView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     OctogonImageView octogonImageView1= (OctogonImageView) view;
                      showColorPicker();
                 }
             });
            }

        }
    }

    private void showColorPicker()
    {
        ColorPickerDialogBuilder

    }
}