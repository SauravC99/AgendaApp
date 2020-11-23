package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class ZoomLinksPage extends AppCompatActivity {
    // Created an int array of Button ids
    //buttonNames contains the IDs of the buttons
    final int[] buttonNames =
            {R.id.button, R.id.button4, R.id.button5, R.id.button6,
                    R.id.button7, R.id.button8, R.id.button9, R.id.button10};

    // buttonList contains the Button objects themselves
    Button[] buttonList = new Button[buttonNames.length - 1];
    // Count variable to keep track of visible buttons
    int count = 0;

    ZoomLinksDatabaseHelper databaseHelper;
    List<ZoomClassModel> everything;
    String className;

    /** Called when this activity comes to attention/starts */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_links_page);

        // Created a list of Buttons and added the buttons on screen
        // We are doing i + 1 on line 26 cause buttonNames has 7, but buttonList has 6
        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i] = findViewById(buttonNames[i + 1]);
        }

        // Sees whats currently in the database to populate screen
        databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
        everything = databaseHelper.getEverything();

        count = everything.size();
        for (int i = 0; i < everything.size(); i++) {
            buttonList[i].setVisibility(View.VISIBLE);
            buttonList[i].setText(everything.get(i).getClassName());
        }
    }

    /** Called when the user taps the Add Class button */
    public void addClass(View view) {
        // Add 1 to count bc we want to know another button is shown unless its at max
        if (count < buttonList.length) {
            count++;
            buttonList[count - 1].setVisibility(View.VISIBLE);
        }
    }

    /** Called when the user taps a Class Button */
    public void insertClassDetails(View view) {
        final Button currentButton = findViewById(view.getId());
        final int buttonNumber = getButtonNumber(view);

        if (currentButton.getText().toString().equals("Class")) {
            // AlertDialog Builder - this is used to create the dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add A Class");

            // Set the layout to the dialog box layout - dialog_box_zoom_links.xml
            final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_box_zoom_links, null);
            builder.setView(dialogLayout);

            // Add the Save button
            builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Capture the user input and make a ZoomClassModel Object
                    // The EditTexts are text boxes in the xml
                    EditText editClassName = dialogLayout.findViewById(R.id.editTextClassName);
                    EditText editZoomLink = dialogLayout.findViewById(R.id.editTextFilledZoomLink);

                    // Created a model object with the attributes from the user
                    ZoomClassModel model = new ZoomClassModel(editClassName.getText().toString(),
                            editZoomLink.getText().toString());

                    // Save the button name to the class box
                    currentButton.setText(model.getClassName());

                    // Add it to the database
                    databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
                    boolean success = databaseHelper.addSingleClass(model);
                }
            });
            // Create and show the dialog box
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            // AlertDialog Builder - this is used to create the dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            SpannableString title = new SpannableString(currentButton.getText().toString());
            title.setSpan(
                    new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                    0, title.length(), 0
            );
            builder.setTitle(title);

            // Set the layout to the dialog box layout - dialog_box_zoom_links_filled.xml
            View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_box_zoom_links_filled, null);
            builder.setView(dialogLayout);

            // These are different elements on screen like buttons and text boxes
            Button ok = dialogLayout.findViewById(R.id.button3);
            Button delete = dialogLayout.findViewById(R.id.button11);
            TextView zoomLink = dialogLayout.findViewById(R.id.editTextFilledZoomLink);

            // Get what is currently in the database
            databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
            List<ZoomClassModel> everything = databaseHelper.getEverything();

            // Extract the class name and zoom link from the current box
            final String link = everything.get(buttonNumber - 1).getLink();
            className = everything.get(buttonNumber - 1).getClassName();
            zoomLink.setText(link);

            final AlertDialog dialog = builder.create();
            dialog.show();

            // These run when the element in question is clicked upon
            zoomLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // This will handle opening the link in a browser or app
                    String url = link;
                    if (url.startsWith("https://") || url.startsWith("http://")) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    else {
                        url = "http://" + url;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.hide();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Creates a new model object with the class name and uses it to delete the
                    // entry in the database
                    ZoomClassModel model = new ZoomClassModel(className, "");
                    ZoomLinksDatabaseHelper db = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
                    db.deleteSingleClass(model);
                    // This will refresh the page to reflect the deleted class
                    refresh();

                    // Hide the box at the end
                    dialog.hide();
                }
            });
        }
    }

    // For testing, use this to show whats in the DB
    public void clear(View view) {
        List<ZoomClassModel> everything = databaseHelper.getEverything();

        Toast.makeText(ZoomLinksPage.this, everything.toString(), Toast.LENGTH_SHORT).show();
    }

    /** This finds the position of the button */
    public int getButtonNumber(View view) {
        int num = view.getId();
        for(int i = 0; i < buttonNames.length; i++) {
            if(buttonList[i].getId() == num) {
                return i + 1;
            }
        }
        return -1;
    }

    /** This will refresh the current page and reset deleted boxes */
    public void refresh() {
        // Get everything from the database and iterate up to how many there are and make
        // those boxes visible while resetting and hiding the rest
        everything = databaseHelper.getEverything();
        count = everything.size();

        for (int i = 0; i < buttonList.length; i++) {
            if (i < count) {
                buttonList[i].setVisibility(View.VISIBLE);
                buttonList[i].setText(everything.get(i).getClassName());
            }
            else {
                buttonList[i].setVisibility(View.GONE);
                buttonList[i].setText("Class");
            }
        }
    }
}