package seniorproject.badger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import static seniorproject.badger.R.layout.activity_badgescreen;


public class BadgeScreen extends AppCompatActivity {

    ImageButton button, button1, button2, button3, button4;
    protected int selected;
    protected Button submitButton;
    protected ArrayList<Badge> badgeList;
    protected ViewGroup scrollLayout;
    ImageView image;
    EditText badgeNameText, badgeDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_badgescreen);
        scrollLayout = (ViewGroup) findViewById(R.id.scrollLayout);

        addListenerOnButton();
        selected = 0;

    }


    public void addListenerOnButton(){

        image = (ImageView) findViewById(R.id.imageView);

        button = (ImageButton) findViewById(R.id.imageButton2);
        button1 = (ImageButton) findViewById(R.id.imageButton3);
        button2 = (ImageButton) findViewById(R.id.imageButton4);
        button3 = (ImageButton) findViewById(R.id.imageButton5);
        button4 = (ImageButton) findViewById(R.id.imageButton6);

        submitButton = (Button) findViewById(R.id.submitButton);
        final EditText edit = (EditText) findViewById(R.id.badgeNameEditText);
        final EditText edit1 = (EditText) findViewById(R.id.badgeDescriptionEditText);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                image.setImageResource(R.drawable.badge1);
                image.setImageResource(R.drawable.badge2);
                image.setImageResource(R.drawable.badge3);
                image.setImageResource(R.drawable.badge4);
                image.setImageResource(R.drawable.badge5);
            }

        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge1);

                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                selected = 1;
            }

        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge2);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                selected = 2;
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge3);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                selected = 3;
            }

        });


        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge4);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                selected = 4;
            }

        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge5);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                selected = 5;
            }

        });

    }

}

