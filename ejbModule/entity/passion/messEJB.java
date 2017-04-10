//package entity.passion;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//import javax.jms.TextMessage;
//import org.jboss.ejb3.annotation.DeliveryActive;
//
//
//@MessageDriven(name = "AjoutPassion", 
//	activationConfig = {    
//			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),    
//			@ActivationConfigProperty(propertyName = "destination",     propertyValue = "jms/queue/TestDMQueue"),    
//			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })   
//@DeliveryActive(true)
//public class messEJB implements MessageListener {
// 
//    public messEJB() { 
//    } 
//  
//    public void onMessage(Message message) { 
//        try { 
//        	// affichage du TextMessage reçu
//            if (message instanceof TextMessage) { 
//                TextMessage msg = (TextMessage) message; 
//                System.out.println("Message : " + msg.getText()); 
//                
//            } 
//            // affichage de l'objet reçu dans le message
//            else if (message instanceof ObjectMessage) { 
//                System.out.println("Réception d'un ObjectMessage "); 
//                
//                ObjectMessage msg = (ObjectMessage) message; 
//                Passion passion = (Passion) msg.getObject(); 
//                
//                System.out.println("Passion ajoutée : "); 
//                System.out.println(passion.getNom());
//                System.out.println(passion.getDescription());
//                System.out.println(passion.getMagies()); 
//                
//            } else { 
//                System.out.println("Le message n'est pas valide"); 
//            } 
//  
//        } catch (JMSException e) { 
//            e.printStackTrace(); 
//        } 
//    } 
//}