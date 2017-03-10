package com.example.ahmed.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button b1;
    TextView t1,t2;
    EditText e1;

    int sum =0;
    int score =0;

    Task task = new Task();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = (Button) findViewById(R.id.button);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        e1 = (EditText) findViewById(R.id.editText);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = e1.getText().toString();

                if (result.equals(String.valueOf(sum))){

                    score++;

                    generateRandom();
                }
                else{

                    task.cancel(true);
                    //finishGame();
                }
            }
        });


        generateRandom();

        task.execute(10);

    }


    private void finishGame(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Game Over");
        dialog.setMessage( String.valueOf(score));
        dialog.show();
    }

    private void  generateRandom(){

        Random random = new Random();

        int x = random.nextInt(9);
        int y = random.nextInt(9);

        sum =x+y;

        String equ = String.valueOf(x) + " + " + String.valueOf(y);

        t2.setText(equ);


    }


    private class Task extends AsyncTask<Integer,String,Void>{


        @Override
        protected Void doInBackground(Integer... params) {

            for (int i= params[0] ; i>=0; i--){


                publishProgress(String.valueOf(i));


                if (isCancelled())
                    return null ;

                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            t1.setText(values[0]);
        }


        @Override
        protected void onPostExecute(Void aVoid) {

            finishGame();
        }

        @Override
        protected void onCancelled() {

            finishGame();
        }
    }



}
