package com.example.apppruebachatsgrupo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class ConversacionActivity extends AppCompatActivity {

    private ListView mChatListView;
    private ArrayList<String> mChatMessages;
    private ArrayAdapter<String> mChatListAdapter;

    private static final int REQUEST_SELECT_IMAGE = 1;
    private static final int REQUEST_RECORD_AUDIO = 2;
    private static final int REQUEST_RECORD_VIDEO = 3;
    private static final int REQUEST_SELECT_FILE = 4;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 5;
    private static final int REQUEST_PERMISSION_RECORD_AUDIO = 6;
    private static final int REQUEST_PERMISSION_CAMERA = 7;




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

            if (requestCode == REQUEST_SELECT_FILE && resultCode == RESULT_OK) {
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
                        // Verificar si se ha concedido permiso para acceder a la galería
                        if (ActivityCompat.checkSelfPermission(ConversacionActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Si el permiso no se ha concedido, solicitar al usuario que lo conceda
                            ActivityCompat.requestPermissions(ConversacionActivity.this,
                                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                    REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
                        } else {
                            // Si el permiso se ha concedido, iniciar la actividad para seleccionar una imagen de la galería
                            selectImageFromGallery();
                        }
                        return true;
                    case R.id.menu_document:
                        // Verificar si se ha concedido permiso para acceder a los documentos
                        if (ActivityCompat.checkSelfPermission(ConversacionActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Si el permiso no se ha concedido, solicitar al usuario que lo conceda
                            ActivityCompat.requestPermissions(ConversacionActivity.this,
                                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                    REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
                        } else {
                            // Si el permiso se ha concedido, iniciar la actividad para seleccionar un archivo de los documentos
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("*/*"); // Permitir seleccionar cualquier tipo de archivo
                            startActivityForResult(intent, 1);
                        }
                        return true;
                    case R.id.menu_audio:
                        // Verificar si se ha concedido permiso para grabar audio
                        if (ActivityCompat.checkSelfPermission(ConversacionActivity.this, android.Manifest.permission.RECORD_AUDIO)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Si el permiso no se ha concedido, solicitar al usuario que lo conceda
                            ActivityCompat.requestPermissions(ConversacionActivity.this,
                                    new String[]{android.Manifest.permission.RECORD_AUDIO},
                                    REQUEST_PERMISSION_RECORD_AUDIO);
                        } else {
                            // Si el permiso se ha concedido, iniciar la actividad para grabar audio
                            recordAudio();
                        }
                        return true;
                    case R.id.menu_video:
                        // Verificar si se ha concedido permiso para acceder a la cámara
                        if (ActivityCompat.checkSelfPermission(ConversacionActivity.this, android.Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Si el permiso no se ha concedido, solicitar al usuario que lo conceda
                            ActivityCompat.requestPermissions(ConversacionActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_PERMISSION_CAMERA);
                        } else {
                            // Si el permiso se ha concedido, iniciar la actividad para grabar un video
                            recordVideo();
                        }
                        return true;

                    default:
                        return false;
                }
            }
        });
        popup.show(); // muestra el menú emergente
    }
}
