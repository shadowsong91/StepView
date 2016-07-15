package com.shadowsong.stepview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhoumo on 16/7/15.
 */
public class VerticalStepDecoration extends StepDecoration{


    private int width = 200;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        float startY = 0;
        float centerY = 0;
        float endY = parent.getHeight();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            if (params.getViewLayoutPosition() == currentStep) {
                centerY = (child.getTop() + child.getBottom()) / 2;
                break;
            }
            if (i == 0) {
                if (params.getViewLayoutPosition() == 0) {
                    startY = child.getTop()+child.getHeight()/2;
                }
                if (params.getViewLayoutPosition() > currentStep) {
                }
            }
            if (i == childCount - 1) {
                if (params.getViewLayoutPosition() == parent.getAdapter().getItemCount() - 1) {
                    endY = child.getTop()+child.getHeight()/2;
                }
                if (params.getViewLayoutPosition() < currentStep) {
                    centerY = parent.getHeight();
                }
            }
        }

        c.drawLine(width / 2, startY, width / 2, centerY, linePaint);

        path.reset();
        path.moveTo(width / 2, centerY);
        path.lineTo(width / 2, endY);
        c.drawPath(path, dashPaint);


        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            if (params.getViewLayoutPosition() == currentStep) {
                int x = (width - currentDrawable.getIntrinsicWidth()) / 2;
                int y = (child.getHeight() - currentDrawable.getIntrinsicHeight()) / 2 + child.getTop();
                currentDrawable.setBounds(x, y, x + currentDrawable.getIntrinsicWidth(),
                        y + currentDrawable.getIntrinsicHeight());
                currentDrawable.draw(c);
            } else if (params.getViewLayoutPosition() < currentStep) {
                int x = (width - doneDrawable.getIntrinsicWidth()) / 2;
                int y = (child.getHeight() - doneDrawable.getIntrinsicHeight()) / 2 + child.getTop();
                doneDrawable.setBounds(x, y, x + doneDrawable.getIntrinsicWidth(),
                        y + doneDrawable.getIntrinsicHeight());
                doneDrawable.draw(c);
            } else {
                int x = (width - undoneDrawable.getIntrinsicWidth()) / 2;
                int y = (child.getHeight() - undoneDrawable.getIntrinsicHeight()) / 2 + child.getTop();
                undoneDrawable.setBounds(x, y, x + undoneDrawable.getIntrinsicWidth(),
                        y + undoneDrawable.getIntrinsicHeight());
                undoneDrawable.draw(c);
            }


        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.set(width, 0, 0, 0);
    }

    public static class Builder {

        private VerticalStepDecoration verticalStepDecoration;

        private Context context;

        public Builder(Context context) {
            this.context = context;
            verticalStepDecoration = new VerticalStepDecoration();
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

        public Builder width(int step) {
            verticalStepDecoration.width = step;
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


        public VerticalStepDecoration build() {
            return verticalStepDecoration;
        }

    }
}
