package com.example.amyas.grocery;

import android.net.Uri;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String uri = Uri.encode("https://webview.cht.znrmny.com/couponList?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE4MTcwODc4ODQ5IiwiYWNjb3VudF9pZCI6IjIzIn0.NTjMa-FT_GbeiQ4QRFzwHnFs6J3dULVIPV0Rwx_QPOE");

        System.out.println(uri);
    }
    @Test
    public void testListToArray(){
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] array = list.toArray(new Integer[]{1});
    }
}