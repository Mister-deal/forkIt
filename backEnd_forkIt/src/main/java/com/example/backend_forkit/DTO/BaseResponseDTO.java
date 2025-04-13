package com.example.backend_forkit.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDTO {

    private Object message;
    private Object data;

    public BaseResponseDTO(Object message, Object data) {
        this.message = message;
        this.data = data;
    }

    public BaseResponseDTO(Object message) {
        this.message = message;
    }


}
