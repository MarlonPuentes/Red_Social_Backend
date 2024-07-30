package com.uptc.dto;

import com.uptc.annotation.ValidEmail;
import com.uptc.annotation.ValidPassword;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmailDto {
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
