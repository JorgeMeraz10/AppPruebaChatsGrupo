package com.example.apppruebachatsgrupo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        mChatGroups.add(new ChatGroup("Pogramacion Movil I", Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("REDES IV", Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Ingenieria de Sotware I",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Computacion en la Nube",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Programacion Movil II",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Arquitectura de Computadoras",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Programacion Web I",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Sistemas Operativos I",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Redes III",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Ingenieria de Sotware II",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Computacion Grafica y Visual",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Mantenimiento de Hardaware y Sotware",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));
        mChatGroups.add(new ChatGroup("Programacion Web II",Arrays.asList("Jorge", "Estefany", "Elsy", "Fabiana")));


        // Inicializa el adaptador de la lista de chats
        mChatListAdapter = new ListaChatCustomAdapter(getContext(), mChatGroups);


        // Configura el ListView
        mChatListView = rootView.findViewById(R.id.chat_list_view);
        mChatListView.setAdapter(mChatListAdapter);



        mChatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Aquí puedes manejar el evento de hacer clic en un grupo de chat
                ChatGroup chatGroup = mChatGroups.get(position);

                // Abre el fragmento de chat para el grupo de chat seleccionado
                openChatFragment(chatGroup);

                // crea un Intent para iniciar la actividad de conversación del grupo de chat
                Intent intent = new Intent(getActivity(), ConversacionActivity.class);
                intent.putExtra("chatGroupName", chatGroup.getName());
                startActivity(intent);

            }
        });

        return rootView;
    }

    public void openChatFragment(ChatGroup ChatGroup) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Crea una nueva instancia del fragmento de chat y pasa el grupo de chat seleccionado como argumento
        ConversacionFragment conversacionFragment = new ConversacionFragment();
        Bundle args = new Bundle();
        args.putSerializable("chatGroup", ChatGroup);
        conversacionFragment.setArguments(args);

        fragmentTransaction.replace(R.id.fragment_container, conversacionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}


