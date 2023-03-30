package com.example.apppruebachatsgrupo;

import java.util.List;

public class ChatGroup {
    private String mName;
    private List<String> mParticipants;

    public ChatGroup(String name, List<String> participants) {
        mName = name;
        mParticipants = participants;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<String> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(List<String> participants) {
        mParticipants = participants;
    }
}
