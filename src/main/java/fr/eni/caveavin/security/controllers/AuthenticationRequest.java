package fr.eni.caveavin.security.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "login")
public class AuthenticationRequest {
    private String login;
    private String password;
}
