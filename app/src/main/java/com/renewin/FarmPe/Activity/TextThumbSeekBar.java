package com.renewin.FarmPe.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;


public class TextThumbSeekBar extends AppCompatSeekBar {

    private int mThumbSize;
    private TextPaint mTextPaint;

    public TextThumbSeekBar(Context context) {
        this(context, null);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mThumbSize = 8;
        mTextPaint = new TextPaint();

        mTextPaint.setColor(Color.parseColor("#000000"));
        mTextPaint.setTextSize(0);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setLinearText(false);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String progressText = String.valueOf(getProgress());
        Rect bounds = new Rect();
        //  mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);

        int leftPadding = getPaddingLeft() - getThumbOffset();
        int rightPadding = getPaddingRight() - getThumbOffset();
        int width = getWidth() - leftPadding - rightPadding;
        float progressRatio = (float) getProgress() / getMax();
        float thumbOffset = mThumbSize * (.5f - progressRatio);
        float thumbX = progressRatio * width + leftPadding + thumbOffset;
        float thumbY = getHeight()* 2 /2; // width calculation
        canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
    }
}