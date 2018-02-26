package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.model.Token;

public interface Receiver {

    public void receiveMessage(Token token) throws Exception;
    
}