package com.example.kashish.ravi_new;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    private WebView wv;
//    ImageView img;
    Spinner s;
    Button b;

    int imageX,imageY;

    void initViews(){
        b = (Button) findViewById(R.id.button_showImage);
        s = (Spinner) findViewById(R.id.spinner);
    }


    void writeFile(final Context context, String fileName){
        String largeString = "";
        File file = new File(fileName);
        FileOutputStream outputStream;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String st;
            while ((st = br.readLine()) != null) {

                largeString += st+"\n";


            }

            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(largeString.getBytes());
            outputStream.close();
            Log.d(TAG,"largeString: "+largeString);

            Toast.makeText(context, "File written", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
//        writeFile(this, fileName);

        ArrayList<String>
        list = new ArrayList<String>();
//        list.add("square");
//        list.add("rectangle");
//        list.add("equilateral triangle");
//        list.add("isoceles triangle");
//        list.add("scalene triangle");
//        list.add("pentagon");
        list.add("Complex1");
        list.add("Complex2");
        list.add("Circle");
        list.add("Rectangle");
//        list.add("Semicircle");
        list.add("Square");
        list.add("Star");
        list.add("Triangle");
//        list.add("Ellipse");

//        final ArrayList<String> files = new ArrayList<>();

        final ArrayList<String> filesMobile = new ArrayList<>();
        filesMobile.add("Complex1.svg");
        filesMobile.add("Complex2.svg");
        filesMobile.add("Circle.svg");
        filesMobile.add("Rectangle.svg");
        filesMobile.add("Square.svg");
        filesMobile.add("Star.svg");
        filesMobile.add("Triangle.svg");
        filesMobile.add("Ellipse.svg");


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(this, "Height: "+height+" and width: "+width, Toast.LENGTH_SHORT).show();
        Log.e(TAG,"Height: "+height+" and width: "+width);

        scaleSVG ss = new scaleSVG();
        ss.scale(this, "Complex1.svg",width,height);
        ss.scale(this, "Complex2.svg",width,height);
        ss.scale(this, "Circle.svg",width,height);
        ss.scale(this, "Rectangle.svg",width,height);
        ss.scale(this, "Square.svg",width,height);
        ss.scale(this, "Star.svg",width,height);
        ss.scale(this, "Triangle.svg",width,height);
        ss.scale(this, "Ellipse.svg",width,height);

        s = (Spinner) findViewById(R.id.spinner);
        s.setPrompt("Choose a diagram");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(dataAdapter);

//        final int screenOrientation = getResources().getConfiguration().orientation;



        Button b = (Button) findViewById(R.id.button_showImage);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MainActivity.this, showImage.class);
                String showFile = filesMobile.get(s.getSelectedItemPosition());
                n.putExtra("file",showFile);
//                n.putExtra("orientation",screenOrientation);
                startActivity(n);
            }
        });
    }



}
