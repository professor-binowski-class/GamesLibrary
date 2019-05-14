package com.example.mockuppage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


public class ViewDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private CustomButton rogueLikeButton;
    private CustomButton rolePlayingButton;
    private CustomButton shooterButton;
    private CustomButton adventureButton;
    private CustomButton strategyButton;
    private CustomButton sportsButton;
    private CustomButton actionButton;
    private CustomButton boardButton;
    private TextView goTextView;


    public ViewDialog(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        setCancelable(false);

        rogueLikeButton = findViewById(R.id.rogueLikeButton);
        rogueLikeButton.setOnClickListener(this);

        rolePlayingButton = findViewById(R.id.rolePlayingButton);
        rolePlayingButton.setOnClickListener(this);

        shooterButton = findViewById(R.id.shooterButton);
        shooterButton.setOnClickListener(this);

        adventureButton = findViewById(R.id.adventureButton);
        adventureButton.setOnClickListener(this);

        strategyButton = findViewById(R.id.strategyButton);
        strategyButton.setOnClickListener(this);

        sportsButton = findViewById(R.id.sportsButton);
        sportsButton.setOnClickListener(this);

        actionButton = findViewById(R.id.actionButton);
        actionButton.setOnClickListener(this);

        boardButton = findViewById(R.id.boardButton);
        boardButton.setOnClickListener(this);

        goTextView = findViewById(R.id.goTextView);
        goTextView.setOnClickListener(this);

    }

    @Override
    public void dismiss() { //when dialog is dismissed
        super.dismiss();
        StringBuilder mBuilder = new StringBuilder();

        /**
         * if(rogueLikeButton.isUnSelectedState())
         *             mBuilder.append("\n\nRogueLike Button: ").append(rogueLikeButton.getCurrentState());
         */

        if(rogueLikeButton.isSelectedState())
            mBuilder.append("\n\nRogueLike Button: ").append(rogueLikeButton.getCurrentState());
        if(rogueLikeButton.isDefaultState())
            mBuilder.append("\n\nRogueLike Button: ").append(rogueLikeButton.getCurrentState());

        if(rolePlayingButton.isSelectedState())
            mBuilder.append("\n\nRole Playing Button: ").append(rolePlayingButton.getCurrentState());
        if(rolePlayingButton.isDefaultState())
            mBuilder.append("\n\nRole Playing Button: ").append(rolePlayingButton.getCurrentState());

        if(shooterButton.isSelectedState())
            mBuilder.append("\n\nShooter Button: ").append(shooterButton.getCurrentState());
        if(shooterButton.isDefaultState())
            mBuilder.append("\n\nShooter Button: ").append(shooterButton.getCurrentState());

        if(adventureButton.isSelectedState())
            mBuilder.append("\n\nAdventure Button: ").append(adventureButton.getCurrentState());
        if(adventureButton.isDefaultState())
            mBuilder.append("\n\nAdventure Button: ").append(adventureButton.getCurrentState());

        if(strategyButton.isSelectedState())
            mBuilder.append("\n\nStrategy Button: ").append(strategyButton.getCurrentState());
        if(strategyButton.isDefaultState())
            mBuilder.append("\n\nStrategy Button: ").append(strategyButton.getCurrentState());

        if(sportsButton.isSelectedState())
            mBuilder.append("\n\nSports Button: ").append(sportsButton.getCurrentState());
        if(sportsButton.isDefaultState())
            mBuilder.append("\n\nSports Button: ").append(sportsButton.getCurrentState());

        if(actionButton.isSelectedState())
            mBuilder.append("\n\nAction Button: ").append(actionButton.getCurrentState());
        if(actionButton.isDefaultState())
            mBuilder.append("\n\nAction Button: ").append(actionButton.getCurrentState());

        if(boardButton.isSelectedState())
            mBuilder.append("\n\nBoard Button: ").append(boardButton.getCurrentState());
        if(boardButton.isDefaultState())
            mBuilder.append("\n\nBoard Button: ").append(boardButton.getCurrentState());

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        intent.putExtra("result", mBuilder.toString());
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == goTextView) {
            dismiss();
        }

        if (v == rogueLikeButton) {
            if (rogueLikeButton.isDefaultState()) {
                rogueLikeButton.setDefaultState(false);
                rogueLikeButton.setSelectedState(true);
            } else if (rogueLikeButton.isSelectedState()) {
                rogueLikeButton.setSelectedState(false);
                rogueLikeButton.setUnSelectedState(true);
            } else if (rogueLikeButton.isUnSelectedState()) {
                rogueLikeButton.setUnSelectedState(false);
                rogueLikeButton.setDefaultState(true);
            }
        }

        if (v == rolePlayingButton) {
            if (rolePlayingButton.isDefaultState()) {
                rolePlayingButton.setDefaultState(false);
                rolePlayingButton.setSelectedState(true);
            } else if (rolePlayingButton.isSelectedState()) {
                rolePlayingButton.setSelectedState(false);
                rolePlayingButton.setUnSelectedState(true);
            } else if (rolePlayingButton.isUnSelectedState()) {
                rolePlayingButton.setUnSelectedState(false);
                rolePlayingButton.setDefaultState(true);
            }
        }

        if (v == shooterButton) {
            if (shooterButton.isDefaultState()) {
                shooterButton.setDefaultState(false);
                shooterButton.setSelectedState(true);
            } else if (shooterButton.isSelectedState()) {
                shooterButton.setSelectedState(false);
                shooterButton.setUnSelectedState(true);
            } else if (shooterButton.isUnSelectedState()) {
                shooterButton.setUnSelectedState(false);
                shooterButton.setDefaultState(true);
            }
        }

        if (v == adventureButton) {
            if (adventureButton.isDefaultState()) {
                adventureButton.setDefaultState(false);
                adventureButton.setSelectedState(true);
            } else if (adventureButton.isSelectedState()) {
                adventureButton.setSelectedState(false);
                adventureButton.setUnSelectedState(true);
            } else if (adventureButton.isUnSelectedState()) {
                adventureButton.setUnSelectedState(false);
                adventureButton.setDefaultState(true);
            }
        }

        if (v == strategyButton) {
            if (strategyButton.isDefaultState()) {
                strategyButton.setDefaultState(false);
                strategyButton.setSelectedState(true);
            } else if (strategyButton.isSelectedState()) {
                strategyButton.setSelectedState(false);
                strategyButton.setUnSelectedState(true);
            } else if (strategyButton.isUnSelectedState()) {
                strategyButton.setUnSelectedState(false);
                strategyButton.setDefaultState(true);
            }
        }

        if (v == sportsButton) {
            if (sportsButton.isDefaultState()) {
                sportsButton.setDefaultState(false);
                sportsButton.setSelectedState(true);
            } else if (sportsButton.isSelectedState()) {
                sportsButton.setSelectedState(false);
                sportsButton.setUnSelectedState(true);
            } else if (sportsButton.isUnSelectedState()) {
                sportsButton.setUnSelectedState(false);
                sportsButton.setDefaultState(true);
            }
        }

        if (v == actionButton) {
            if (actionButton.isDefaultState()) {
                actionButton.setDefaultState(false);
                actionButton.setSelectedState(true);
            } else if (actionButton.isSelectedState()) {
                actionButton.setSelectedState(false);
                actionButton.setUnSelectedState(true);
            } else if (actionButton.isUnSelectedState()) {
                actionButton.setUnSelectedState(false);
                actionButton.setDefaultState(true);
            }
        }

        if (v == boardButton) {
            if (boardButton.isDefaultState()) {
                boardButton.setDefaultState(false);
                boardButton.setSelectedState(true);
            } else if (boardButton.isSelectedState()) {
                boardButton.setSelectedState(false);
                boardButton.setUnSelectedState(true);
            } else if (boardButton.isUnSelectedState()) {
                boardButton.setUnSelectedState(false);
                boardButton.setDefaultState(true);
            }
        }
    }
}
