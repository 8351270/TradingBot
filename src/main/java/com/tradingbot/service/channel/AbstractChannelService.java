package com.tradingbot.service.channel;

import java.util.ArrayList;
import java.util.List;

public class AbstractChannelService<T> {


    List<T> elements;

    public AbstractChannelService(){
        this.elements = new ArrayList<>();
    }

    public void addElement(T response) {
        if (elements.size()>99){
            elements.remove(99);
        }
        elements.add(response);
    }

}
