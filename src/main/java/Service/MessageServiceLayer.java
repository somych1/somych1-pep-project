package Service;

import DAO.AccountDao;
import DAO.MessageDAO;
import Model.Message;

public class MessageServiceLayer {
    MessageDAO messageDAO;
    AccountDao accountDao;

    public MessageServiceLayer(){
        this.messageDAO = new MessageDAO();
    }

    public Message createMessage(Message message){
        System.out.println(accountDao.checkAccountIdExistenceById(message.getPosted_by()) + "---------");
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
