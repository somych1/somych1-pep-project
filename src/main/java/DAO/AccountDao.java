package DAO;

import Model.Account;
import java.util.List;

public interface AccountDao {
    Account createAccount(Account account);
    Account getAccountByUsername(String username);
//    List<Account> getAllAccounts();
//    void updateAccount(Account account);
//    void deleteAccount(int account_id);
}
