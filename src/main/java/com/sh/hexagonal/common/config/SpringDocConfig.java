package com.sh.hexagonal.common.config;

import com.sh.hexagonal.common.STag;
import com.sh.hexagonal.common.response.ErrorCode;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SpringDocConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(
                                HttpHeaders.AUTHORIZATION,
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION)
                )
                .addServersItem(new Server().url("/"))
                .info(
                        new Info().title("3o3과제")
                                .description(
                                        new StringBuilder()
                                                .append("<h4>클라이언트는 로그인시 받은 토큰을 http 헤더에 아래의 포맷으로 요청해야합니다.</h4>")
                                                .append("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzd....")
                                                .append("<br><br>")
                                                .append("<h4>응답값은 content-type이 항상 application/json 으로 아래의 포맷으로 응답됩니다.</h4>")
                                                .append("{")
                                                .append("\"data\": [성공시 응답 데이터입니다.],")
                                                .append("\"errorCode\": \"실패일때 에러코드입니다.\",")
                                                .append("\"message\": \"에러메시지입니다.\",")
                                                .append("\"result\": \"SUCCESS or FAIL 값을 가집니다.\"")
                                                .append("}")
                                                .append("<br><br>")
                                                .append("<h4>공통 에러 코드 입니다.</h4>")
                                                .append(ErrorCode.COMMON_SYSTEM_ERROR).append(": 정의되지 않은 에러 <br>")
                                                .append(ErrorCode.COMMON_INVALID_PARAMETER).append(": 파라미터가 잘못된 경우 <br>")
                                                .append(ErrorCode.COMMON_ENTITY_NOT_FOUND).append(": 엔티티가 없는경우 <br>")
                                                .append(ErrorCode.AUTH_TOKEN_EXPIRED).append(": 토큰이 만료된 경우 <br>")
                                                .append(ErrorCode.AUTH_INVALID_TOKEN).append(": 토큰이 유효하지 않은 경우 <br>")
                                                .toString()
                                )
                )
                .addTagsItem(new Tag().name(STag.AUTH).description("인증"))
                .addTagsItem(new Tag().name(STag.ACCOUNT).description("계정"))
                .addTagsItem(new Tag().name(STag.REFUND).description("환급"))
                ;
    }

}
