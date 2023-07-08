package Service;

import DAO.AccountDao;
import DAO.MessageDAO;
import Model.Message;

import java.util.List;
import java.util.Map;

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

    public boolean validateMessageText(String messageText){
        if(messageText.length() > 0 && messageText.length() < 255){
            return true;
        }
        return false;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId){
        return messageDAO.getMessageById(messageId);
    }

    public Message deleteMessageById(int messageId){
        Message message = messageDAO.getMessageById(messageId);
        if (message != null){
            messageDAO.deleteMessageById(messageId);
        }
        return message;
    }

    public Message updateMessageTextById(int messageId, Message message){
        if(validateMessageText(message.getMessage_text())){
            Message existingMessage = messageDAO.getMessageById(messageId);
            if(existingMessage != null){
                return messageDAO.updateMessageTextById(existingMessage, message.getMessage_text());
            }
        }
        return null;
    }

    public List<Message> getAllMessagesByPostedById(int postedById){
        return messageDAO.getAllMessagesByPostedById(postedById);
    }
}
