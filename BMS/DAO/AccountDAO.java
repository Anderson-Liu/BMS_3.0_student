package DAO;

import java.util.HashMap;

public interface AccountDAO {
    void saveAccounts(HashMap var1);

    HashMap loadAccounts();
}
