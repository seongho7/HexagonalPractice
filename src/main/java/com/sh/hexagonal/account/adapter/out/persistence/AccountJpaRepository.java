package com.sh.hexagonal.account.adapter.out.persistence;

import com.sh.hexagonal.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(String userId);
}
