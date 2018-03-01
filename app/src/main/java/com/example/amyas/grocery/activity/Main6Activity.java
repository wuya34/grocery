package com.example.amyas.grocery.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.amyas.grocery.MyApplication;
import com.example.amyas.grocery.R;
import com.example.amyas.grocery.bean.TestObjectBoxBean;
import com.example.amyas.grocery.bean.TestObjectBoxBean_;

import java.util.List;

import butterknife.ButterKnife;
import io.objectbox.Box;

public class Main6Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mUnbinder = ButterKnife.bind(this);
        Box<TestObjectBoxBean> boxStore = ((MyApplication) getApplication())
                .getBoxStore().boxFor(TestObjectBoxBean.class);
        //        boxStore.remove(7,8,9,10, 11,12);
        //        for (int i = 0; i < 6; i++) {
        //            TestObjectBoxBean boxBean = new TestObjectBoxBean();
        //            boxBean.setName("amyas"+i);
        //            boxStore.put(boxBean);
        //        }

        List<TestObjectBoxBean> list = boxStore.query()
                .startsWith(TestObjectBoxBean_.name, "amyas")
                .build()
                .find();
        long total = boxStore.query()
                .startsWith(TestObjectBoxBean_.name, "amyas")
                .build()
                .min(TestObjectBoxBean_.id);
        for (TestObjectBoxBean objectBoxBean : list) {
            Log.e(" test bean", "onCreate: " + objectBoxBean);
        }
        Log.e(" test bean", "onCreate: total " + total);

        List<TestObjectBoxBean> list1 = boxStore.query()
                .build()
                .find(2, 3);
        for (TestObjectBoxBean objectBoxBean : list) {
            Log.e(" test bean", "onCreate: " + objectBoxBean);
        }

    }
}
