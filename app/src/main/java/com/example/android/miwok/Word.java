package com.example.android.miwok;

import android.media.Image;

/**
 * Created by Brian on 2017/07/10.
 */

public class Word {


    //Default Translation for the words
    private  String mDefaultTranslation;

    //Miwork Translation for the words
    private String mMiworkTranslation;

    //Image resourece id of the word
    private int mImageResourcedId = No_IMAGE_PROVIDED;

    private static final int No_IMAGE_PROVIDED =-1;

    //Audio Resouced ID for the word
    private  int mAudioResourceId;


    //First Contructor taking twp prams eg. miwork and miwok translation
    public Word(String defaultTranslation,String miworkTranslation, int audioResourceId)
    {
            mDefaultTranslation = defaultTranslation;
            mMiworkTranslation = miworkTranslation;
            mAudioResourceId = audioResourceId;
    }

    //Second Contructor taking one prams for Image
    public Word(String defaultTranslation,String miworkTranslation, int imageResourcedId,int audioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiworkTranslation = miworkTranslation;
        mImageResourcedId = imageResourcedId;
        mAudioResourceId = audioResourceId;
    }

    //get the default translation
    public String getmDefaultTranslation(){
        return mDefaultTranslation;
    }

    //get the Miwork translation
    public String getmMiworkTranslation()
    {
        return mMiworkTranslation;
    }

    //get the Image resouce
    public int getmImageResourcedId(){return mImageResourcedId; }

    //get the Audio resouceID
    public int getAudioResourceId(){return mAudioResourceId; }

    //return whether or not there is am image for the word
    public boolean hasImage(){

        //there is no image !=
        return  mImageResourcedId != No_IMAGE_PROVIDED;
    }
}
