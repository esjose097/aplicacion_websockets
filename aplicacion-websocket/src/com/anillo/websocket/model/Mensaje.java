/**
 * Esta clase representa los mensajes que los clientes envian.
 */
package com.anillo.websocket.model;

import java.util.Date;

/**
 *
 * @author Comunidad del anillo
 */
public class Mensaje {

    private String content;
    private String sender;
    private Date received;
    
    public Mensaje(){        
    }

    public Mensaje(String content, String sender, Date received) {
        this.content = content;
        this.sender = sender;
        this.received = received;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(final Date received) {
        this.received = received;
    }
    
    
    
}
