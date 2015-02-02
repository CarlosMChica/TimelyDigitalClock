package com.carlosdelachica.timelydigitalclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.carlosdelachica.timelydigitalclock.animation.TimelyEvaluator;
import com.carlosdelachica.timelydigitalclock.model.NumberUtils;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.util.Property;

public class TimelyView extends View {

    private static final Property<TimelyView, float[][]> CONTROL_POINTS_PROPERTY = new Property<TimelyView, float[][]>(float[][].class, "controlPoints") {
        @Override
        public float[][] get(TimelyView object) {
            return object.getControlPoints();
        }

        @Override
        public void set(TimelyView object, float[][] value) {
            object.setControlPoints(value);
        }
    };
    private Paint paint = null;
    private Path path = null;
    private float[][] controlPoints = null;
    private int textSize = -1;
    private int textColor = Color.BLACK;

    public TimelyView(Context context) {
        super(context);
        init();
    }

    public TimelyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimelyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public float[][] getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(float[][] controlPoints) {
        this.controlPoints = controlPoints;
        invalidate();
    }

    public ObjectAnimator animate(int start, int end) {
        float[][] startPoints = NumberUtils.getControlPointsFor(start);
        float[][] endPoints = NumberUtils.getControlPointsFor(end);

        return ObjectAnimator.ofObject(this, CONTROL_POINTS_PROPERTY, new TimelyEvaluator(), startPoints, endPoints);
    }

    public ObjectAnimator animate(int end) {
        float[][] startPoints = NumberUtils.getControlPointsFor(-1);
        float[][] endPoints = NumberUtils.getControlPointsFor(end);

        return ObjectAnimator.ofObject(this, CONTROL_POINTS_PROPERTY, new TimelyEvaluator(), startPoints, endPoints);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (controlPoints == null) return;

        int length = controlPoints.length;

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        float minDimen = height > width ? width : height;
        minDimen -= paint.getStrokeWidth();

        path.reset();
        path.moveTo(minDimen * controlPoints[0][0], minDimen * controlPoints[0][1]);
        for (int i = 1; i < length; i += 3) {
            path.cubicTo(minDimen * controlPoints[i][0], minDimen * controlPoints[i][1],
                    minDimen * controlPoints[i + 1][0], minDimen * controlPoints[i + 1][1],
                    minDimen * controlPoints[i + 2][0], minDimen * controlPoints[i + 2][1]);
        }
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (int) ((getMeasuredWidth() > textSize ? textSize : getMeasuredWidth())
                + paint.getStrokeWidth());
        int height = (int) (textSize + paint.getStrokeWidth());

        setMeasuredDimension(width, height);
    }

    private void init() {
        // A new paint with the style as stroke.
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setStrokeWidth(5.0f);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        paint.setColor(textColor);
    }

}
