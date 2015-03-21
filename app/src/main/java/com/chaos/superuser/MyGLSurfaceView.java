package com.chaos.superuser;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

public class MyGLSurfaceView extends GLSurfaceView
{
    private com.chaos.superuser.Renderer mRenderer;
    ScaleGestureDetector scaleGestureDetector;
    //Scale factor

    // Offsets for touch events
    private float mPreviousX;
    private float mPreviousY;
    private float mPreviousZ;
    private float mDensity;


    public MyGLSurfaceView(Context context)
    {
        super(context);

    }

    public MyGLSurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        scaleGestureDetector=new ScaleGestureDetector(context, new MyOnScaleGestureListener());

    }
    //

    //
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        scaleGestureDetector.onTouchEvent(event);

        if (event != null)
        {
            float x = event.getX();
            float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                if (mRenderer != null)
                {
                    float deltaX = (x - mPreviousX) / mDensity / 2f;
                    float deltaY = (y - mPreviousY) / mDensity / 2f;

                    mRenderer.mDeltaX += deltaX;
                    mRenderer.mDeltaY += deltaY;
                }
            }

            mPreviousX = x;
            mPreviousY = y;
            //

            //
            return true;
        }
        else
        {
            return super.onTouchEvent(event);
        }
    }
    public class MyOnScaleGestureListener extends
            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            float deltaZ=(scaleFactor-mPreviousZ)/mDensity/2f;
            if (scaleFactor > 1) {
                mRenderer.mDeltaZ+=deltaZ;
            } else {
                mRenderer.mDeltaZ-=deltaZ;
            }
            return true;
        }
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {

            return true;
        }
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }



    //

    // Hides superclass method.
    public void setRenderer(com.chaos.superuser.Renderer renderer, float density)
    {
        mRenderer = renderer;
        mDensity = density;
        super.setRenderer(renderer);
    }
}
