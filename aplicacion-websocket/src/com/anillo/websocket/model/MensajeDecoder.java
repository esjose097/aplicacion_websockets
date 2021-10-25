/**
 * Esta clase se encarga de desempaquetar por decirlo de alguna manera
 * los mensajes que el usuario recibe es decir pasarlos de un Json a
 * un objeto de la clase "mensaje".
 */
package com.anillo.websocket.model;

import java.io.StringReader;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Comunidad del anillo
 */
public class MensajeDecoder implements Decoder.Text<Mensaje>{
    
    @Override
    public Mensaje decode(String txtMensaje) throws DecodeException {
        Mensaje mensaje = new Mensaje();
        JsonObject jsonObject = Json.createReader(new StringReader(txtMensaje)).readObject();        
        mensaje.setContent(jsonObject.getString("message"));
        mensaje.setSender(jsonObject.getString("sender"));
        mensaje.setReceived(new Date());
        return mensaje;
        
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }

    @Override
    public void init(final EndpointConfig config) {
        
    }

    @Override
    public void destroy() {       
    }
    
}
