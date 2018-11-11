package com.example.fly_s_y.request;

public class JSONAccessor {
    private JSONAccessorType type;
    private String accessor;

    public JSONAccessor(JSONAccessorType type, String accessor){
        this.accessor = accessor;
        this.type = type;
    }

    public String getAccessor(){
        return accessor;
    }

    public JSONAccessorType getType(){
        return type;
    }
}
