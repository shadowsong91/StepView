package com.shadowsong.stepview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhoumo on 16/7/15.
 */
public class HorizontalStepDecoration extends StepDecoration{


    private int height = 200;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        float startX = 0;
        float centerX = 0;
        float endX = parent.getWidth();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            if (params.getViewLayoutPosition() == currentStep) {
                centerX = (child.getLeft() + child.getRight()) / 2;
                break;
            }
            if (i == 0) {
                if (params.getViewLayoutPosition() == 0) {
                    startX = child.getLeft()+child.getWidth()/2;
                }
                if (params.getViewLayoutPosition() > currentStep) {
                }
            }
            if (i == childCount - 1) {
                if (params.getViewLayoutPosition() == parent.getAdapter().getItemCount() - 1) {
                    endX = child.getLeft()+child.getWidth()/2;
                }
                if (params.getViewLayoutPosition() < currentStep) {
                    centerX = parent.getWidth();
                }
            }
        }

        c.drawLine(startX, height/2, centerX, height/2, linePaint);

        path.reset();
        path.moveTo(centerX, height/2);
        path.lineTo(endX, height/2);
        c.drawPath(path, dashPaint);


        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            if (params.getViewLayoutPosition() == currentStep) {
                int x = child.getLeft() + (child.getWidth()-currentDrawable.getIntrinsicWidth())/2;
                int y = (height - currentDrawable.getIntrinsicHeight()) / 2;
                currentDrawable.setBounds(x, y, x + currentDrawable.getIntrinsicWidth(),
                        y + currentDrawable.getIntrinsicHeight());
                currentDrawable.draw(c);
            } else if (params.getViewLayoutPosition() < currentStep) {
                int x = child.getLeft() + (child.getWidth()-doneDrawable.getIntrinsicWidth())/2;
                int y = (height - doneDrawable.getIntrinsicHeight()) / 2;
                doneDrawable.setBounds(x, y, x + doneDrawable.getIntrinsicWidth(),
                        y + doneDrawable.getIntrinsicHeight());
                doneDrawable.draw(c);
            } else {
                int x = child.getLeft() + (child.getWidth()-undoneDrawable.getIntrinsicWidth())/2;
                int y = (height - undoneDrawable.getIntrinsicHeight()) / 2;
                undoneDrawable.setBounds(x, y, x + undoneDrawable.getIntrinsicWidth(),
                        y + undoneDrawable.getIntrinsicHeight());
                undoneDrawable.draw(c);
            }


        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.set(0, height, 0, 0);
    }

    public static class Builder {

        private HorizontalStepDecoration verticalStepDecoration;

        private Context context;

        public Builder(Context context) {
            this.context = context;
            verticalStepDecoration = new HorizontalStepDecoration();
        }

        public Builder doneRes(int id) {
            verticalStepDecoration.doneDrawable = context.getResources().getDrawable(id);
            return this;
        }
        public Builder currentRes(int id) {
            verticalStepDecoration.currentDrawable = context.getResources().getDrawable(id);
            return this;
        }
        public Builder undoneRes(int id) {
            verticalStepDecoration.undoneDrawable = context.getResources().getDrawable(id);
            return this;
        }

        public Builder currentStep(int step) {
            verticalStepDecoration.currentStep = step;
            return this;
        }

        public Builder height(int heightPixels) {
            verticalStepDecoration.height = heightPixels;
            return this;
        }

        public Builder dashColor(int color) {
            verticalStepDecoration.setDashColor(color);
            return this;
        }

        public Builder lineColor(int color) {
            verticalStepDecoration.setLineColor(color);
            return this;
        }

        public Builder lineWidth(float widthPixels) {
            verticalStepDecoration.setLineWidth(widthPixels);
            return this;
        }


        public HorizontalStepDecoration build() {
            return verticalStepDecoration;
        }

    }
}
