package com.sh.hexagonal.refund.adapter.out.sh;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sh.hexagonal.common.Money;
import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@AllArgsConstructor
public class   UserScrapDto {
    private String status;

    private Data data;

    private static final String successCode = "success";

    UserIncomeScrapInfo toUserIncomeScrapInfo() {
        if(!successCode.equals(status)) {
            throw new ShException(String.format("코드테스트 서버 status:%s", status));
        }
        Money calculatedTaxAmount = stringToMoney(data.dataList.taxList.get(0).getCalculatedTaxAmount());
        Money grossSalary = stringToMoney(data.dataList.salaryList.get(0).getGrossSalary());
        return new UserIncomeScrapInfo(calculatedTaxAmount, grossSalary);
    }

    private Money stringToMoney(String moneyStr) {
        if(ObjectUtils.isEmpty(moneyStr)) {
            return Money.ZERO;
        }

        return Money.of(
                Long.parseLong(moneyStr.replaceAll(",", ""))
        );

    }

    @Getter
    @AllArgsConstructor
    static class Data {
        private DataList dataList;
    }

    @Getter
    @AllArgsConstructor
    static class DataList {
        private List<Salary> salaryList;
        private List<Tax> taxList;
    }

    @Getter
    @AllArgsConstructor
    static class Salary{
        @JsonProperty("총지급액")
        private String grossSalary;

    }

    @Getter
    @AllArgsConstructor
    static class Tax {
        @JsonProperty("총사용금액")
        private String calculatedTaxAmount;
    }
}
