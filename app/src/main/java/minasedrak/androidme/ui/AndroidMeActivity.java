package minasedrak.androidme.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import minasedrak.androidme.R;
import minasedrak.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if( savedInstanceState == null){

            Intent mIntent = getIntent();
            Bundle bodyParts = mIntent.getExtras();

            if( bodyParts != null){
                headIndex = bodyParts.getInt("headIndex");
                bodyIndex = bodyParts.getInt("bodyIndex");
                legIndex = bodyParts.getInt("legIndex");

                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                headFragment.setmListIndex(headIndex);

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setmListIndex(bodyIndex);

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                legFragment.setmListIndex(legIndex);


                FragmentManager fragmentManager = getSupportFragmentManager();

                if(mIntent.hasExtra("headIndex")){
                    fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();}

                if(mIntent.hasExtra("bodyIndex")){
                    fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();}

                if(mIntent.hasExtra("legIndex")){
                    fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();}

               /* fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .add(R.id.body_container, bodyFragment)
                        .add(R.id.leg_container, legFragment)
                        .commit();*/
            }

        }else {
            Toast.makeText(this, "OnSavedInstanceStates is running", Toast.LENGTH_SHORT).show();
        }


        Button backBtn = (Button) findViewById(R.id.back_Btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(AndroidMeActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
            }
        });

    }
}
