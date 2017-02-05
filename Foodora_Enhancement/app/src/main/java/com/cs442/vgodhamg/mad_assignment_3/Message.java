package com.cs442.vgodhamg.mad_assignment_3;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vedant on 24-02-2016.
 */
public class Message {

    public static void message(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
