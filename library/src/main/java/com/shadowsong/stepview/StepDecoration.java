package com.shadowsong.stepview;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zhoumo on 16/7/15.
 */
public abstract class StepDecoration extends RecyclerView.ItemDecoration {
  protected Paint linePaint;
  protected Paint dashPaint;
  protected int currentStep = 10;

  protected Drawable currentDrawable;
  protected Drawable undoneDrawable;
  protected Drawable doneDrawable;

  protected Path path;

  public StepDecoration() {
    linePaint = new Paint();
    linePaint.setColor(0xff11cd6e);
    linePaint.setStrokeWidth(3);

    dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    dashPaint.setAntiAlias(true);
    dashPaint.setStyle(Paint.Style.STROKE);
    dashPaint.setColor(0xff11cd6e);
    dashPaint.setStrokeWidth(3);
    dashPaint.setPathEffect(new DashPathEffect(new float[] {5, 10}, 0));

    path = new Path();
  }

  public void setLineWidth(float widthPixels) {
    dashPaint.setStrokeWidth(widthPixels);
    linePaint.setStrokeWidth(widthPixels);
  }

  public void setDashColor(int dashColor) {
    dashPaint.setColor(dashColor);
  }

  public void setLineColor(int lineColor) {
    linePaint.setColor(lineColor);
  }

}
