package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZoomLinksPage extends AppCompatActivity {
    // Created an int array of Button ids
    final int[] buttonNames =
            {R.id.button, R.id.button4, R.id.button5, R.id.button6,
                    R.id.button7, R.id.button8, R.id.button9, R.id.button10};
    Button[] buttonList = new Button[buttonNames.length - 1];
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_links_page);

        // Created a list of Buttons and added the buttons on screen
        // We are doing i + 1 on line 26 cause buttonNames has 7, but buttonList has 6
        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i] = findViewById(buttonNames[i+1]);
        }
    }
    
    /** Called when the user taps the Add Class Button */
    public void addClass(View view) {
        // Add 1 to count bc we want to know another button is shown unless its at max
        if (count < buttonList.length)
            count++;
        for (int i = 0; i < count; i++) {
            if (buttonList[i].getVisibility() == View.GONE) {
                buttonList[i].setVisibility(View.VISIBLE);
            }
        }
    }

    /** Called when the user taps a Class Button */
    public void insertClassDetails(final View view) {
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
                EditText editZoomLink = dialogLayout.findViewById(R.id.editTextZoomLink);

                saveClassDetails(editClassName.getText().toString(), view);
                saveClassDetails(editZoomLink.getText().toString(), view);
            }
        });
        // Create and show the dialog box
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Do something with the data coming from the AlertDialog
    // Takes in a View parameter so we know where the request came from
    private void saveClassDetails(String data, View view) {
        Button a = findViewById(view.getId());
        a.setText(data);

        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }

}