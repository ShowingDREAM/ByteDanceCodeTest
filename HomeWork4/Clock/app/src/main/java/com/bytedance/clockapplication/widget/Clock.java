package com.bytedance.clockapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Locale;

public class Clock extends View {


    private Handler mHandler;
    private Runnable mRunnable;
    private  boolean isRunning;

    private static final int FULL_ANGLE = 360;

    private static final int CUSTOM_ALPHA = 140;
    private static final int FULL_ALPHA = 255;

    private static final int DEFAULT_PRIMARY_COLOR = Color.WHITE;
    private static final int DEFAULT_SECONDARY_COLOR = Color.LTGRAY;

    private static final float DEFAULT_DEGREE_STROKE_WIDTH = 0.010f;

    public final static int AM = 0;

    private static final int RIGHT_ANGLE = 90;

    private int mWidth, mCenterX, mCenterY, mRadius;
    private static final String TAG = "Clock";
    /**
     * properties
     */
    private float hourAngle,minAngle,secAngle;
    private int centerInnerColor;
    private int centerOuterColor;

    private int secondsNeedleColor;
    private int hoursNeedleColor;
    private int minutesNeedleColor;

    private int degreesColor;

    private int hoursValuesColor;

    private int numbersColor;

    private boolean mShowAnalog = true;

