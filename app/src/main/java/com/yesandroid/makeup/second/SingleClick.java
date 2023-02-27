package com.yesandroid.makeup.second;


import com.yesandroid.makeup.api.MakeItem;

public class SingleClick {

    private static SingleClick single_instance = null;

    public static MakeItem makeItem = null;

    public static SingleClick getInstance()
    {
        if(single_instance==null)
        {
            single_instance=new SingleClick();

        }
        return single_instance;

    }

    public void setMakeItem(MakeItem makeItem)
    {
        this.makeItem=makeItem;
    }





    public static MakeItem getMakeItem()

    {
        return  makeItem;
    }




}
