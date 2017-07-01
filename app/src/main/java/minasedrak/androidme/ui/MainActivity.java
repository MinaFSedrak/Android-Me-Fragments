package minasedrak.androidme.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import minasedrak.androidme.R;
import minasedrak.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean hasHeadIndex = false;
    private boolean hasBodyIndex = false;
    private boolean hasLegIndex = false;

    Button nextBtn;
    Intent androidMeIntent;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.viewDivider) != null){
            mTwoPane = true;

            // Deleting Next Button
            Button nextBtn = (Button) findViewById(R.id.next_Btn);
            nextBtn.setVisibility(View.GONE);

            //make GridView with 2 Colmuns
            GridView gridView = (GridView) findViewById(R.id.master_list_gridView);
            gridView.setNumColumns(2);

            if( savedInstanceState == null){

                    BodyPartFragment headFragment = new BodyPartFragment();
                    headFragment.setmImageIds(AndroidImageAssets.getHeads());

                    BodyPartFragment bodyFragment = new BodyPartFragment();
                    bodyFragment.setmImageIds(AndroidImageAssets.getBodies());

                    BodyPartFragment legFragment = new BodyPartFragment();
                    legFragment.setmImageIds(AndroidImageAssets.getLegs());


                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.head_container, headFragment)
                            .add(R.id.body_container, bodyFragment)
                            .add(R.id.leg_container, legFragment)
                            .commit();


            }


        }else {
            mTwoPane = false;

            nextBtn = (Button) findViewById(R.id.next_Btn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(androidMeIntent == null){
                        Toast.makeText(MainActivity.this, "Select at least one piece of your character", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(androidMeIntent);
                    }
                }
            });
        }




    }

    @Override
    public void onImageSelected(int position) {
        //Toast.makeText(this, "Postition = " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNum = position / 12;

        int mListIndex = position - 12*bodyPartNum;

        if(mTwoPane){

            BodyPartFragment newPiece = new BodyPartFragment();

            switch (bodyPartNum){
                case 0:
                    Toast.makeText(this, "Head piece selected", Toast.LENGTH_SHORT).show();
                    newPiece.setmImageIds(AndroidImageAssets.getHeads());
                    newPiece.setmListIndex(mListIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container, newPiece).commit();
                    break;
                case 1:
                    Toast.makeText(this, "Body piece selected", Toast.LENGTH_SHORT).show();
                    newPiece.setmImageIds(AndroidImageAssets.getBodies());
                    newPiece.setmListIndex(mListIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, newPiece).commit();
                    break;
                case 2:
                    Toast.makeText(this, "Leg piece selected", Toast.LENGTH_SHORT).show();
                    newPiece.setmImageIds(AndroidImageAssets.getLegs());
                    newPiece.setmListIndex(mListIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.leg_container, newPiece).commit();
                    break;
                default:
                    Toast.makeText(this, "Incorrect Choice Default of Switch has been run", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else {
            switch (bodyPartNum){
                case 0: headIndex = mListIndex;
                    Toast.makeText(this, "Head piece selected", Toast.LENGTH_SHORT).show();
                    hasHeadIndex = true;
                    break;
                case 1: bodyIndex = mListIndex;
                    Toast.makeText(this, "Body piece selected", Toast.LENGTH_SHORT).show();
                    hasBodyIndex = true;
                    break;
                case 2: legIndex = mListIndex;
                    Toast.makeText(this, "Leg piece selected", Toast.LENGTH_SHORT).show();
                    hasLegIndex = true;
                    break;
                default:
                    Toast.makeText(this, "Incorrect Choice Default of Switch has been run", Toast.LENGTH_SHORT).show();
                    break;
            }


            Bundle bodyParts = new Bundle();

            if(hasHeadIndex){
                bodyParts.putInt("headIndex", headIndex);}

            if(hasBodyIndex){
                bodyParts.putInt("bodyIndex", bodyIndex);}

            if(hasLegIndex){
                bodyParts.putInt("legIndex", legIndex);}

            androidMeIntent = new Intent(this, AndroidMeActivity.class);
            androidMeIntent.putExtras(bodyParts);
        }




    }

}
