package com.idnp.proyectofinal.statistics;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Barra extends View {
    private static final  int RECTX = 50;
    private static final  int RECTY = 200;
    private Paint Paintrect,p1,p2,p3,Letras,Lineas;
    private Rect rect,rect1,rect2,rect3;
    private Integer Dato;
    public Barra(Context context) {
        super(context);
        init(null);
    }

    public Barra(Context context, String dato) {
        super(context);
        //init(null);
        Paintrect = new Paint();
        p1 = new Paint();
        p2 = new Paint();
        p3 = new Paint();
        Letras = new Paint();
        Lineas = new Paint();
        rect = new Rect();
        rect1 = new Rect();
        rect2 = new Rect();
        rect3 = new Rect();
        Dato = new Integer(Integer.parseInt(dato));
    }
    public Barra(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Barra(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Barra(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    private void init(@Nullable AttributeSet set){
        Dato = new Integer(200);
        Paintrect = new Paint();
        p1 = new Paint();
        p2 = new Paint();
        p3 = new Paint();
        Letras = new Paint();
        Lineas = new Paint();
        rect = new Rect();
        rect1 = new Rect();
        rect2 = new Rect();
        rect3 = new Rect();
    }

    public void ReDraw(){
        rect.left = RECTX;
        rect.top  = RECTY;
        rect.right = rect.left + 200;
        rect.bottom = rect.top + 200;
    }

    @Override
    protected void onDraw(Canvas canvas){
        //super.onDraw(canva
        Paintrect.setColor(Color.RED);
        p1.setColor(Color.GREEN);
        p2.setColor(Color.GRAY);
        p3.setColor(Color.BLUE);
        rect.left = 200;
        rect.top  = Dato +280;
        rect.right = rect.left + 100;
        rect.bottom = rect.top + 300;

        rect1.left = 320;
        rect1.top  = 180;
        rect1.right = rect1.left + 100;
        rect1.bottom = rect1.top + 600;

        rect2.left = 440;
        rect2.top  = 280;
        rect2.right = rect2.left + 100;
        rect2.bottom = rect2.top + 500;

        rect3.left = 560;
        rect3.top  = 80;
        rect3.right = rect3.left + 100;
        rect3.bottom = rect3.top + 700;

        Letras.setColor(Color.BLACK);
        Letras.setStyle(Paint.Style.FILL);
        Letras.setTextSize(50);
        Lineas.setColor(Color.BLACK);
        Lineas.setStrokeWidth(6f);

        canvas.drawRect(rect,Paintrect);
        canvas.drawRect(rect1,p1);
        canvas.drawRect(rect2,p2);
        canvas.drawRect(rect3,p3);
        canvas.drawText("12-18",230,850,Letras);
        canvas.drawText("2",350,850,Letras);
        canvas.drawText("3",480,850,Letras);
        canvas.drawText("4",590,850,Letras);
        canvas.drawText("1",140,750,Letras);
        canvas.drawText("2",140,590,Letras);
        canvas.drawText("3",140,480,Letras);
        canvas.drawText("4",140,350,Letras);
        canvas.drawLine(180, 800, 800, 800, Lineas);
        canvas.drawLine(180, 800, 180, 100, Lineas);

    }
}