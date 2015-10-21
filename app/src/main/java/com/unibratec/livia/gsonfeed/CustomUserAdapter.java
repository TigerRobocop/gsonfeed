package com.unibratec.livia.gsonfeed;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Livia on 20/10/2015.
 */
public class CustomUserAdapter extends ArrayAdapter<User>{

    public CustomUserAdapter(Context context,  List<User> objects) {
        super(context, 0, objects);
    }

   //implement methods

    //no getView implementation
    //usar o picasso

    //Picasso.with(context).load("file:///android_asset/DvpvklR.png").into(imageView2);
    //onde context é o getContext do metodo, file é o
    // atributo file do objeto e image view é a imagem do view holder que contém a img view d

}


