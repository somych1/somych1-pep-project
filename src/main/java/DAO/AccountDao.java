package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDao {

    public Account loginAccount(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = " SELECT * FROM account WHERE username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = " INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyRS = preparedStatement.getGeneratedKeys();
            if (pkeyRS.next()){
                int generatedAccountId = (int) pkeyRS.getInt(1);
                return new Account(generatedAccountId, account.getUsername(), account.getPassword());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM account WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
//
//    @Override
//    public List<Account> getAllAccounts() {
//        return null;
//    }
//
//    @Override
//    public void updateAccount(Account account) {
//
//    }
//
//    @Override
//    public void deleteAccount(int account_id) {
//
//    }
}
