package fr.eni.caveavin.security.services;

import fr.eni.caveavin.bo.client.Utilisateur;
import fr.eni.caveavin.repository.UtilisateurRepository;
import fr.eni.caveavin.security.JwtService;
import fr.eni.caveavin.security.controllers.AuthenticationRequest;
import fr.eni.caveavin.security.controllers.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private UtilisateurRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthenticationService(UtilisateurRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        Utilisateur user = userRepository.findByPseudo(request.getLogin());

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
