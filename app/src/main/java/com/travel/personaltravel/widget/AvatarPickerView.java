package com.travel.personaltravel.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;


/**
 * Created with Android Studio
 * Email: sielee@163.com
 * Date: 2016/1/13
 * Author: SieLee
 * CopyRight: MilesLife
 *
 * @Description: TODO
 */
public class AvatarPickerView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener {
    public AvatarPickerView(Context context) {
        super(context);
        init(context);
    }

    public AvatarPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AvatarPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public AvatarPickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];
    /**
     * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
     */
    private float initScale = 1.0f;
    private static float SCALE_MAX = 4.0f;
    int lineWidth;
    Rect bound = new Rect();
    int avatarSize;
    int horizPadding;
    int vertPadding;
    boolean scaling = false;
    Matrix scaleMatrix = new Matrix();
    ScaleGestureDetector detector;
    float lastX;
    float lastY;
    Matrix matrix;
    Paint paint = new Paint();

    {
        paint.setColor(0xaf000000);
    }

    Paint linePaint = new Paint();

    {
        linePaint.setColor(0xafffffff);
    }

    void init(Context context) {
        detector = new ScaleGestureDetector(context, this);
        lineWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, context.getResources().getDisplayMetrics());
    }

    @Override
    public boolean onTouchEvent(@NotNull MotionEvent e) {
        detector.onTouchEvent(e);
        if (e.getAction() == MotionEvent.ACTION_DOWN && e.getPointerCount() == 1)
            scaling = false;
        if (matrix != null && e.getAction() == MotionEvent.ACTION_MOVE && e.getPointerCount() == 1 && !scaling) {
            matrix.postTranslate(e.getX() - lastX, e.getY() - lastY);
            checkBorder();
            setImageMatrix(matrix);
        }
        lastX = e.getX();
        lastY = e.getY();
        return true;
    }

    public void resetMatrix() {
        matrix = null;
    }

    @Override
    public void onDraw(@NotNull Canvas canvas) {
        if (getDrawable() == null) {
            super.onDraw(canvas);
            return;
        }
        canvas.getClipBounds(bound);
        avatarSize = bound.width() * 3 / 4;
        horizPadding = (bound.width() - avatarSize) / 2;
        vertPadding = (bound.height() - avatarSize) / 2;
        if (matrix == null) {
            RectF drawableRect = new RectF(getDrawable().getBounds());
            RectF viewRect = new RectF(bound);
            matrix = new Matrix();
            matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
            setImageMatrix(matrix);
        }

        super.onDraw(canvas);

        canvas.drawRect(
                bound.left + horizPadding, bound.top,
                bound.left + horizPadding + avatarSize, bound.top + vertPadding,
                paint);
        canvas.drawRect(
                bound.left + horizPadding, bound.top + vertPadding + avatarSize,
                bound.left + horizPadding + avatarSize, bound.bottom,
                paint);
        canvas.drawRect(
                bound.left, bound.top,
                bound.left + horizPadding, bound.bottom,
                paint);
        canvas.drawRect(
                bound.left + horizPadding + avatarSize, bound.top,
                bound.right, bound.bottom,
                paint);

        canvas.drawRect(
                bound.left + horizPadding - lineWidth, bound.top + vertPadding - lineWidth,
                bound.left + horizPadding + avatarSize + lineWidth, bound.top + vertPadding,
                linePaint);
        canvas.drawRect(
                bound.left + horizPadding - lineWidth, bound.top + vertPadding + avatarSize,
                bound.left + horizPadding + avatarSize + lineWidth, bound.top + vertPadding + avatarSize + lineWidth,
                linePaint);
        canvas.drawRect(
                bound.left + horizPadding - lineWidth, bound.top + vertPadding,
                bound.left + horizPadding, bound.top + vertPadding + avatarSize,
                linePaint);
        canvas.drawRect(
                bound.left + horizPadding + avatarSize, bound.top + vertPadding,
                bound.left + horizPadding + avatarSize + lineWidth, bound.top + vertPadding + avatarSize,
                linePaint);
    }

    public Bitmap getClippedBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(-horizPadding, -vertPadding);
        canvas.scale(width / (float) avatarSize, height / (float) avatarSize, horizPadding, vertPadding);
        canvas.concat(matrix);
        getDrawable().draw(canvas);
        return bitmap;
    }

    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    public final float getScale() {
        matrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (matrix == null)
            return false;
        matrix.set(scaleMatrix);
        /**
         * 缩放的范围控制
         */
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        if ((scale < SCALE_MAX && scaleFactor > 1.0f)
                || (scale > initScale && scaleFactor < 1.0f)) {
            /**
             * 最大值最小值判断
             */
            if (scaleFactor * scale < initScale) {
                scaleFactor = initScale / scale;
            }
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale;
            }
            /**
             * 设置缩放比例
             */
            if (scaleFactor > 1) {
                matrix.postScale(scaleFactor, scaleFactor,
                        detector.getFocusX(), detector.getFocusY());
                checkBorder();
                setImageMatrix(matrix);
            }
        }
        return true;
        /*matrix.postScale(detector.getScaleFactor(), detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
        checkBorder();
        setImageMatrix(matrix);
        return false;*/
    }


    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        if (matrix == null)
            return false;
        scaleMatrix.set(matrix);
        scaling = true;
        return true;
    }

    /**
     * 根据当前图片的Matrix获得图片的范围
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix mt = matrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            mt.mapRect(rect);
        }
        return rect;
    }

    private void checkBorder() {

        RectF rect = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        // 如果宽或高大于屏幕，则控制范围 ; 这里的0.001是因为精度丢失会产生问题，但是误差一般很小，所以我们直接加了一个0.01
        if (rect.width() + 0.01 >= width - 2 * horizPadding) {
            if (rect.left > horizPadding) {
                deltaX = -rect.left + horizPadding;
            }
            if (rect.right < width - horizPadding) {
                deltaX = width - horizPadding - rect.right;
            }
        }
        if (rect.height() + 0.01 >= height - 2 * vertPadding) {
            if (rect.top > vertPadding) {
                deltaY = -rect.top + vertPadding;
            }
            if (rect.bottom < height - vertPadding) {
                deltaY = height - vertPadding - rect.bottom;
            }
        }
        matrix.postTranslate(deltaX, deltaY);
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
}

