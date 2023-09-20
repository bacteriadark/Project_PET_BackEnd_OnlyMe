package project_pet_backEnd.user.dto;

import lombok.Data;

@Data
public class ResultResponse<T> {
    private Integer code =200;
    private  T message;
    private Object data;
}
