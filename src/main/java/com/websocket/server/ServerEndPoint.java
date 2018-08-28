package com.websocket.server;

import com.websocket.model.Message;
import com.websocket.model.MessageDecoder;
import com.websocket.model.MessageEncoder;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint(value = "/chat", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ServerEndPoint {

    private static Set<Session> peers = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("{} joined the chat room.", session.getId());
        peers.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        String user = (String) session.getUserProperties().get("user");
        if (user == null) {
            session.getUserProperties().put("user", message.getSender());
        }
        switch (message.getContent()) {
            case "quit":
                session.close();
                break;
            default:
                System.out.println(String.format("%s: %s", message.getSender(), message.getContent()));
                //broadcast the message
                for (Session peer : peers) {
                    peer.getBasicRemote().sendObject(message);
                }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        log.info("{} left the chat room.", session.getId());
        peers.remove(session);
        //notify peers about leaving the chat room
        for (Session peer : peers) {
            Message message = new Message();
            message.setSender("Server");
            message.setContent(String.format("%s left the chat room", (String) session.getUserProperties().get("user")));
            message.setReceived(new Date());
            peer.getBasicRemote().sendObject(message);
        }
    }

    @OnError
    public void onError(Throwable error) {
        log.error("Something was wrong!", error);
    }
}
