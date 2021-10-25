package com.anillo.websocket.client;

import static com.anillo.websocket.util.JsonUtil.formatMessage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import javax.websocket.DeploymentException;

import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

/**
 *
 * @author Comunidad del anillo
 */
public class Cliente {
    public static final String SERVER = "ws://localhost:8025/ws/chat";
    
    public static void main(String[] args) throws URISyntaxException, DeploymentException, IOException {
        
        ClientManager client = ClientManager.createClient();
        String mensaje;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al chat!!!");
        System.out.println("Cual es tu nombre?");
        String user = scanner.nextLine();
        Session session = client.connectToServer(ClienteEndpoint.class, new URI(SERVER));
        System.out.println("Usted ha sido logeado como: " + user);
        
        do 
        {
            System.out.println("Escriba su mensaje:");
            mensaje = scanner.nextLine();
            session.getBasicRemote().sendText(formatMessage(mensaje, user));
        } 
        while (!mensaje.equalsIgnoreCase("quit"));        
    }    
}
