package com.cs3303.mycomet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CometAnimation extends View {
    float halfW, halfH, x, y, width, height, radius;

    int angle;

    public CometAnimation(Context context){
        super(context);
        angle = 0;
        radius = 50;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint cometPaint = new Paint();
        cometPaint.setStyle(Paint.Style.FILL);
        cometPaint.setColor(Color.MAGENTA);

        height = getHeight();
        width = getWidth();

        canvas.drawCircle(x, y, radius, cometPaint);

        angle = ( (angle + 1) % 360 );
        updateEllipseCoordinates();
        invalidate();
    }



    private void updateEllipseCoordinates(){
        float radians = (float) Math.toRadians(angle);
        halfW = width/2;
        halfH = height/2;
        float a = (halfW - 100);
        float b = (halfH - 100);

        x = halfW + a*(float)Math.cos(radians);
        y = halfH + b*(float)Math.sin(radians);
    }
}
