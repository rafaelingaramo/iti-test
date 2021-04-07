package br.com.iti.password.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * https://jsonapi.org/format/#errors
 */
public class ErrorEntity {
    private Integer status;
    private String title;
    private String detail;
}
