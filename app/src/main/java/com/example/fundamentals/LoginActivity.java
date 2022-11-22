package com.example.fundamentals;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button boton;
    TextView text;
    Button login;
    static String MESSAGE_NOMBRE, TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boton=findViewById(R.id.login);
        text=findViewById(R.id.SingIn);
        login=findViewById(R.id.login);


        

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApp();
            }
        });
        
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paginaSiguiente();
            }
        });

/*
//    Glide for loading girls
        ImageView mGirl = findViewById(R.id.logosplash);

        Glide.with(this)
                .load("data:image/jpeg;base64,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")
                //.load(R.drawable.messi)
                .transition(DrawableTransitionOptions.withCrossFade(100))

//                .centerCrop()
                  .placeholder(new ColorDrawable(this.getResources().getColor(R.color.purple_200)))
//                .circleCrop()
                .into(mGirl);*/

        ImageView mMessi = findViewById(R.id.icono);

        Glide.with(this)
                .load(R.drawable.messi)
                .transition(DrawableTransitionOptions.withCrossFade(100))

//                .centerCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.fucsia_200)))
                .circleCrop()
                .into(mMessi);

        ImageView thunder = findViewById(R.id.icono);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.zoom);
        thunder.startAnimation(myanim);








    }




    public void paginaSiguiente(){
        Intent intent = new Intent(this, SingUpActivity.class); //This llama a la propia magina y se crea la otra
        activityResult.launch(intent);


    }
    public void mainApp(){
        Intent intent = new Intent(this, Main.class); //This llama a la propia magina y se crea la otra
        activityResult.launch(intent);


    }
    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, ""+result.getResultCode());
                                    }
            }
    );

}