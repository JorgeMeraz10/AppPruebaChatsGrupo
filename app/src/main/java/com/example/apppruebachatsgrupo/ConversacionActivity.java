package com.example.apppruebachatsgrupo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

    private static final int REQUEST_SELECT_IMAGE = 1;
    private static final int REQUEST_RECORD_AUDIO = 2;
    private static final int REQUEST_RECORD_VIDEO = 3;




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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            // Usa la imagen seleccionada aquí

            if (requestCode == REQUEST_RECORD_AUDIO && resultCode == RESULT_OK && data != null) {
                // Usa el audio grabado aquí
            }

            if (requestCode == REQUEST_RECORD_VIDEO && resultCode == RESULT_OK && data != null) {
                // Usa el video grabado aquí
            }

            if (requestCode == 1 && resultCode == RESULT_OK) {
                // Obtiene el archivo URI y el PATH
                Uri uri = data.getData();
                String path = uri.getPath();

                // Usar el archivo PATH como se necesite
                // ...
            }
        }
    }



    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_SELECT_IMAGE);
    }

    private void recordAudio() {
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivityForResult(intent, REQUEST_RECORD_AUDIO);
    }

    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, REQUEST_RECORD_VIDEO);
    }



    //Metodo para mostrar las opciones y llamar sus metodos funcionales
    public void showAttachmentOptions(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.attachment_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_image:
                        // llamar Elemento de Imagen
                        selectImageFromGallery();
                        return true;
                    case R.id.menu_document:
                        //Abrir Archivos
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*"); // Permitir seleccionar cualquier tipo de archivo
                        startActivityForResult(intent, 1);
                        return true;
                    case R.id.menu_audio:
                        // llamar Elemento de Audio
                        recordAudio();
                        return true;
                    case R.id.menu_video:
                        // llamar Elemento de Video
                        recordVideo();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show(); // muestra el menú emergente
    }
}
