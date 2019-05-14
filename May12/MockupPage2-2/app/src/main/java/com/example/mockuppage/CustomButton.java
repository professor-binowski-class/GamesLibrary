package com.example.mockuppage;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class CustomButton extends android.support.v7.widget.AppCompatButton {

    private boolean defaultState;
    private boolean selectedState;
    private boolean unSelectedState;
    private String currentState;

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        loadAttributes(context, attrs);
    }

    private void loadAttributes(Context context, AttributeSet attributeSet) {

        @SuppressLint({"Recycle", "CustomViewStyleable"}) TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomStates, 0, 0);

        defaultState = typedArray.getBoolean(R.styleable.CustomStates_defaultState, true);
        selectedState = typedArray.getBoolean(R.styleable.CustomStates_selectedState, false);
        unSelectedState = typedArray.getBoolean(R.styleable.CustomStates_unselectedState, false);

        setBackgroundColor(getResources().getColor(R.color.defaultColor));
        currentState = "DEFAULT";

    }

    public boolean isDefaultState() {
        return defaultState;
    }

    public void setDefaultState(boolean bool) {
        this.defaultState = bool;
        if (bool) {
            setBackgroundColor(getResources().getColor(R.color.defaultColor));
            currentState = "DEFAULT";
        }
    }

    public boolean isSelectedState() {
        return selectedState;
    }

    public void setSelectedState(boolean selectedState) {
        this.selectedState = selectedState;
        if (selectedState) {
            setBackgroundColor(getResources().getColor(R.color.selectedColor));
            currentState = "SELECTED";
        }
    }


    public boolean isUnSelectedState() {
        return unSelectedState;
    }


    public void setUnSelectedState(boolean unSelectedState) {
        this.unSelectedState = unSelectedState;
        if (unSelectedState) {
            setBackgroundColor(getResources().getColor(R.color.unselectedColor));
            currentState = "UNSELECTED";
        }
    }
    public String getCurrentState() {
        return currentState;
    }
}