package com.example.android.miwok;

import java.util.ArrayList;

/**
 * {@link Word} represent a vocabulary word that the user want to learn
 * It contains a default translation and Miwork translation for that word.
 */

public class Word {
    //** Default translation for the word * word*/
     private  String mDefaultTranslation;

    //** Miwok translation for the word */
    private String mMiwokTranslation;

    public  Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    //**
    //get default translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * get the miwok translation of the word
     */
    public  String getMiwokTranslation(){
        return  mMiwokTranslation;
    }

}
