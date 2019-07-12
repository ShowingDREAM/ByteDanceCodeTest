package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2_layout);

        View inflate = LayoutInflater.from(this).inflate(R.layout.im_list_item, null);
        int count = getViewCount(inflate);

        tv = findViewById(R.id.tv_countTheViews);
        tv.setText("" + count);
    }


    public static int getViewCount(View view) {
        //todo 补全你的代码
        View root = view;
        int viewCount = 0;
        if (null == root) {
            return 0;
        }
        if (root instanceof ViewGroup) {
            viewCount++;
            for (int i = 0; i < ((ViewGroup) root).getChildCount(); i++) {
                View z_view = ((ViewGroup) root).getChildAt(i);
                if (z_view instanceof ViewGroup) {
                    viewCount += getViewCount(z_view);
                } else {
                    viewCount++;
                }
            }
        }
        return viewCount;
    }
}
