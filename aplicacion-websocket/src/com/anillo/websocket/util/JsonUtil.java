/**
 * Esta clase basicamente recibe 2 strings y los convierte en Json.
 * esta clase se utiliza como "util" para no tener que repetir
 * demasiado código.
 */
package com.anillo.websocket.util;
import javax.json.Json;

/**
 *
 * @author Comunidad del anillo
 */
public class JsonUtil {
    
    public static String formatMessage(String message, String user){
        return Json.createObjectBuilder()
                .add("message", message)
                .add("sender", user)
                .add("received", "")
                .build().toString();
    }
}
