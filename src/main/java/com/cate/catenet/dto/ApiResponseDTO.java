package com.cate.catenet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {
    @JsonIgnore
    private HttpStatus status;
    @JsonProperty
    private Boolean success;
    @JsonProperty
    private String message;
    @JsonProperty
    private T object;

    public ApiResponseDTO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.success = status.is2xxSuccessful();
    }

    public void setSuccess(boolean success) {}
}
