package com.sh.hexagonal.refund.adapter.out.sh;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
      log.error("sh Server Error methodKey:{}, body:{}", methodKey, response.body().toString());
      throw new ShException("");
    }
}
