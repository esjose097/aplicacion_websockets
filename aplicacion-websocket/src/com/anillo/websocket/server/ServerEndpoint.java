/**
 * Esta clase basicamente representa al servidor el cual recibe y re-envia
 * mensajes enviados por "clientes" mediante "Jsons" para crear instancias 
 * del objeto "Mensaje".
 */
package com.anillo.websocket.server;
import com.anillo.websocket.model.*;
import java.io.IOException;
import static java.lang.String.format;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;

import javax.websocket.Session;

/**
 *
 * @author Comunidad del anillo.
 */
@javax.websocket.server.ServerEndpoint(value = "/chat", encoders = MensajeEncoder.class, decoders = MensajeDecoder.class)
public class ServerEndpoint {
    
    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(format("%s Se ha unido al chat!", session.getId()));
        peers.add(session);
    }
    
    @OnMessage
    public void onMessage(Mensaje mensaje, Session session) throws IOException, EncodeException {
        String user = (String) session.getUserProperties().get("user");
        if (user == null) 
        {
            session.getUserProperties().put("user", mensaje.getSender());
        }
        if ("quit".equalsIgnoreCase(mensaje.getContent())) 
        {
            session.close();
        }

        System.out.println(format("[%s:%s] %s",session.getId(),mensaje.getReceived(),mensaje.getContent()));

        //Transmisión del mensaje
        for (Session peer : peers) 
        {
            if (!session.getId().equals(peer.getId()))
            { //Hay que evitar re-enviar el mensaje a su propio remitente.
                peer.getBasicRemote().sendObject(mensaje);
            }
        }
    }           
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s Ha abandonado el chat.", session.getId()));
        peers.remove(session);
        //Notifica a los conectados sobre los que abandonan el chat
        for (Session peer : peers) 
        {
            Mensaje mensajeChat = new Mensaje();
            mensajeChat.setSender("Server");
            mensajeChat.setContent(format("%s Ha abandonado el chat!.", (String) session.getUserProperties().get("user")));
            mensajeChat.setReceived(new Date());
            peer.getBasicRemote().sendObject(mensajeChat);
        }
    }
    
}
