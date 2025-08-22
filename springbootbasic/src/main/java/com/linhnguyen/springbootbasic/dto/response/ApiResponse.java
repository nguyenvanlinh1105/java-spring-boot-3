package com.linhnguyen.springbootbasic.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)// khai báo trường nào null thì không trả về
public class ApiResponse <T> {
    private int code = 1000;
    private String message;
    private T result;
}
