package com.sh.hexagonal.account.application.port.out;

import com.sh.hexagonal.account.domain.Account;

public interface SaveAccountPort {
    Account save(Account account);
}
