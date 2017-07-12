package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CodeTribe on 2017/07/11.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public  WordAdapter(Activity context, ArrayList<Word> androidFlavours){
        super(context, 0, androidFlavours);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);
        TextView miworkTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miworkTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTexView = (TextView) listItemView.findViewById(R.id.defaulty_tex_view);
        defaultTexView.setText(currentWord.getDefaultTranslation());

        return listItemView;
    }

}
