package Service;

import DAO.AccountDao;

public class AccountServiceLayer {
    AccountDao accountDao;

    public AccountServiceLayer(AccountDao dao){
        this.accountDao = dao;
    }


}
