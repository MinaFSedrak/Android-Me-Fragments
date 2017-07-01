package minasedrak.androidme.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import minasedrak.androidme.R;
import minasedrak.androidme.data.AndroidImageAssets;

/**
 * Created by MinaSedrak on 7/1/2017.
 */

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallBack;

    public MasterListFragment(){}

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }


    // Check if the host activity implements CallBack interface or not
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallBack = (OnImageClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.master_list_gridView);

        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallBack.onImageSelected(position);
            }
        });

        return rootView;
    }




}
