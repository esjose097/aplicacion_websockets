/**
 * Esta clase viene siendo algo así como la parte "oyente" del usuario
 * detecta cuando los mensajes entran y les da un formato para imprimirlos
 * en la consola.
 */
package com.anillo.websocket.client;
import com.anillo.websocket.model.*;
import static java.lang.String.format;
import java.text.SimpleDateFormat;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Comunidad del anillo
 */
@javax.websocket.ClientEndpoint(encoders = MensajeEncoder.class, decoders = MensajeDecoder.class)
public class ClienteEndpoint {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(format("Conexión establecida. session id: %s", session.getId()));
    }

    @OnMessage
    public void onMessage(Mensaje mensaje) {
        System.out.println(format("[%s:%s] %s", simpleDateFormat.format(mensaje.getReceived()),mensaje.getSender(),mensaje.getContent()));
    }
}
