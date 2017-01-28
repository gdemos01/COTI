package com.app.coti.coti.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.coti.coti.MainActivity;
import com.app.coti.coti.R;
import com.app.coti.coti.models.Knowledge;
import com.app.coti.coti.models.NegativeResponse;
import com.app.coti.coti.models.PositiveResponse;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Giorgos on 27/01/2017.
 */

public class COTI extends Fragment {

    private View view;
    private TextView agree;
    private TextView disagree;
    private TextView skip;
    private TextView report;
    private LinearLayout questionResponses;
    public static  Typewriter writer;
    public static ArrayList<Knowledge> knowledge;
    private PositiveResponse positiveResponse;
    private NegativeResponse negativeResponse;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public COTI() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static COTI newInstance(int sectionNumber) {
        COTI fragment = new COTI();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view==null){
            view =  inflater.inflate(R.layout.fragment_main, container, false);
        }

        agree = (TextView)view.findViewById(R.id.agree);
        disagree = (TextView) view.findViewById(R.id.disagree);
        skip = (TextView) view.findViewById(R.id.skip);

        writer = new Typewriter((TextView)view.findViewById(R.id.tongue_coti));
        questionResponses = (LinearLayout) view.findViewById(R.id.questionResponses);

        knowledge = MainActivity.memory.getKnowledge();

        if(knowledge.size()<10){
            /**
             * Download Knowledge
             */
        }

        knowledge.add(new Knowledge(1,"Nazis are very cool",3,1,0,0));
        knowledge.add(new Knowledge(2,"Eating dick could kill you",3,1,0,0));
        knowledge.add(new Knowledge(3,"Should I be a virgin?",1,3,0,0));

        positiveResponse = new PositiveResponse();
        negativeResponse = new NegativeResponse();

