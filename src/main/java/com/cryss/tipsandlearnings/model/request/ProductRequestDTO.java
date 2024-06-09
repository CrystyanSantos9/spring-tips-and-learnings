package com.cryss.tipsandlearnings.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO implements Serializable {

    private Long id;

    @NotBlank(message = "O campo não poder nulo, e nem vazio!")
    private String name;


}
