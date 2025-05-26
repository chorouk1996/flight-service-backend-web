package com.service.backend.web.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionalExceptionDto implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;

    private String error;

    private String message;

    private String path;


    public FunctionalExceptionDto(String message,HttpStatus status){
        super();
        this.message = message;
        this.status = status;
        this.error = String.valueOf(status.value());
        this.timestamp = LocalDateTime.now();
    }
}
