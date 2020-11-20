package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
/*
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

        pref = getPreferences(MODE_PRIVATE);

        // Created a list of Buttons and added the buttons on screen
        // We are doing i + 1 on line 26 cause buttonNames has 7, but buttonList has 6
        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i] = findViewById(buttonNames[i + 1]);
        }

        ZoomLinksDatabaseHelper helper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
        List<ZoomClassModel> everything = helper.getEverything();



        //Check if the button has been modified by the user and sets them accordingly
        if (pref.contains(COUNT)) {
            count = pref.getInt(COUNT, 0);
        }
        for (int i = 0; i < everything.size(); i++) {
            //int vis = pref.getInt(Integer.toString(buttonList[i].getId()), View.GONE);
            buttonList[i].setVisibility(View.VISIBLE);
            //String text = pref.getString(String.valueOf(i + 1), "CLASS");
            buttonList[i].setText(everything.get(i).getClassName());
        }
    }

    // Called when the user taps the Add Class Button
    public void addClass(View view) {
        pref = getPreferences(MODE_PRIVATE);
        editor = pref.edit();

        //Get the num the counter was at last time user used this screen
        if (pref.contains(COUNT)) {
            count = pref.getInt(COUNT, 0);
        }
        // Add 1 to count bc we want to know another button is shown unless its at max
        if (count < buttonList.length) {
            count++;
            editor.putInt(COUNT, count);
            editor.apply();
        }
        //Iterate up to count and make those buttons visible if they are not
        for (int i = 0; i < count; i++) {
            buttonList[i].setVisibility(View.VISIBLE);
            editor.putInt(Integer.toString(buttonList[i].getId()), buttonList[i].getVisibility());
            editor.apply();
        }
    }

    // Called when the user taps a Class Button
    public void insertClassDetails(final View view) {
        pref = getPreferences(MODE_PRIVATE);
        editor = pref.edit();
        final Button b = findViewById(view.getId());
        final int buttonNum = getButtonNum(view);

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
                    String[] deets = {editClassName.getText().toString(), editZoomLink.getText().toString()};

                    //saveClassDetails(editClassName.getText().toString(), view);
                    //saveClassDetails(editZoomLink.getText().toString(), view);

                    //saveClassDetails(deets, view);
                    ZoomClassModel model = new ZoomClassModel(getButtonNum(view), deets[0], deets[1]);
                    //Toast.makeText(this, " " + model.toString(), Toast.LENGTH_SHORT).show();

                    ZoomLinksDatabaseHelper databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);

                    boolean b = databaseHelper.addSingleClass(model);
                    //Toast.makeText(this, "Success = " + b, Toast.LENGTH_SHORT).show();
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
            //final String link = pref.getString(count + "#", "");
            ZoomLinksDatabaseHelper databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
            ZoomClassModel a = databaseHelper.getSingleClass(buttonNum);
            final String link = a.getLink();
            zoomLink.setText(link);

            Button ok = dialogLayout.findViewById(R.id.button3);
            Button delete = dialogLayout.findViewById(R.id.button11);

            final AlertDialog dialog = builder.create();
            dialog.show();

            zoomLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                    ZoomClassModel model = new ZoomClassModel(buttonNum, "", "");
                    ZoomLinksDatabaseHelper db = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
                    boolean bb = db.deleteSingleClass(model);

                    if (bb) {
                        b.setText("Class");
                    }
                    dialog.hide();
                }
            });
        }
    }

    // Do something with the data coming from the AlertDialog
    // Takes in a View parameter so we know where the request came from
    private void saveClassDetails(String[] data, View view) {
        pref = getPreferences(MODE_PRIVATE);
        editor = pref.edit();

        editor.putString(String.valueOf(count), data[0]);
        editor.apply();
        String modded = count + "#";
        editor.putString(modded, data[1]);
        editor.apply();


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

        ZoomLinksDatabaseHelper helper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
        List<ZoomClassModel> everything = helper.getEverything();

        //String buttonN = Integer.toString(everything.get(0).getButtonNum());
        Toast.makeText(ZoomLinksPage.this, everything.toString(), Toast.LENGTH_SHORT).show();
    }
    public int getButtonNum(View view) {
        int num = view.getId();
        for(int i = 0; i < buttonNames.length; i++) {
            if(buttonList[i].getId() == num) {
                return i + 1;
            }
        }
        return -1;
    }
}
*/
public class ZoomLinksPage extends AppCompatActivity {
    // Created an int array of Button ids
    //buttonNames contains the IDs of the buttons
    final int[] buttonNames =
            {R.id.button, R.id.button4, R.id.button5, R.id.button6,
                    R.id.button7, R.id.button8, R.id.button9, R.id.button10};

    //buttonList contains the Button objects themselves
    Button[] buttonList = new Button[buttonNames.length - 1];
    int count = 0; //Count variable to keep track of visible buttons

    ZoomLinksDatabaseHelper databaseHelper;
    List<ZoomClassModel> everything;

    /** Called when this activity comes to attention */
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

        //Iterate up to count and make those buttons visible if they are not
        //for (int i = 0; i < count; i++) {
            //buttonList[i].setVisibility(View.VISIBLE);
        //}

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

                    ZoomClassModel model = new ZoomClassModel(buttonNumber,
                            editClassName.getText().toString(), editZoomLink.getText().toString());

                    // Save the button name to the class
                    currentButton.setText(model.getClassName());

                    databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
                    boolean success = databaseHelper.addSingleClass(model);
                    //System.out.println("Success = " + success);
                }
            });
            // Create and show the dialog box
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            // AlertDialog Builder - this is used to create the dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(currentButton.getText().toString());

            // Set the layout to the dialog box layout - dialog_box_zoom_links_filled.xml
            View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_box_zoom_links_filled, null);
            builder.setView(dialogLayout);

            Button ok = dialogLayout.findViewById(R.id.button3);
            Button delete = dialogLayout.findViewById(R.id.button11);

            TextView zoomLink = dialogLayout.findViewById(R.id.editTextFilledZoomLink);
            databaseHelper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
            List<ZoomClassModel> everything = databaseHelper.getEverything();

            final String link = everything.get(buttonNumber - 1).getLink();
            zoomLink.setText(link);

            final AlertDialog dialog = builder.create();
            dialog.show();

            zoomLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                    ZoomClassModel model = new ZoomClassModel(buttonNumber, "", "");
                    ZoomLinksDatabaseHelper db = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
                    boolean success = db.deleteSingleClass(model);

                    if (success) {
                        currentButton.setText("Class");
                    }
                    dialog.hide();
                }
            });
        }
    }













    public void clear(View view) {

        //ZoomLinksDatabaseHelper helper = new ZoomLinksDatabaseHelper(ZoomLinksPage.this);
        List<ZoomClassModel> everything = databaseHelper.getEverything();

        //String buttonN = Integer.toString(everything.get(0).getButtonNum());
        Toast.makeText(ZoomLinksPage.this, everything.toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(ZoomLinksPage.this, Integer.toString(count), Toast.LENGTH_SHORT).show();
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
}