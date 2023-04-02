package com.example.apppruebachatsgrupo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carga el fragmento que contiene la lista de chats
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaChatFragment listChatFragment = new ListaChatFragment();
        fragmentTransaction.add(R.id.fragment_container, listChatFragment);
        fragmentTransaction.commit();

        //Cargar el fragmento que contiene la lista de chats y reemplazar el fragmento  de pila por otro.
      //  Fragment fragment = new ConversacionFragment();
       // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      //  transaction.replace(R.id.fragment_container, fragment);
      //  transaction.addToBackStack(null);
      //  transaction.commit();



    }
}
