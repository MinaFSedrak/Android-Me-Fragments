package minasedrak.androidme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import minasedrak.androidme.R;
import minasedrak.androidme.data.AndroidImageAssets;

/**
 * Created by MinaSedrak on 7/1/2017.
 */

public class BodyPartFragment extends Fragment {

    private List<Integer> mImageIds;
    private int mListIndex;

    private static final String TAG = "BodyPartFragment";
    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";

    public BodyPartFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_imageView);

        if(mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int size = mImageIds.size()-1;

                    if(mListIndex < size){
                        mListIndex++;
                    }else{
                        mListIndex = 0;
                    }

                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });

        }else {
            Log.e(TAG, "body fragment has a null list of image ids");
        }


        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }


    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }

}
