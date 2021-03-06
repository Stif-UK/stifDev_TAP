package com.granthutchison.stifdev_tap.Activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.granthutchison.stifdev_tap.R;
import com.granthutchison.stifdev_tap.Util.FontManager;
import com.granthutchison.stifdev_tap.Util.HandyUtils;

/**
 * Created by Stifler on 23/07/2016.
 */
public class AboutDialog extends DialogFragment {

    //About
    Button btnAbtExpand;
    TextView abtView;
    TextView abtTitle;

    //Acknowledgements
    Button btnAck;
    TextView ackView;
    TextView ackTitle;

    //History
    Button btnHist;
    TextView histView;
    TextView histTitle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Use an AlertDialog builder to populate the dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Inject the custom layout into the dialog box
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.about_dialog,null);
        builder.setView(dialogView);
        //Get the close button and set it up to close the dialog
        Button btnClose = (Button) dialogView.findViewById(R.id.btnAboutClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //This dialog box is made of a series of sections, expanded by a button press.

        //1. The about button and page section
        btnAbtExpand = (Button) dialogView.findViewById(R.id.btnAbtExpand);
        abtView = (TextView) dialogView.findViewById(R.id.aboutText);
        abtTitle = (TextView) dialogView.findViewById(R.id.aboutTitle);

        //Use the custom utility to set FontAwesome fonts on buttons
        btnAbtExpand.setTypeface(FontManager.getTypeface(btnAbtExpand.getContext(),FontManager.FONTAWESOME));
        btnAbtExpand.setText(R.string.fa_icon_expand);
        //Set onClick behaviour of the About button
        btnAbtExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutExpand();
            }
        });
        abtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutExpand();
            }
        });

        //2. the acknowledgement button and page section
        btnAck = (Button) dialogView.findViewById(R.id.btnInfoExpand);
        ackView = (TextView) dialogView.findViewById(R.id.ackText);
        ackTitle = (TextView) dialogView.findViewById(R.id.ackTitle);

        //Use the custom utility to set FontAwesome
        btnAck.setTypeface(FontManager.getTypeface(btnAck.getContext(),FontManager.FONTAWESOME));
        btnAck.setText(R.string.fa_icon_expand);
        //Set onClick behaviour for the info button
        btnAck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ackExpand();
            }
        });
        ackTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ackExpand();
            }
        });

        //3. the history button and page section
        btnHist = (Button) dialogView.findViewById(R.id.btnHistExpand);
        histView = (TextView) dialogView.findViewById(R.id.historyText);
        histTitle = (TextView) dialogView.findViewById(R.id.historyTitle);

        //Use the custom utility to set FontAwesome
        btnHist.setTypeface(FontManager.getTypeface(btnAck.getContext(),FontManager.FONTAWESOME));
        btnHist.setText(R.string.fa_icon_expand);
        //Set onClick behaviour for the info button
        btnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                histExpand();
            }
        });
        histTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                histExpand();
            }
        });


        //Set the style - this is configured to set the width of the box
        builder.getContext().getTheme().applyStyle(R.style.About_Dialog, true);

        return builder.create();
    }

    //Helper methods to expand/contract text sections to allow the text headers to also be clickable
    private void aboutExpand(){
        if (abtView.getText().length() == 0) {
            //Change the 'expand' button to a 'contract' button icon
            btnAbtExpand.setText(R.string.fa_icon_contract);
            //Set the text of the about box - file read in from a file in res/raw
            abtView.setText(HandyUtils.readRawTextFile(abtView.getContext(), R.raw.stifdev_tap_about));
        } else {
            btnAbtExpand.setText(R.string.fa_icon_expand);
            abtView.setText("");
        }
    }

    private void ackExpand(){
        if(ackView.getText().length() == 0){
            btnAck.setText(R.string.fa_icon_contract);
            //Read in text from a file held in res/raw
            ackView.setText(HandyUtils.readRawTextFile(ackView.getContext(),R.raw.stifdev_tap_thanks));
        }else {
            btnAck.setText(R.string.fa_icon_expand);
            ackView.setText("");
        }
    }

    private void histExpand(){
        if(histView.getText().length() == 0){
            btnHist.setText(R.string.fa_icon_contract);
            //Read in text from a file held in res/raw
            histView.setText(HandyUtils.readRawTextFile(histView.getContext(),R.raw.stifdev_tap_history));
        }else {
            btnHist.setText(R.string.fa_icon_expand);
            histView.setText("");
        }
    }
}
