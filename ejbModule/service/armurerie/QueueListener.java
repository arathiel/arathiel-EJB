package service.armurerie;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.DeliveryActive;

/**
 * Message-Driven Bean implementation class for: QueueListener
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destination",     propertyValue = "jms/queue/TestDdevilQueue"),    
				@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
		})
@DeliveryActive(true)
public class QueueListener implements MessageListener {

    /**
     * Default constructor. 
     */
    public QueueListener() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
       try {
    	   if (message instanceof TextMessage) {
    		   TextMessage msg = (TextMessage) message;
    		   System.out.println("Le message est : " + msg.getText()); 
    	   }
    	   else {
    		   System.out.println("Votre insertion n'a pas abouti");
    	   }
       }
       catch (JMSException e) { 
           e.printStackTrace(); 
       } 
        
    }

}
