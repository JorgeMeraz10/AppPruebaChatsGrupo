package com.example.apppruebachatsgrupo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class ListaChatFragment  extends Fragment {

    private ListView mChatListView;
    private ListaChatCustomAdapter mChatListAdapter;
    private ArrayList<ChatGroup> mChatGroups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_lista, container, false);

        // Inicializa la lista de grupos de chat , con su nombre y miembros
        mChatGroups = new ArrayList<>();
        mChatGroups.add(new ChatGroup("PMI", Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("REDES IV", Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("ISI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("CNI",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));


        // Inicializa el adaptador de la lista de chats
        mChatListAdapter = new ListaChatCustomAdapter(getContext(), mChatGroups);

        // Configura el ListView
        mChatListView = rootView.findViewById(R.id.chat_list_view);
        mChatListView.setAdapter(mChatListAdapter);
        mChatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Aquí puedes manejar el evento de hacer clic en un grupo de chat
            }
        });

        return rootView;
    }
}


