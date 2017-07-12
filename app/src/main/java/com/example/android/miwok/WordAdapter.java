package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Brian on 2017/07/10.
 */

public class WordAdapter  extends ArrayAdapter<Word> {


    //resource id for backgroud color for each list in the intent
    private int mColorResouceId;

     public WordAdapter(Activity context, ArrayList<Word> words,int colorResouceId)
     {

         super(context, 0 ,words);
         mColorResouceId = colorResouceId;
     }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Checking if the existing wiew is beinbf reused,otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        //Get the Bject lacated at this position in the list
        Word currentWord = getItem(position);

        //find the textview in the list_items layout with the ID version name
        TextView miworkTextView = (TextView) listItemView.findViewById(R.id.miwork_text_view);

        //get The version name from the current ob
        miworkTextView.setText(currentWord.getmMiworkTranslation());

        //find the textview in the list_items layout with the ID version number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        //get The version name from the current ob
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        //get the version Number from the currrent List_items.xml layout with the ID list_item_icon
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        //Checking the image property in the WordAdapter
        if(currentWord.hasImage()){

            //Set the ImageView to the image resource specifued in the current word
            imageView.setImageResource(currentWord.getmImageResourcedId());

            //Make sure thew view is visible
            imageView.setVisibility(View.VISIBLE);
        }else {
            //Otherwise hide the ImageView(setVisibility to Gone)
            imageView.setVisibility(View.GONE);
        }

        //set the theme color for the list_item id in XML
        View textContainer = listItemView.findViewById(R.id.text_container);

        //find the color that the resouce ID maps to
        int color = ContextCompat.getColor(getContext(),mColorResouceId);

        //Set the backGroudColor of the container View
        textContainer.setBackgroundColor(color);

        //return the whole list item layout(contaning 2 Textviews)so that it can be shown in the listView
        return listItemView;
    }
}
