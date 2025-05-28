package connectors;

import models.Account;
import models.ListAccount;

public class AccountConnector {
    public Account login(String usr, String pwd)
    {
        ListAccount le = new ListAccount();
        le.generate_sample_account_dataset();
        for (Account acc : le.getAccounts())
        {
            String accUsername = acc.getUsername();
            String accPassword = acc.getPassword();

            if (accUsername != null && accPassword != null &&
                    accUsername.equalsIgnoreCase(usr) &&
                    accPassword.equalsIgnoreCase(pwd))
            {
                return acc;
            }
        }
        return null;
    }
}
