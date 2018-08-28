package com.websocket.model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Date;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(final String textMessage) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(textMessage)).readObject();

        Message message = new Message();
        message.setContent(jsonObject.getString("message"));
        message.setSender(jsonObject.getString("sender"));
        message.setReceived(new Date());
        return message;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }
}
