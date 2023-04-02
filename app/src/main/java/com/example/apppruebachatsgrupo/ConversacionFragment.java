package com.example.apppruebachatsgrupo;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversacionFragment extends Fragment {
    private ListView mChatListView;
    private ListaChatCustomAdapter mChatListAdapter;
    private ArrayList<ChatGroup> mChatGroups;
    private static final String ARG_GROUP_INDEX = "group_index";

    public ConversacionFragment() {

        //Requiere un constructor vacio publico

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_conversation, container, false);
        mChatListView = view.findViewById(R.id.message_listview);
        View mSendMessageButton = view.findViewById(R.id.send_button);
        View mMessageEditText = view.findViewById(R.id.new_message_edittext);
        mChatListAdapter = new ListaChatCustomAdapter(getContext(), new ArrayList<>());
        mChatListView.setAdapter(mChatListAdapter);

       // int selectedGroupIndex = getArguments().getInt(ARG_GROUP_INDEX);
       // ChatGroup selectedGroup = mChatGroups.get(selectedGroupIndex);


        Bundle bundle = getArguments();
        if (bundle != null) {
            ChatGroup group = bundle.getParcelable("group");
            if (group != null) {
                List<Message> messages = new ArrayList<>();
                for (Message message : group.getMessages()) {
                    messages.add(message);
                }
                mChatListAdapter.setMessages(messages);
            }
        }

        mSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String messageText = mMessageEditText.getText().toString();
                String messageText = mMessageEditText.toString();
                if (!messageText.isEmpty()) {
                    // Agregar el nuevo mensaje a la lista de mensajes del grupo seleccionado
                    // Aquí deberás agregar el nuevo mensaje al grupo seleccionado y actualizar la lista de mensajes del adaptador
                    //  mMessageEditText.getText().clear();
                    mMessageEditText.clearFocus();
                }
            }
        });

        return view;
    }


    @Override
    //Aqui se actualizan los datos , notificando al adaptador los cambios hechos
    public void onResume() {
        super.onResume();
        mChatListAdapter.notifyDataSetChanged();
    }


}
