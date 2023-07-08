package Controller;

import Model.Account;
import Model.Message;
import Service.AccountServiceLayer;
import Service.MessageServiceLayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountServiceLayer accountServiceLayer;
    MessageServiceLayer messageServiceLayer;
    public SocialMediaController() {
        accountServiceLayer = new AccountServiceLayer();
        messageServiceLayer = new MessageServiceLayer();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::createAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.post("/messages", this::postMessage);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.delete("/messages/{message_id}", this::deleteMessageById);

        return app;
    }

    private void deleteMessageById(Context context){
        int messageId = Integer.parseInt(context.pathParam("{message_id}"));
        Message message = messageServiceLayer.deleteMessageById(messageId);
        if (message != null){
            context.json(message);
        }
    }

    private void getMessageById(Context context){
        int messageId = Integer.parseInt(context.pathParam("{message_id}"));
        Message message = messageServiceLayer.getMessageById(messageId);
        if (message!= null){
            context.json(message);
        }
    }

    private void getAllMessages(Context context) {
        List<Message> messageList = messageServiceLayer.getAllMessages();
        context.json(messageList);
    }

    private void postMessage(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message postedMessage = messageServiceLayer.createMessage(message);
        if(postedMessage == null){
            context.status(400);
        } else {
            context.json(postedMessage);
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void createAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account createdAccount = accountServiceLayer.createAccount(account);
        if(createdAccount == null){
            context.status(400);
        } else{
            context.json(createdAccount);
        }
    }

    private void loginAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account foundAccount = accountServiceLayer.loginAccount(account);
        if (foundAccount == null){
            context.status(401);
        } else {
            context.json(foundAccount);
        }
    }


}