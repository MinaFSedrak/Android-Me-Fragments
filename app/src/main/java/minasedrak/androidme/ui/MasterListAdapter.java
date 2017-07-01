package minasedrak.androidme.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by MinaSedrak on 7/1/2017.
 */

public class MasterListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mImageIds;

    public MasterListAdapter (Context context, List<Integer> imageIds){
        mContext = context;
        mImageIds = imageIds;
    }

    @Override
    public int getCount() {
        int size = mImageIds.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mImageIds.get(position));
        return imageView;
    }
}
