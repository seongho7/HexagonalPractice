package com.sh.hexagonal.refund.adapter.out.sh;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpyShClientImpl implements ShClient{
    @Override
    public UserScrapDto scrapUserIncome(GetUserIncomeDto dto) {
        var salary = new UserScrapDto.Salary("24,000,000");
        var tax = new UserScrapDto.Tax("2,000,000");
        var dataList = new UserScrapDto.DataList(Arrays.asList(salary), Arrays.asList(tax));
        var data = new UserScrapDto.Data(dataList);


        return new UserScrapDto("success", data);
    }
}
