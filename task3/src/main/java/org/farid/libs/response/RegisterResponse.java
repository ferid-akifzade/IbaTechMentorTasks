package org.farid.libs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterResponse {
    private String email;
    private String password;
    private String token;
}
