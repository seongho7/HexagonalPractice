package com.sh.hexagonal.refund.adapter.out.sh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "shClient", url = "https://naver.co.kr")
public interface ShClient {
    //@PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    UserScrapDto scrapUserIncome(GetUserIncomeDto dto);
}
