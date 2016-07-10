package com.granthutchison.stifdev_tap.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.granthutchison.stifdev_tap.Model.Controller;
import com.granthutchison.stifdev_tap.R;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stifler on 06/07/2016.
 */
public class CreditsActivity extends AppCompatActivity {

    private TextView creditsView1;
    private TextView creditsView2;
    private TextView creditsView3;
    private TextView creditsView4;
    private TextView creditsView5;
    private LinkedHashMap<String,String> endCredits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Controller myCont = Controller.getInstance();
        setContentView(R.layout.activity_credits);

        //Bind the TextViews to instance variables
        creditsView1 = (TextView) findViewById(R.id.creditField1);
        creditsView2 = (TextView) findViewById(R.id.creditField2);
        creditsView3 = (TextView) findViewById(R.id.creditField3);
        creditsView4 = (TextView) findViewById(R.id.creditField4);
        creditsView5 = (TextView) findViewById(R.id.creditField5);

        //Get the end credits for the current game
        endCredits = myCont.getCredits();


    }

    @Override
    protected void onStart(){
        super.onStart();
        rollCredits();

    }

    /**
     * The rollCredits method handles the task of parsing the credits of the current game
     * and populating these in the view.
     */
    private  void rollCredits(){

        final List<TextView> viewList = new ArrayList<>();
        //Add each of the views to the global list to allow iteration
        viewList.add(creditsView1);
        viewList.add(creditsView2);
        viewList.add(creditsView3);
        viewList.add(creditsView4);
        viewList.add(creditsView5);


        /*
         * Create an Iterator to iterate over the endCredits map. This is declared as final
         * so that it can be passed to the CountDownTimer.
         */
        final Iterator it = endCredits.entrySet().iterator();

        /*
         * Determine the required timer length - initially set at one second per entry in the
         * endCredits map, plus an additional second.
         */
        int creditSize = endCredits.size();
        int timerLength = (creditSize*1000)+1000;

        new CountDownTimer(timerLength, (timerLength/creditSize/2)) {
            //Prep for iteration within the timer
            int counter = 0;
            //The value of cont determines if the iteration should continue.
            boolean cont = it.hasNext();
            boolean headerBody = false;
            TextView currentView;
            TextView previousView = null;

            //Local variables to hold the credit text
            Map.Entry<String, String> credit;
            String creditHeader;
            String creditBody;






            public void onTick(long millisUntilFinished) {
                //TODO: Error in code here - by determining if there is a next value before entering
                //the updates I'm calling null values
                /*
                 * First, determine if the counter has a value greater than 4, and reset to zero
                 * if so.
                 */
                if(counter > 4){
                    counter = 0;
                }

                /*
                 * If there are still additional values in the iterator, continue to process the
                 * values. NOTE: Iterator is only
                 */
                if(cont) {

                    clearPreviousView(previousView);

                    if(headerBody){
                        credit = (Map.Entry) it.next();
                        creditHeader = credit.getKey();
                        Log.d("rollCredits","In tick, assigning Header value: "+creditHeader);
                        creditBody = credit.getValue();
                        Log.d("rollCredits","In tick, assigning Body value: "+creditBody);
                        currentView = viewList.get(counter);
                        Log.d("rollCredits","Clearing previous view");
                        writeCreditsHeader(currentView, creditHeader);
                        Log.d("rollCredits","Writing Header: "+creditHeader);
                        headerBody = false;
                    }else{
                        writeCreditsBody(currentView,creditBody);
                        Log.d("rollCredits","Writing Body: " +creditBody);
                        headerBody = true;
                        /*
                         * Check if the iterator has finished - if so set the cont boolean to
                         * false. Carried out in the else clause to ensure that both credit header
                         * and body have been written before finishing.
                         */
                        if(!it.hasNext()){
                            cont = false;
                        }
                        /*
                         * Set the current view as the previous view to allow clearing this on the
                         * next tick and increment the counter so that the next view is used next time.
                         * Again carried out within the else clause to ensure it happens after the
                         * credits body has been written.
                         */
                        previousView = currentView;
                        counter++;
                    }


                }






            }

            public void onFinish() {
                //TODO: Replace this snackbar with the ability to end the game
                Snackbar.make(creditsView1, "The credits have ended!",Snackbar.LENGTH_LONG).show();
            }
        }.start();


    }

    private void clearPreviousView(TextView previousView){
        if(previousView != null){
            //TODO: Replace this with a fade out animation
            previousView.setText("");
        }
    }

    private  void writeCreditsHeader(TextView currentView, String creditHeader){
        //TODO: Add fade in animation to the creditHeader line
        currentView.setText(creditHeader);
        currentView.append("\n");
    }

    private void writeCreditsBody(TextView currentView, String creditBody){
        currentView.append(creditBody);
    }

}
