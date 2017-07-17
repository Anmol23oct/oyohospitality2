package com.example.anmolsharma.oyohospitality;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by anmolsharma on 15/07/17.
 */
public class DialogBoxlogin extends DialogFragment {


    final CharSequence[] items={"Emloyee","Employer"};
    String selection;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Login As").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                switch (which) {

                    case 0:

                        selection= (String) items[which];

                        break;

                    case 1:

                        selection= (String) items[which];

                        break;



                    default:

                        break;




                }

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"You Registered as "+selection,Toast.LENGTH_LONG).show();





            }
        });



        return builder.create();


    }
}
