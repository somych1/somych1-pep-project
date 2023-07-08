package Service;

import DAO.AccountDao;
import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageServiceLayer {
    MessageDAO messageDAO;
    AccountDao accountDao;

    public MessageServiceLayer(){
        this.messageDAO = new MessageDAO();
        this.accountDao = new AccountDao();
    }

    public Message createMessage(Message message){
        if(validateMessageText(message.getMessage_text()) && accountDao.checkAccountIdExistenceById(message.getPosted_by())){
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

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }
}
