package com.example.amyas.customwidget.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.amyas.customwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main5Activity extends BaseActivity {
    public static final String TAG  = "Main5Activity";
    @BindView(R.id.camila)
    ImageView camila;
    @BindView(R.id.view_group)
    GridLayout viewGroup;
    @BindView(R.id.change)
    Button change;
    @BindView(R.id.reset)
    Button reset;

    private Bitmap mBitmap;
    private int mEtsWidth;
    private int mEtsHeight;
    private EditText[] mEts;
    private float[] mColorMatrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mUnbinder = ButterKnife.bind(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camila);
        camila.setImageBitmap(mBitmap);

        mEts = new EditText[20];
        mColorMatrix = new float[20];
        viewGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtsHeight = viewGroup.getHeight() / 4;
                mEtsWidth = viewGroup.getWidth() / 5;
                addEts();
                initEts();

            }
        });
    }

    /**
     * 初始化editText
     */
    private void initEts() {
        for (int i = 0; i < mEts.length; i++) {

            if (i%6== 0) {
                mEts[i].setText("1");
            } else {
                mEts[i].setText("0");
            }
        }
    }

    /**
     * 添加editTest到group中
     */
    private void addEts() {

        for (int i = 0; i < mEts.length; i++) {
            System.out.println("addEts: "+mEts.length);
//            Log.e(TAG, "addEts: "+mEts.length);
            mEts[i] = new EditText(this);
            viewGroup.addView(mEts[i],mEtsWidth, mEtsHeight);
        }
    }

    /**
     * 设置图片
     */
    @OnClick(R.id.change)
    public void btnChange() {
        getMatrix();
        setImageMatrix();
    }

    /**
     * 设置图片新矩阵
     */
    private void setImageMatrix() {
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(),
                mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        camila.setImageBitmap(bitmap);
    }

    /**
     * 重置图片
     */
    @OnClick(R.id.reset)
    public void btnReset() {
        initEts();
        getMatrix();
        setImageMatrix();
    }

    /**
     * 获取矩阵值
     */
    private void getMatrix() {
        for (int i = 0; i < mEts.length; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }
}
