package com.bytedance.videoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private android.widget.Button Button;
    private ImageButton imageButton2;

    private int screen_width, screen_height;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        String url = "https://s3.pstatp.com/toutiao/static/img/logo.271e845.png";
        Glide.with(this).load(url).into(imageView);
        setTitle("VideoView");
        initScreenWidthAndHeight();
        videoView = findViewById(R.id.video_View);
        videoView.setVideoPath(getVideoPath(R.raw.bytedance));
        videoView.start();
        initBind();//添加滑动seekbar
        Button = findViewById(R.id.Button);
        Button.setText("Pause");
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()) {
                    Button.setText("Start");
                    videoView.pause();
                } else {
                    Button.setText("Pause");
                    videoView.start();
                }
            }
        });

        imageButton2=findViewById(R.id.image_Button_full);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //videoView.
                videoView.start();
            }
        });
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
    private void initBind(){
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
    }

    /**
     * 获取屏幕的宽和屏幕的高
     */
    private void initScreenWidthAndHeight() {
        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 设置VideoView和最外层相对布局的宽和高
     * @param width : 像素的单位
     * @param height : 像素的单位
     */
    private void setVideoViewScale(int width, int height) {
        //获取VideoView宽和高
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        //赋值给VideoView的宽和高
        layoutParams.width = width;
        layoutParams.height = height;
        //设置VideoView的宽和高
        videoView.setLayoutParams(layoutParams);

        //同上
        ViewGroup.LayoutParams layoutParams1 = videoView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        videoView.setLayoutParams(layoutParams1);
    }

    /**
     * 监听屏幕方向的改变,横竖屏的时候分别做处理
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //当屏幕方向是横屏的时候,我们应该对VideoView以及包裹VideoView的布局（也就是对整体）进行拉伸
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        }
        //当屏幕方向是竖屏的时候，竖屏的时候的高我们需要把dp转为px
        else {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtils.dip2px(this,240));
        }
    }

}
