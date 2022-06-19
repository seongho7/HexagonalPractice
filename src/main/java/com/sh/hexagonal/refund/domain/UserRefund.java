package com.sh.hexagonal.refund.domain;

import com.sh.hexagonal.common.Money;
import lombok.Value;

@Value
public class UserRefund {
    Money taxCreditLimit;   //세액공제 한도
    Money taxDeduction;     //세액 공제
    Money refund;           //환급액
}
