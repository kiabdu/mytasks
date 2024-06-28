package com.kiabdu.mytasks.config;

import java.util.HashMap;

public class HttpSession {
    private HashMap<String, Object> attributes;

    public void setAttribute(String key, Object value){
        attributes.put(key, value);
    }

    public Object getAttribute(String key){
        return attributes.get(key);
    }

    public void removeAttribute(String key){
        attributes.remove(key);
    }
}