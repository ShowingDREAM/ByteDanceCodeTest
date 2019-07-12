package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 大作业:实现一个抖音消息页面,所需资源已放在res下面
 */
public class Exercises3 extends AppCompatActivity {
    private List<String> strings = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        RecyclerView rvlist = findViewById(R.id.rv_list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.HORIZONTAL);
        rvlist.setLayoutManager(manager);
        initData();
        Myadapter adapter=new Myadapter(strings);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(adapter);
    }

    private void initData() {
        for(int i = 0 ;i < 100; i ++){
            String  te = "name" + i;
            strings.add(te);
        }
    }


}
