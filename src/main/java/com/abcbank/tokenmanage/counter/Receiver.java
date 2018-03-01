package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.dto.TokenDTO;


public interface Receiver {

    public void receiveToken(TokenDTO tokenDTO) throws Exception;
    
}