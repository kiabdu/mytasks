package com.kiabdu.mytasks.config;

import java.util.HashMap;

public class HttpSession {
    private HashMap<Integer, Boolean> attributes = new HashMap<>();

    public void setAttribute(int id, boolean isActive){
        attributes.put(id, isActive);
    }

    public boolean getAttribute(int id){
        return attributes.get(id);
    }

    public void removeAttribute(int id){
        attributes.remove(id);
    }
}