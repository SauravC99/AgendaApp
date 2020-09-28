package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            if (buttonList[i].getVisibility() == View.INVISIBLE) {
                buttonList[i].setVisibility(View.VISIBLE);
            }
        }
    }

    /** Called when the user taps a Class Button */
    public void insertClassDetails(View view) {
        Button a;
        // Switch here so we know which button called this method
        switch (view.getId()) {
            case R.id.button4:
                a = findViewById(R.id.button4);
        }
    }
}