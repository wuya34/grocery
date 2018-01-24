package com.example.amyas.customwidget.calendar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.amyas.customwidget.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class CalendarActivity extends AppCompatActivity {
    public static final String TAG = "CalendarActivity";
    private MaterialCalendarView mMaterialCalendarView;
    private int count = 0;
    private CalendarDay mCalendarDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mMaterialCalendarView = findViewById(R.id.material);
        mMaterialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        mMaterialCalendarView.setOnDateChangedListener((widget, date, selected) -> {
            count++;
            Log.e(TAG, "onCreate: current date = "+mCalendarDay );
            Log.e(TAG, "onCreate: count = "+count);
//            Toast.makeText(CalendarActivity.this,"current date = "+mCalendarDay,Toast.LENGTH_SHORT).show();
//            Toast.makeText(CalendarActivity.this,"count = "+count,Toast.LENGTH_SHORT).show();
            if(count ==2 ){
                if (date.isAfter(mCalendarDay)){
                    Log.e(TAG, "onCreate: 完成");
//                    Toast.makeText(CalendarActivity.this,"完成",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    mMaterialCalendarView.clearSelection();
                    mMaterialCalendarView.setDateSelected(date,true);
                    mCalendarDay = date;
                    count = 1;
                    Log.e(TAG, "onCreate: else count = "+count);
                    return;
                }
            }
            mCalendarDay = date;
        });
    }
}
