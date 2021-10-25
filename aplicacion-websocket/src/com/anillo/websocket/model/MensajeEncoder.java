/**
 * Esta clase se encarga de empaquetar por decirlo de alguna manera
 * los mensajes que el usuario desea enviar convirtiendo de una instancia
 * de la clase "Mensaje" a un Json.
 */
package com.anillo.websocket.model;

import com.anillo.websocket.util.JsonUtil;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Comunidad del anillo.
 */
public class MensajeEncoder implements Encoder.Text<Mensaje>{

    @Override
    public String encode(Mensaje mensaje) throws EncodeException {
        return JsonUtil.formatMessage(mensaje.getContent(), mensaje.getSender());
    }

    @Override
    public void init(final EndpointConfig config) {        
    }

    @Override
    public void destroy() {
    }
    
}
