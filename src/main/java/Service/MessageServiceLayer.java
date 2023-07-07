package Service;

import DAO.MessageDAO;
import Model.Message;

public class MessageServiceLayer {
    MessageDAO messageDAO;

    public MessageServiceLayer(){
        this.messageDAO = new MessageDAO();
    }

    public Message createMessage(Message message){
        if(validateMessageText(message.getMessage_text())){
            System.out.println("this is createMessage Service 6666666666666666666");
            return messageDAO.createMessage(message);
        }
        return null;
    }

    public boolean validateMessageText(String message_text){
        System.out.println("this is validateMessageText 44444444444444444444444");
        if(message_text.length() > 0 && message_text.length() < 255){
            System.out.println("this is validateMessageText 5555555555555555555555");
            return true;
        }
        return false;
    }
}
