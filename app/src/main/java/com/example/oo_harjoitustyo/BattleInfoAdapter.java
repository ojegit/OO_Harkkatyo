package com.example.oo_harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleInfoAdapter extends RecyclerView.Adapter {

    //Store the incoming dialogue that is to be forwarded to RecycleView over here
    private ArrayList<BattleMsgContainer> battleMsgContainers;
    private Context context;


    public BattleInfoAdapter(Context context, ArrayList<BattleMsgContainer> battleMsgContainers) {
        this.context = context;
        this.battleMsgContainers = battleMsgContainers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch(battleMsgContainers.get(viewType).getMessageType()) {
            case RIGHT:
                return new BattleInfoHolderRight(LayoutInflater.from(context)
                        .inflate(R.layout.attack_item_right, parent, false));
            case LEFT:
                return new BattleInfoHolderLeft(LayoutInflater.from(context)
                        .inflate(R.layout.attack_item_left, parent, false));
            case TEXT:
                return new BattleInfoHolderMiddle(LayoutInflater.from(context)
                        .inflate(R.layout.attack_item_middle, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //positions are as follow until one perishes. If nothing happens between 1-9 then they repeat:
        BattleMsgContainer battleMsgContainer = battleMsgContainers.get(position);
        if(battleMsgContainer != null) {
            if(battleMsgContainer.getMessageType() == BattleMsgContainer.MessageType.LEFT) {
                BattleInfoAdapter.BattleInfoHolderLeft battleInfoHolderLeft = (BattleInfoAdapter.BattleInfoHolderLeft)holder;
                setBattleInfoHolderLeft(battleInfoHolderLeft,
                        battleMsgContainer.getLutemon(),
                        battleMsgContainer.getMessage());

            }else if(battleMsgContainer.getMessageType() == BattleMsgContainer.MessageType.RIGHT) {
                BattleInfoAdapter.BattleInfoHolderRight battleInfoHolderRight = (BattleInfoAdapter.BattleInfoHolderRight)holder;
                setBattleInfoHolderRight(battleInfoHolderRight,
                        battleMsgContainer.getLutemon(),
                        battleMsgContainer.getMessage());

            }else if(battleMsgContainer.getMessageType() == BattleMsgContainer.MessageType.TEXT) {
                BattleInfoAdapter.BattleInfoHolderMiddle battleInfoHolderMiddle = (BattleInfoAdapter.BattleInfoHolderMiddle)holder;
                setBattleInfoHolderMiddle(battleInfoHolderMiddle,
                        battleMsgContainer.getMessage());
            }

            /*
            switch (battleMsgContainer.getMessageType()) {
                case LEFT:
                    BattleInfoAdapter.BattleInfoHolderLeft battleInfoHolderLeft = (BattleInfoAdapter.BattleInfoHolderLeft) holder;
                    setBattleInfoHolderLeft(battleInfoHolderLeft,
                            battleMsgContainer.getLutemon(),
                            battleMsgContainer.getMessage());

                case RIGHT:
                    BattleInfoAdapter.BattleInfoHolderRight battleInfoHolderRight = (BattleInfoAdapter.BattleInfoHolderRight) holder;
                    setBattleInfoHolderRight(battleInfoHolderRight,
                            battleMsgContainer.getLutemon(),
                            battleMsgContainer.getMessage());

                case TEXT:
                    BattleInfoAdapter.BattleInfoHolderMiddle battleInfoHolderMiddle = (BattleInfoAdapter.BattleInfoHolderMiddle)holder;
                    setBattleInfoHolderMiddle(battleInfoHolderMiddle,
                            battleMsgContainer.getMessage());
            }
             */
        }
    }


    public void setBattleInfoHolderMiddle(BattleInfoAdapter.BattleInfoHolderMiddle battleInfoHolderMiddle, String txt) {
        battleInfoHolderMiddle.tvMiddleBattle.setText(txt);
    }

    public void setBattleInfoHolderLeft(BattleInfoAdapter.BattleInfoHolderLeft battleInfoHolderLeft, Lutemon lutemon, String txt) {
        battleInfoHolderLeft.ivLeftBattle.setImageResource(lutemon.getImageSrc());
        battleInfoHolderLeft.tvLeftBattle.setText(txt);
        battleInfoHolderLeft.tvTimeStampLeftBattle.setText(""); //time stamp
    }

    public void setBattleInfoHolderRight(BattleInfoAdapter.BattleInfoHolderRight battleInfoHolderRight, Lutemon lutemon, String txt) {
        battleInfoHolderRight.ivRightBattle.setImageResource(lutemon.getImageSrc());
        battleInfoHolderRight.tvRightBattle.setText(txt);
        battleInfoHolderRight.tvTimeStampRightBattle.setText(""); //time stamp
    }


    @Override
    public int getItemViewType(int position) {
        //match position to viewType 1:1
        return position;
    }

    @Override
    public int getItemCount() {
        return battleMsgContainers.size();
    }


    //
    //Holder subclasses
    //

    //Left holder
    public static class BattleInfoHolderLeft extends RecyclerView.ViewHolder {

        public ImageView ivLeftBattle;
        public TextView tvLeftBattle, tvTimeStampLeftBattle;

        public BattleInfoHolderLeft(@NonNull View itemView) {
            super(itemView);
            this.ivLeftBattle = (ImageView)itemView.findViewById(R.id.ivLeftBattle);
            this.tvLeftBattle = (TextView)itemView.findViewById(R.id.tvLeftBattle);
            this.tvTimeStampLeftBattle = (TextView)itemView.findViewById(R.id.tvTimeStampLeftBattle);
        }
    }

    //Right holder
    public static class BattleInfoHolderRight extends RecyclerView.ViewHolder {
        public ImageView ivRightBattle;
        public TextView tvRightBattle, tvTimeStampRightBattle;

        public BattleInfoHolderRight(@NonNull View itemView) {
            super(itemView);
            this.ivRightBattle = (ImageView)itemView.findViewById(R.id.ivRightBattle);
            this.tvRightBattle = (TextView)itemView.findViewById(R.id.tvRightBattle);
            this.tvTimeStampRightBattle = (TextView)itemView.findViewById(R.id.tvTimeStampRightBattle);
        }
    }

    //Middle
    public static class BattleInfoHolderMiddle extends RecyclerView.ViewHolder {
        public TextView tvMiddleBattle;
        public BattleInfoHolderMiddle(@NonNull View itemView) {
            super(itemView);
            this.tvMiddleBattle = (TextView)itemView.findViewById(R.id.tvMiddleBattle);
        }
    }
}
