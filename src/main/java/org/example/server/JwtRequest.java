package org.example.server;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
