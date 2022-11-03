package comp208.drawapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        drawingView = findViewById(R.id.drawingView);

        //touch listener get the current location of the cursor while pressed
        //switch works accordingly to action took place inside the canvas and calls the required method to store the path of the cursor.

        drawingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();

                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        drawingView.beginPath(motionEvent.getX(),motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                        drawingView.addPointToPath(motionEvent.getX(),motionEvent.getY());
                        break;
                }
                return true;
            }
        });

    }
    public  void clearScreenButton(View view){
        drawingView.clearScreen();
    }

    //gets the current draw tool pressed and sets the value in DrawingView class
    public void imageButtonListener(View view){
        Paint paint = new Paint();
        switch (getResources().getResourceEntryName(view.getId()))
        {
            case "brush":
                paint.setStrokeWidth(88);
                break;
            case "marker":
                paint.setStrokeWidth(44);
                break;
            case "pencil":
                paint.setStrokeWidth(7);
                break;
        }

        drawingView.setDrawingStyle(paint.getStrokeWidth());
    }

    //gets the current color pressed and sets the value in DrawingView class for color
    public void buttonOnclickListener(View view){

     // Toast.makeText(MainActivity.this, "Id: "+getResources().getResourceEntryName(view.getId()), Toast.LENGTH_SHORT).show();
        Paint paint = new Paint();

        switch (getResources().getResourceEntryName(view.getId()))
        {
            case "red":
                paint.setColor(Color.RED);
                break;
            case "yellow":
                paint.setColor(Color.YELLOW);
                break;
            case "green":
                paint.setColor(Color.GREEN);
                break;
            case "skyblue":
                paint.setColor(Color.parseColor("#41B2E4"));
                break;
            case "blue":
                paint.setColor(Color.BLUE);
                break;
            case "pink":
                paint.setColor(Color.parseColor("#DF3E75"));
                break;
            case "black":
                paint.setColor(Color.BLACK);
                break;
            case "white":
                paint.setColor(Color.WHITE);
                break;
            default:
                paint.setColor(Color.BLACK);

        }
        drawingView.setDrawingColour(paint.getColor());

    }
}