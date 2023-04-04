package com.example.apppruebachatsgrupo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;

public class ConversacionActivity extends AppCompatActivity {

    private ListView mChatListView;
    private ArrayList<String> mChatMessages;
    private ArrayAdapter<String> mChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        // Obtiene el nombre del grupo de chat seleccionado de la actividad anterior
        Intent intent = getIntent();
        String chatGroupName = intent.getStringExtra("chatGroupName");

        // Establece el título de la actividad como el nombre del grupo de chat seleccionado
        getSupportActionBar().setTitle(chatGroupName);

        //--------------- Inicializa la lista de mensajes del chat-----------
        mChatMessages = new ArrayList<>();
        mChatMessages.add("Jorge:  Hola, ¿cómo están todos?");
        mChatMessages.add("Luis:    Bien, gracias ");
        mChatMessages.add("Elsy:    bien, gracias.");
        mChatMessages.add("Estefany:    bien, gracias.");
        mChatMessages.add("Armando:    bien, gracias.");
        mChatMessages.add("Fabiana:    bien, gracias.");
        mChatMessages.add("Luis:       Hay que tenerminar avanzar en el" +
                "                      Proyecto de Programacion Movil I.");
        mChatMessages.add("Estefany:   Si esta muy largo, hay que investigar mucho.");

        // Inicializa el adaptador de la lista de mensajes del chat
        mChatListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mChatMessages);

        //------ Configura el ListView----------------
        mChatListView = findViewById(R.id.message_listview);
        mChatListView.setAdapter(mChatListAdapter);

        // ----Agrega un mensaje cuando se presiona el botón de enviar-----
        final EditText messageEditText = findViewById(R.id.new_message_edittext);
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    mChatMessages.add(message);
                    mChatListAdapter.notifyDataSetChanged();
                    messageEditText.setText("");
                }
            }
        });

        // Vincula el método showAttachmentOptions con el botón de adjuntar
        findViewById(R.id.attach_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAttachmentOptions(v);
            }
        });
    }

    public void showAttachmentOptions(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.attachment_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_image:
                        // Handle image option
                        return true;
                    case R.id.menu_document:
                        // Handle document option
                        return true;
                    case R.id.menu_audio:
                        // Handle audio option
                        return true;
                    case R.id.menu_video:
                        // Handle video option
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show(); // muestra el menú emergente
    }
}
