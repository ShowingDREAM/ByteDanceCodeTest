package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 作业1：
 * 打印出Activity屏幕切换 Activity会执行什么生命周期？
 * 启动
 * 07-12 08:13:24.577 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onCreate:
 * 07-12 08:13:24.579 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onStart:
 * 07-12 08:13:24.587 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onResume:
 * 后台
 * 07-12 08:14:16.426 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onPause:
 * 07-12 08:14:16.809 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onStop:
 * 还原
 * 07-12 08:14:42.443 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onRestart:
 * 07-12 08:14:42.444 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onStart:
 * 07-12 08:14:42.444 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onResume:
 * 销毁
 * 07-12 08:15:08.796 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onPause:
 * 07-12 08:15:09.140 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onStop:
 * 07-12 08:15:09.140 9920-9920/chapter.android.aweme.ss.com.homework.new D/Exercises1: onDestroy:
 * 旋转
 * 07-12 07:02:32.442 7694-7694/chapter.android.aweme.ss.com.homework.new D/Exercises1: onPause:
 * 07-12 07:02:32.442 7694-7694/chapter.android.aweme.ss.com.homework.new D/Exercises1: onStop:
 * 07-12 07:02:32.442 7694-7694/chapter.android.aweme.ss.com.homework.new D/Exercises1: onDestroy:
 * 07-12 07:02:32.467 7694-7694/chapter.android.aweme.ss.com.homework.new D/Exercises1: onCreate:
 * 07-12 07:02:32.468 7694-7694/chapter.android.aweme.ss.com.homework.new D/Exercises1: onStart:
 * 07-12 07:02:32.493 7694-7694/chapter.android.aweme.ss.com.homework.new D/Exercises1: onResume:
 */
public class Exercises1 extends AppCompatActivity {
    private static final String TAG = "Exercises1";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
