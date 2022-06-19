package com.sh.hexagonal.refund.adapter.out.persistence;

import com.sh.hexagonal.refund.domain.UserIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIncomeJpaRepository extends JpaRepository<UserIncome, Long> {
}
