package com.blackboxstudios.agendaplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class zzTestHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zztesthome);
    }

    public void a(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
    public void b(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
    public void c(View view) {
        Intent intent = new Intent(this, ZoomLinksPage.class);
        startActivity(intent);
    }
    public void d(View view) {
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);
    }
    public void e(View view) {
        Intent intent = new Intent(this, GuestLogInPage.class);
        startActivity(intent);
    }
    public void f(View view) {
        Intent intent = new Intent(this, HomePage_Navigation.class);
        startActivity(intent);
    }
    public void g(View view) {
        Intent intent = new Intent(this, ClassScheduleActivity.class);
        startActivity(intent);
    }
}
