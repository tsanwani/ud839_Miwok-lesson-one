/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class NumbersActivity extends AppCompatActivity {

    //Handles playback of all the sound files
    private MediaPlayer mMediaplayer;

    //Handles audio focus when palying a sound file
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener myAudioFocus = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaplayer.pause();
                mMediaplayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //resume back

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener () {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_list);

        //Create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);



        //Creating a List of words Object  /Decalring an Array List
       final ArrayList<Word> words = new ArrayList<Word>();

        //words Class contructor reference
        words.add(new Word("One","Lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("Two","Otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("Three","Tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("Four","Oyyiso",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("Five","Massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("Six","Temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("Seven","Kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("Eight","Kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("Nine","Wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("Ten","Na'aacha",R.drawable.number_ten,R.raw.number_ten));


        //Passing an Array For Displaying Recycling
        //there input prams in the adpter based on the WordAdapter Class constructor
        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);

        //ListVew On the XML Side
       ListView listView = (ListView)findViewById(R.id.list);

        //Displaying adapter array to the listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Catching the position of the auido  resource file clicked on
                Word word = words.get(i);

                //Release Media Player if its currently exists to play different sound
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(myAudioFocus,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //Audio focus start hare

                    // with the current word
                    mMediaplayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    //start auidio file
                    mMediaplayer.start();

                    //Setup a listener on the media play,so that we can stop and release it
                    mMediaplayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        //When the activity is stopped, release the media player resources
        //stop playing anymore
        releaseMediaPlayer();
    }



    //Cleaning up the Media Player by Releasing its resources
    public void releaseMediaPlayer()
    {
        //if Media player is not null it. might be currently playing a sound
        if(mMediaplayer!=null)
        {
            //r5egardless its state. it must release the resources
            mMediaplayer.release();

            //setting up Media Player back to null/not being configured
            mMediaplayer=null;

            //regardless auidio is used abondon it.
            mAudioManager.abandonAudioFocus(myAudioFocus);
        }
    }
}
