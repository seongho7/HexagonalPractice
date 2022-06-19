package com.sh.hexagonal.refund.application.port.in;

import com.sh.hexagonal.refund.domain.UserRefund;

public interface CalculateRefundUseCase {
    UserRefund calculate(long accountId) throws UserIncomeNotFoundException;

}
