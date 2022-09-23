package com.mfarial.practicebaseapp.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {
    private String foodName;
    private BigDecimal quantity;
}
