package seniorproject.badger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import static seniorproject.badger.R.layout.activity_badgescreen;


public class BadgeScreen extends AppCompatActivity {

    ImageButton button, button1, button2, button3, button4;
    Button submitButton;
    ImageView image;
    EditText edit, edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_badgescreen);

        addListenerOnButton();


    }


    public void addListenerOnButton(){

        image = (ImageView) findViewById(R.id.imageView);

        button = (ImageButton) findViewById(R.id.imageButton2);
        button1 = (ImageButton) findViewById(R.id.imageButton3);
        button2 = (ImageButton) findViewById(R.id.imageButton4);
        button3 = (ImageButton) findViewById(R.id.imageButton5);
        button4 = (ImageButton) findViewById(R.id.imageButton6);
        submitButton = (Button) findViewById(R.id.submitButton);
        final EditText edit = (EditText) findViewById(R.id.editText);
        final EditText edit1 = (EditText) findViewById(R.id.editText1);

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
            }

        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge2);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge3);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
            }

        });


        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge4);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
            }

        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.badge5);
                edit.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
            }

        });


    }

}