    public Clock(Context context) {
        super(context);
        init(context, null);
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int size;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        if (widthWithoutPadding > heightWithoutPadding) {
            size = heightWithoutPadding;
        } else {
            size = widthWithoutPadding;
        }

        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size + getPaddingTop() + getPaddingBottom());
    }

    private void init(Context context, AttributeSet attrs) {

        this.centerInnerColor = Color.LTGRAY;
        this.centerOuterColor = DEFAULT_PRIMARY_COLOR;

        this.secondsNeedleColor = DEFAULT_SECONDARY_COLOR;
        this.hoursNeedleColor = DEFAULT_PRIMARY_COLOR;
        this.minutesNeedleColor = DEFAULT_PRIMARY_COLOR;

        this.degreesColor = DEFAULT_PRIMARY_COLOR;

        this.hoursValuesColor = DEFAULT_PRIMARY_COLOR;

        numbersColor = Color.WHITE;

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                postInvalidate();
                mHandler.postDelayed(this,1000);
            }
        };
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        if(!isRunning){
            runClock();
        }
        else{
            Log.d(TAG, ""+isRunning);
            mWidth = getHeight() > getWidth() ? getWidth() : getHeight();

            int halfWidth = mWidth / 2;
            mCenterX = halfWidth;
            mCenterY = halfWidth;
            mRadius = halfWidth;

            if (mShowAnalog) {
                drawDegrees(canvas);
                drawHoursValues(canvas);
                drawNeedles(canvas);
                drawCenter(canvas);
            } else {
                drawNumbers(canvas);
            }
        }

    }

    private void runClock() {
        isRunning=true;
        mHandler.postDelayed(mRunnable,1000);
    }

    private void drawDegrees(Canvas canvas) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(mWidth * DEFAULT_DEGREE_STROKE_WIDTH);
        paint.setColor(degreesColor);

        int rPadded = mCenterX - (int) (mWidth * 0.01f);
        int rEnd = mCenterX - (int) (mWidth * 0.05f);

        for (int i = 0; i < FULL_ANGLE; i += 6 /* Step */) {

            if ((i % RIGHT_ANGLE) != 0 && (i % 15) != 0)
                paint.setAlpha(CUSTOM_ALPHA);
            else {
                paint.setAlpha(FULL_ALPHA);
            }

            int startX = (int) (mCenterX + rPadded * Math.cos(Math.toRadians(i)));
            int startY = (int) (mCenterX - rPadded * Math.sin(Math.toRadians(i)));

            int stopX = (int) (mCenterX + rEnd * Math.cos(Math.toRadians(i)));
            int stopY = (int) (mCenterX - rEnd * Math.sin(Math.toRadians(i)));

            canvas.drawLine(startX, startY, stopX, stopY, paint);

        }
    }

    /**
     * @param canvas
     */
    private void drawNumbers(Canvas canvas) {

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(mWidth * 0.2f);
        textPaint.setColor(numbersColor);
        textPaint.setColor(numbersColor);
        textPaint.setAntiAlias(true);

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int amPm = calendar.get(Calendar.AM_PM);

        String time = String.format("%s:%s:%s%s",
                String.format(Locale.getDefault(), "%02d", hour),
                String.format(Locale.getDefault(), "%02d", minute),
                String.format(Locale.getDefault(), "%02d", second),
                amPm == AM ? "AM" : "PM");

        SpannableStringBuilder spannableString = new SpannableStringBuilder(time);
        spannableString.setSpan(new RelativeSizeSpan(0.3f), spannableString.toString().length() - 2, spannableString.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // se superscript percent

        StaticLayout layout = new StaticLayout(spannableString, textPaint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1, 1, true);
        canvas.translate(mCenterX - layout.getWidth() / 2f, mCenterY - layout.getHeight() / 2f);
        layout.draw(canvas);
    }

    /**
     * Draw Hour Text Values, such as 1 2 3 ...
     *
     * @param canvas
     */
    private void drawHoursValues(Canvas canvas) {
        // Default Color:
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);//画笔模式：描边+填充
        paint.setStrokeCap(Paint.Cap.ROUND);//线帽，起始结束时候，线头/尾的形状
        paint.setStrokeWidth(1f);//线宽
        paint.setColor(degreesColor);
        paint.setAntiAlias(true);//为抗锯齿
        paint.setTextSize(32);
        paint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < 12; i++) {
            String number = 6 + i < 12 ? String.valueOf(6 + i) : (6 + i) > 12
                    ? String.valueOf(i - 6) : "12";
            canvas.save();//画布将当前的状态保存
            canvas.translate(0, mRadius * 5.5f / 7);
            canvas.rotate(-i * 30);
            canvas.drawText(number, mCenterX, mCenterY, paint);
            canvas.restore();//画布取出原来所保存的状态
            canvas.rotate(30);
        }
    }

    /**
     * Draw hours, minutes needles
     * Draw progress that indicates hours needle disposition.
     *
     * @param canvas
     */
    private void drawNeedles(final Canvas canvas) {
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);


        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(hoursNeedleColor);

        // Default Color:
        // - hoursNeedleColor
        // - minutesNeedleColor
        // - secondsNeedleColor
        //下面将时、分、秒指针按照各自的偏移角度进行旋转，每次旋转前要先保存canvas的原始状态
        hourAngle = (float)hour / 12 * 360 + (float)minute / 60 * (360 / 12);//360/12是指每个数字之间的角度
        minAngle = (float)minute / 60 * 360;
        secAngle = (float)second / 60 * 360;

        canvas.save();
        paint.setStrokeWidth(8f);//画笔宽度
        canvas.rotate(hourAngle,mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - 120, paint);//时针长度设置为120
        canvas.restore();

        canvas.save();
        paint.setStrokeWidth(mWidth * DEFAULT_DEGREE_STROKE_WIDTH);//画笔宽度
        canvas.rotate(minAngle,mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - 180 , paint);//分针长度设置为180
        canvas.restore();

        canvas.save();
        paint.setStrokeWidth(3f);//画笔宽度
        canvas.rotate(secAngle,mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - 230 , paint);//秒针长度设置为230
        canvas.restore();

    }
    /**
     * Draw Center Dot
     *
     * @param canvas
     */
    private void drawCenter(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(mWidth * DEFAULT_DEGREE_STROKE_WIDTH);
        // Default Color:
        paint.setColor(degreesColor);
        // - centerInnerColor
        paint.setAlpha(FULL_ALPHA);//亮色
        canvas.drawCircle(mCenterX,mCenterY,6,paint);
        // - centerOuterColor
        paint.setAlpha(CUSTOM_ALPHA);//灰色
        canvas.drawCircle(mCenterX,mCenterY,10,paint);

    }

    public void setShowAnalog(boolean showAnalog) {
        mShowAnalog = showAnalog;
        invalidate();
    }

    public boolean isShowAnalog() {
        return mShowAnalog;
    }

}