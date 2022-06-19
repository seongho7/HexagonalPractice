package com.sh.hexagonal.common.util;

import com.sh.hexagonal.common.Money;

public class HangulMoneyFormatter {
    private Money money;
    public HangulMoneyFormatter(Money money) {
        this.money = money;
    }

    public String format() {
        var strMoney = money.floor().getAmount().toString();
        var result = new StringBuilder()
                .append(transform(strMoney, 9, 4, "억 "))
                .append(transform(strMoney, 5, 4, "만 "))
                .append(transform(strMoney, 4, 1, "천 "))
                .append(transform(strMoney, 1, 3, ""))
                .toString();

        return result.replaceAll("[ ]$", "")+"원";
    }

    private String transform(String source, int sourceMinimumLength, int unitMaxLength, String korUnit) {
        if(source.length() < sourceMinimumLength) return "";

        int endIndex = source.length()-(sourceMinimumLength-1);

        int beginIndex = 0;
        if(source.length() >= sourceMinimumLength + unitMaxLength) {
            beginIndex = endIndex - unitMaxLength;
        }

        int val = Integer.parseInt(source.substring(beginIndex, endIndex));
        return val == 0 ? "" : val+korUnit;
    }
}
