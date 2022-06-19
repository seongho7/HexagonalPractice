package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.domain.AccessToken;

public interface TokenProvider {
    AccessToken createToken(Account account);
    //long getAccountIdFromToken(String token);
}
