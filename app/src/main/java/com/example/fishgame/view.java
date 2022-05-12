package com.example.fishgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class view extends View {
    private Bitmap animal[]=new Bitmap[2];
    private int animalX=10;
    private int animalY;
    private int animalSpeed;
    private int canvasWidth,canvasHeight;

    private int backX,backY;
    private Paint background=new Paint();

    private int yellowX,yellowY,yellowSpeed=8;
    private Paint yellowPaint=new Paint();

    private int greenX,greenY,greenSpeed=15;
    private Paint greenPaint=new Paint();

    private int redX,redY,redSpeed=5;
    private Paint redPaint=new Paint();

    private int score,lifeCounter;
    private boolean touch=false;

    private Paint scorePaint=new Paint();

    private Bitmap life[]=new Bitmap[2];
    public view(Context context) {
        super(context);
        animal[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        animal[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);

        background.setColor(Color.CYAN);
        background.setAntiAlias(true);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.BLUE);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);


        scorePaint.setColor(Color.GREEN);
        scorePaint.setTextSize(40);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        animalY=550;
        score=0;
        lifeCounter=3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();
        canvas.drawRect(0,0,0,0,background);
        int minAnimalY=animal[0].getHeight();
        int maxAnimalY=canvasHeight-animal[0].getHeight() * 3;
        animalY=animalY+animalSpeed;
        if(animalY<minAnimalY){
            animalY=minAnimalY;
        }
        if(animalY>maxAnimalY){
            animalY=maxAnimalY;
        }
        animalSpeed=animalSpeed+2;

        if(touch){
            canvas.drawBitmap(animal[1],animalX,animalY,null);
            touch=false;
        }
        else{
            canvas.drawBitmap(animal[0],animalX,animalY,null);
        }


        yellowX=yellowX-yellowSpeed;

        if (hitBallCheckar(yellowX,yellowY)){
            score=score+10;
            yellowX=-100;
        }

        if(yellowX<0){
            yellowX=canvasWidth+21;
            yellowY=(int) Math.floor(Math.random() * (maxAnimalY-minAnimalY))+minAnimalY;
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);


        greenX=greenX-greenSpeed;

        if (hitBallCheckar(greenX,greenY)){
            score=score+15;
            greenX=-100;
        }

        if(greenX<0){
            greenX=canvasWidth+21;
            greenY=(int) Math.floor(Math.random() * (maxAnimalY-minAnimalY))+minAnimalY;
        }
        canvas.drawCircle(greenX,greenY,30,greenPaint);


        redX=redX-redSpeed;

        if (hitBallCheckar(redX,redY)){
            redX=-100;
            lifeCounter--;
            if(lifeCounter==0){
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
                Intent gameOverIntent=new Intent(getContext(),GameOver.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score",score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if(redX<0){
            redX=canvasWidth+21;
            redY=(int) Math.floor(Math.random() * (maxAnimalY-minAnimalY))+minAnimalY;
        }
        canvas.drawCircle(redX,redY,27,redPaint);
        canvas.drawText("Score : "+score,20,60,scorePaint);
        for(int i=0;i<3;i++){
            int x=(int) (550+life[0].getWidth()*1*i);
            int y=30;
            if(i<lifeCounter){
                canvas.drawBitmap(life[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life[1],x,y,null);
            }
        }



    }

    public boolean hitBallCheckar(int x,int y){
        if(animalX<x && x<(animalX+animal[0].getWidth()) && animalY<y && y<(animalY+animal[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch=true;
            animalSpeed=-20;
        }
        return true;
    }
}

