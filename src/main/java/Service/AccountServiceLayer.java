package Service;

import DAO.AccountDao;
import Model.Account;

public class AccountServiceLayer {
    AccountDao accountDao;

    public AccountServiceLayer(){
        this.accountDao = new AccountDao();
    }

    public AccountServiceLayer(AccountDao dao){
        this.accountDao = dao;
    }

    public Account createAccount(Account account){
        //Check for whitespaces
        if(validateUsername(account.getUsername()) && validatePassword(account.getPassword())){
            //Check if account already exist
            Account existingAccount = accountDao.getAccountByUsername(account.getUsername());
            if (existingAccount != null){
                return null;
            }
            return accountDao.createAccount(account);
        }
        return null;
    }

    public boolean validateUsername(String username){
        if(username != null && username.length() > 0){
            for(int i = 0; i < username.length(); i++){
                if(Character.isWhitespace(username.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean validatePassword(String password){
        if(password.length() >= 4){
            for(int i = 0; i < password.length(); i++){
                if(Character.isWhitespace(password.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
