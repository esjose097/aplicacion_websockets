package com.anillo.websocket.server;

import java.util.Scanner;
import javax.websocket.DeploymentException;

/**
 *
 * @author Comunidad del anillo.
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 8025, "/ws", ServerEndpoint.class);
        
        try{
            server.start();
            System.out.println("Presione cualquier tecla para detener el servidor...");
            new Scanner(System.in).nextLine();            
        }
        catch(DeploymentException e)
        {
            throw new RuntimeException(e);
        }
        finally{
            server.stop();
        }
    }
    
}
