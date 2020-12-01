package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity
{
    //private declaration
    private TextView Screen;
    private String input="";
    private String Answer;
    private boolean clearEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Screen=findViewById(R.id.screen);
    }
    public void ButtonClick(View view)
    {
        Button button= (Button) view;
        String data=button.getText().toString();
        switch (data)
        {
            case "AC":
                //clear
                input="";
                break;
            case "Ans":
                //answer
                clearEmpty=false;
                //input = input + answer b4
                input+=Answer;
                break;
            case "x":
                //multiply
                clearEmpty=false;
                Solve();
                //solving function
                input+="*";
                //* is multiple
                break;
            case "^":
                clearEmpty=false;
                //exponential
                Solve();
                //solve function
                input+="^";
                //^ symbol for exponential
                break;
            case "=":
                //equal
                clearEmpty=true;
                Solve();
                //solve function
                Answer=input;
                //answer = input and input
                break;
            case "⬅":
                //go back
                if(input.length()>0)
                {
                    clearEmpty=false;
                    String newText=input.substring(0,input.length()-1);
                    //go back a spot command
                    input=newText;
                    //so new input
                }
                break;
                //end
            default:
                if(input==null)
                {
                    input="";
                    //empty
                }
                if(data.equals("+") || data.equals("-") || data.equals("/"))
                {
                    clearEmpty=false;
                    Solve();
                //solve function
                }
                else if(clearEmpty==true)
                {
                    input="";
                    clearEmpty=false;
                }
                input+=data;
        }
        Screen.setText(input);
    }
    public void Solve()
            //solve function
    {
        if(input.split("\\*").length==2)
        {
            String numbers[]=input.split("\\*");
            //try catch tester
            try
            {
                double mul=Double.parseDouble(numbers[0])*Double.parseDouble(numbers[1]);
                input=mul+"";
            }
            catch (Exception e)
            {
                //Display error
            }
        }
        else if(input.split("/").length==2)
        {
            String numbers[]=input.split("/");
            try
            {
                double div=Double.parseDouble(numbers[0])/Double.parseDouble(numbers[1]);
                input=div+"";
            }
            catch (Exception e)
            {
                //Display error
            }
        }
        else if(input.split("\\^").length==2)
        {
            String numbers[]=input.split("\\^");
            try
            {
                double pow=Math.pow(Double.parseDouble(numbers[0]),Double.parseDouble(numbers[1]));
                input=pow+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\+").length==2)
        {
            String numbers[]=input.split("\\+");
            try
            {
                double sum=Double.parseDouble(numbers[0])+Double.parseDouble(numbers[1]);
                input=sum+"";
            }
            catch (Exception e)
            {
                //Show the error
            }
        }
        else if(input.split("\\-").length>1)
        {
            String numbers[]=input.split("\\-");
            if(numbers[0]=="" && numbers.length==2)
            {
                numbers[0]=0+"";
            }
            try
            {
                double sub=0;
                if(numbers.length==2)
                {
                    sub = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if(numbers.length==3)
                {
                    sub = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                input=sub+"";
            }
            catch (Exception e)
            {
                //Show the error
            }
        }
        String numbers[]=input.split("\\.");
        if(numbers.length>1)
        {
            if(numbers[1].equals("0"))
            {
                input=numbers[0];
            }
        }
        Screen.setText(input);
    }
}