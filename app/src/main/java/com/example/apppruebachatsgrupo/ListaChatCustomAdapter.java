package com.example.apppruebachatsgrupo;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListaChatCustomAdapter  extends ArrayAdapter<ChatGroup> {

    private Context mContext;
    private ArrayList<ChatGroup> mChatGroups;
    private ListaChatCustomAdapter mChatListAdapter;

    public ListaChatCustomAdapter (Context context, ArrayList<ChatGroup> chatGroups) {
        super(context, 0, chatGroups);

        mContext = context;
        mChatGroups = chatGroups;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chat_grupo, parent, false);
        }

       

        ChatGroup chatGroup = mChatGroups.get(position);

        TextView groupNameTextView = convertView.findViewById(R.id.group_name_text_view);
        groupNameTextView.setText(chatGroup.getName());

        return convertView;
    }

    public void setMessages(List<Message> messages) {
    }
}

