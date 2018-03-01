package com.example.amyas.grocery.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main4Activity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{
    public static final int MAX_VALUE = 255;
    public static final int MID_VALUE = 127;
    public static final String TAG = "Main4Activity";
    @BindView(R.id.hue)
    SeekBar hue;
    @BindView(R.id.saturation)
    SeekBar saturation;
    @BindView(R.id.lum)
    SeekBar lum;
    @BindView(R.id.camila)
    ImageView Camila;

    // 色调饱满度亮度
    private float mHue, mSaturation, mLum;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mUnbinder = ButterKnife.bind(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camila);
        Camila.setImageBitmap(mBitmap);
        // seekar配置
        hue.setOnSeekBarChangeListener(this);
        lum.setOnSeekBarChangeListener(this);
        saturation.setOnSeekBarChangeListener(this);
        hue.setMax(MAX_VALUE);
        lum.setMax(MAX_VALUE);
        saturation.setMax(MAX_VALUE);
        hue.setProgress(MID_VALUE);
        lum.setProgress(MID_VALUE);
        saturation.setProgress(MID_VALUE);


    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.hue:
                mHue = (progress-MID_VALUE)*1.0f/MID_VALUE*180;
                Log.e(TAG, "onProgressChanged: mHue "+ mHue);
                break;
            case R.id.saturation:
                mSaturation = progress*1.0f/MID_VALUE;
                Log.e(TAG, "onProgressChanged: mSaturation "+mSaturation);
                break;
            case R.id.lum:
                mLum = progress*1.0f/MID_VALUE;
                Log.e(TAG, "onProgressChanged: mLum "+mLum);
                break;
        }
        Camila.setImageBitmap(handleImageEffect(mBitmap, mHue, mSaturation, mLum));
    }

    private Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum){
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        // 色调矩阵
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);
        // 饱和度矩阵
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        // 亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum,lum,1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm,0, 0, paint);
        return bmp;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
