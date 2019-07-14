package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View rootview = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = rootview.findViewById(R.id.animation_view2);
        linearLayout=rootview.findViewById(R.id.linerL);
        linearLayout.setAlpha(0f);
        //linearLayout.setVisibility(View.GONE);
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator =  ObjectAnimator.ofFloat(animationView,"alpha",1f,0f);
                animator.setDuration(3000);
                animator.start();
                ObjectAnimator animator2 =  ObjectAnimator.ofFloat(linearLayout,"alpha",0f,1f);
                animator2.setDuration(4000);
                animator2.start();
                //linearLayout.setVisibility(View.VISIBLE);
                //animationView.setVisibility(View.GONE);
            }
        }, 5000);
    }
}