        writer.setCharacterDelay(100);
        writer.animateText("Hi! My name is COTI");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                writer.setCharacterDelay(200);
                writer.animateText(".....");
            }
        },4000);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(knowledge.size()<=0){
                    writer.setCharacterDelay(80);
                    writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                    questionResponses.setVisibility(View.GONE);
                }else{
                    questionResponses.setVisibility(View.VISIBLE);
                    if(knowledge.get(0).getYes()>=knowledge.get(0).getNo()){
                        Random generator = new Random();
                        int i = generator.nextInt(positiveResponse.getAmount());

                        writer.setCharacterDelay(80);
                        writer.animateText(knowledge.get(0).getKnowledge()
                                +"\n.....\n"+positiveResponse.getResponse(i));
                    }else{
                        Random generator = new Random();
                        int i = generator.nextInt(negativeResponse.getAmount());

                        writer.setCharacterDelay(80);
                        writer.animateText(knowledge.get(0).getKnowledge()
                                +"\n.....\n"+negativeResponse.getResponse(i));
                    }

                }
            }
        },5500);


        agree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (knowledge.size() <= 0) {
                    writer.setCharacterDelay(80);
                    writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                    questionResponses.setVisibility(View.GONE);
                } else {
                    questionResponses.setVisibility(View.VISIBLE);
                    Random generator = new Random();
                    int i = generator.nextInt(positiveResponse.getAmount());
                    writer.setCharacterDelay(80);
                    writer.animateText(positiveResponse.getAgreement(i));

                    MainActivity.memory.deleteKnowledge(knowledge.get(0).getId());
                    knowledge.remove(0);
                    // Download new knowledge
                    // Update Online knowledge

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            writer.setCharacterDelay(200);
                            writer.animateText(".....");
                        }
                    }, 1500);

                    final Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (knowledge.size() <= 0) {
                                writer.setCharacterDelay(80);
                                writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                            } else {
                                if (knowledge.get(0).getYes() >= knowledge.get(0).getNo()) {
                                    Random generator = new Random();
                                    int i = generator.nextInt(positiveResponse.getAmount());

                                    writer.setCharacterDelay(80);
                                    writer.animateText(knowledge.get(0).getKnowledge()
                                            + "\n.....\n" + positiveResponse.getResponse(i));
                                } else {
                                    Random generator = new Random();
                                    int i = generator.nextInt(negativeResponse.getAmount());

                                    writer.setCharacterDelay(80);
                                    writer.animateText(knowledge.get(0).getKnowledge()
                                            + "\n.....\n" + negativeResponse.getResponse(i));
                                }

                            }
                        }
                    }, 2500);
                }
            }
        });

        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (knowledge.size() <= 0) {
                    writer.setCharacterDelay(80);
                    writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                    questionResponses.setVisibility(View.GONE);
                } else {
                    questionResponses.setVisibility(View.VISIBLE);
                    Random generator = new Random();
                    int i = generator.nextInt(negativeResponse.getAmount());
                    writer.setCharacterDelay(80);
                    writer.animateText(negativeResponse.getDisagreement(i));

                    MainActivity.memory.deleteKnowledge(knowledge.get(0).getId());
                    knowledge.remove(0);
                    // Download new knowledge
                    // Update online knowledge

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            writer.setCharacterDelay(200);
                            writer.animateText(".....");
                        }
                    }, 1500);

                    final Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (knowledge.size() <= 0) {
                                writer.setCharacterDelay(80);
                                writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                            } else {
                                if (knowledge.get(0).getYes() >= knowledge.get(0).getNo()) {
                                    Random generator = new Random();
                                    int i = generator.nextInt(positiveResponse.getAmount());

                                    writer.setCharacterDelay(80);
                                    writer.animateText(knowledge.get(0).getKnowledge()
                                            + "\n.....\n" + positiveResponse.getResponse(i));
                                } else {
                                    Random generator = new Random();
                                    int i = generator.nextInt(negativeResponse.getAmount());

                                    writer.setCharacterDelay(80);
                                    writer.animateText(knowledge.get(0).getKnowledge()
                                            + "\n.....\n" + negativeResponse.getResponse(i));
                                }

                            }
                        }
                    }, 2500);
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (knowledge.size() <= 0) {
                    writer.setCharacterDelay(80);
                    writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                    questionResponses.setVisibility(View.GONE);
                } else {
                    questionResponses.setVisibility(View.VISIBLE);
                    writer.setCharacterDelay(80);
                    writer.animateText("Skipping..");

                    MainActivity.memory.deleteKnowledge(knowledge.get(0).getId());
                    knowledge.remove(0);
                    // Download new knowledge
                    // Update online knowledge

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            writer.setCharacterDelay(200);
                            writer.animateText(".....");
                        }
                    }, 1500);

                    final Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (knowledge.size() <= 0) {
                                writer.setCharacterDelay(80);
                                writer.animateText("Nothing on my mind...Am I connected to the NET ?");
                            } else {
                                if (knowledge.get(0).getYes() >= knowledge.get(0).getNo()) {
                                    Random generator = new Random();
                                    int i = generator.nextInt(positiveResponse.getAmount());

                                    writer.setCharacterDelay(80);
                                    writer.animateText(knowledge.get(0).getKnowledge()
                                            + "\n.....\n" + positiveResponse.getResponse(i));
                                } else {
                                    Random generator = new Random();
                                    int i = generator.nextInt(negativeResponse.getAmount());

                                    writer.setCharacterDelay(80);
                                    writer.animateText(knowledge.get(0).getKnowledge()
                                            + "\n.....\n" + negativeResponse.getResponse(i));
                                }

                            }
                        }
                    }, 2500);
                }
            }
        });

        return view;
    }

    public class Typewriter {

        private CharSequence mText;
        private int mIndex;
        private long mDelay = 500; //Default 500ms delay
        private TextView txtView;


        public Typewriter(TextView textView) {
            this.txtView = textView;
        }

        private Handler mHandler = new Handler();
        private Runnable characterAdder = new Runnable() {
            @Override
            public void run() {
                txtView.setText(mText.subSequence(0, mIndex++));
                if(mIndex <= mText.length()) {
                    mHandler.postDelayed(characterAdder, mDelay);
                }
            }
        };

        public void animateText(CharSequence text) {
            mText = text;
            mIndex = 0;

            txtView.setText("");
            mHandler.removeCallbacks(characterAdder);
            mHandler.postDelayed(characterAdder, mDelay);
        }

        public void setCharacterDelay(long millis) {
            mDelay = millis;
        }
    }

}