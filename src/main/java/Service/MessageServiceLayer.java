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
            return messageDAO.createMessage(message);
        }
        return null;
    }

    public boolean validateMessageText(String message_text){
        if(message_text.length() > 0 && message_text.length() < 255){
            return true;
        }
        return false;
    }
}
