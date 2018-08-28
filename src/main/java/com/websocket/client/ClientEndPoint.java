package com.websocket.client;

import com.websocket.model.Message;
import com.websocket.model.MessageDecoder;
import com.websocket.model.MessageEncoder;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.tyrus.client.ClientManager;

import javax.json.Json;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.text.SimpleDateFormat;

@Slf4j
@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ClientEndPoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @OnOpen
    public void onOpen(Session session) {
        log.info("Connected ... " + session.getId());
    }

    @OnMessage
    public void onMessage(Message message) {
        System.out.println(String.format("%s: %s", message.getSender(), message.getContent()));
    }

    @OnClose
    public void onClose(Session session) {
        log.info("Session {} is closed", session.getId());
    }
}
