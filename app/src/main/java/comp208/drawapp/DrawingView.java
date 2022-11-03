package comp208.drawapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawingView extends View {

    String TAG = "--DRAW";
    Paint paint = new Paint();
    //to store the location of the cursor / use of Path
    ArrayList<ColouredPath> paths = new ArrayList<>();
    ColouredPath path;

    //setting up the initial value for the color, stroke width and style
    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
    }

    //store starting point of the cursor
    public void addPointToPath(float x, float y)
    {
        if(path.isEmpty())
            path.moveTo(x,y);
        else
            path.lineTo(x,y);

      invalidate();

    }
//store the path followed by the cursor
    public void beginPath(float x, float y)
    {
        path = new ColouredPath();
        path.colour = paint.getColor();
        path.width = paint.getStrokeWidth();
        path.moveTo(x,y);
        paths.add(path);
    }

    //setting up the color for the path that is store in coloruedPath arraylist
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


            for (ColouredPath path : paths) {
                paint.setColor(path.colour);
                paint.setStrokeWidth(path.width);
                canvas.drawPath(path, paint);
            }



    }


//method to change colour while different colour button is pressed
    public void setDrawingColour(int colour)
    {

        paint.setColor(colour);
    }
    //method to change colour while different drawing tools button is pressed
    public void setDrawingStyle(float width)
    {

        paint.setStrokeWidth(width);

    }

    //to clear the entire path stored inside array list so to make the screen clear.
    public  void clearScreen(){

    paths.clear();
    invalidate();


    }
}
