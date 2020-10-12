package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ZoomLinksPage extends AppCompatActivity {
    // Created an int array of Button ids
    //buttonNames contains the IDs of the buttons
    final int[] buttonNames =
            {R.id.button, R.id.button4, R.id.button5, R.id.button6,
                    R.id.button7, R.id.button8, R.id.button9, R.id.button10};

    //buttonList contains the Button objects themselves
    Button[] buttonList = new Button[buttonNames.length - 1];
    int count = 0; //Count variable to keep track of visible buttons
    String COUNT = "COUNT";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_links_page);

        // Created a list of Buttons and added the buttons on screen
        // We are doing i + 1 on line 26 cause buttonNames has 7, but buttonList has 6
        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i] = findViewById(buttonNames[i + 1]);
        }
        pref = getPreferences(MODE_PRIVATE);
        //Check if the button has been made visible by the user and set layout
        for (Button b : buttonList) {
            if (pref.contains(Integer.toString(b.getId()))) {
                int vis = pref.getInt(Integer.toString(b.getId()), View.GONE);
                b.setVisibility(vis);
            }
        }
    }

    /** Called when the user taps the Add Class Button */
    public void addClass(View view) {
        pref = getPreferences(MODE_PRIVATE);
        editor = pref.edit();
        //editor.remove("COUNT");
        //Get the num the counter was at last time user used this screen
        if (pref.contains(COUNT)) {
            count = pref.getInt(COUNT, 0);
        }
        // Add 1 to count bc we want to know another button is shown unless its at max
        if (count < buttonList.length) {
            count++;
            editor.putInt(COUNT, count);
            editor.apply();
            //editor.clear();
        }
        //Iterate up to count and make those buttons visible if they are not
        for (int i = 0; i < count; i++) {
            if (buttonList[i].getVisibility() == View.GONE) {
                buttonList[i].setVisibility(View.VISIBLE);
                editor.putInt(getString(buttonNames[i+1]), buttonList[i].getVisibility());
                editor.apply();
                //editor.clear();
            }
        }
    }

    /** Called when the user taps a Class Button */
    public void insertClassDetails(final View view) {
        Button b = findViewById(view.getId());
        if (b.getText().toString().equals("Class")) {
            // AlertDialog Builder - this is used to create the dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add A Class");

            // Set the layout to the dialog box layout - dialog_box_zoom_links.xml
            final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_box_zoom_links, null);
            builder.setView(dialogLayout);

            // Add the Save button
            builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Send user inputs from the AlertDialog to the Activity
                    // The EditTexts are text boxes in the xml
                    EditText editClassName = dialogLayout.findViewById(R.id.editTextClassName);
                    EditText editZoomLink = dialogLayout.findViewById(R.id.editTextFilledZoomLink);

                    // Save the button name to the class
                    Button a = findViewById(view.getId());
                    a.setText(editClassName.getText().toString());
                    //saveClassDetails(editClassName.getText().toString(), view);

                    saveClassDetails(editZoomLink.getText().toString(), view);
                }
            });
            // Create and show the dialog box
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            // AlertDialog Builder - this is used to create the dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(b.getText().toString());

            // Set the layout to the dialog box layout - dialog_box_zoom_links_filled.xml
            final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_box_zoom_links_filled, null);
            builder.setView(dialogLayout);

            TextView zoomLink = dialogLayout.findViewById(R.id.editTextFilledZoomLink);
            zoomLink.setText("HI");

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    // Do something with the data coming from the AlertDialog
    // Takes in a View parameter so we know where the request came from
    private void saveClassDetails(String data, View view) {
        //Button a = findViewById(view.getId());
        //a.setText(data);
        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    //For testing - use this to reset saved values
    public void clear(View view) {
        pref = getPreferences(MODE_PRIVATE);
        editor = pref.edit();
        editor.clear();
        count = 0;
        editor.remove("COUNT");
        editor.apply();
    }
}