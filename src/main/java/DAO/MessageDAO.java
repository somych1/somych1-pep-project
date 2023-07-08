package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class MessageDAO {

    public Message createMessage(Message message){
        System.out.println("this is MessageDAO 0000000");
        Connection connection = ConnectionUtil.getConnection();
        try{


//            String checkIfExists = "SELECT 1 FROM account WHERE account_id=?";
//            String insert = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
//            PreparedStatement checkIfExistsStatement = connection.prepareStatement(checkIfExists);
//            PreparedStatement insertStatement = connection.prepareStatement(insert, RETURN_GENERATED_KEYS);
//
//            checkIfExistsStatement.setInt(1, message.getPosted_by());
//            ResultSet rs = checkIfExistsStatement.executeQuery();
//            if(rs.next()){
//                insertStatement.setInt(1,message.getPosted_by());
//                insertStatement.setString(2,message.getMessage_text());
//                insertStatement.setLong(3,message.getTime_posted_epoch());
//                int rowsAffected = insertStatement.executeUpdate();
//                int generatedId = (int) rs.getInt(2);
//                return new Message(generatedId, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
//            }

            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("this is MessageDAO 1111");
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
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
