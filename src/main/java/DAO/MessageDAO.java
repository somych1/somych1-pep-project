package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MessageDAO {

    public Message createMessage(Message message){
        System.out.println("this is MessageDAO 0000000");
        Connection connection = ConnectionUtil.getConnection();
        try{
//            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("this is MessageDAO 1111");
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            ResultSet pkeyRS = preparedStatement.getGeneratedKeys();
            System.out.println("this is MessageDAO 2222222");
            if(pkeyRS.next()){
                System.out.println("this is MessageDAO 33333");
                int generatedId = (int) pkeyRS.getInt(1);
                return new Message(generatedId, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
