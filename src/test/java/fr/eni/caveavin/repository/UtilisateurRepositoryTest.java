package fr.eni.caveavin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UtilisateurRepositoryTest {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void test_select() {
        System.out.println(utilisateurRepository.findByPseudo("log5"));
        System.out.println(utilisateurRepository.findByPseudoAndPassword("log5", "pwd5"));
        System.out.println(utilisateurRepository.findByPseudoAndPassword("log5", "pwd4"));
    }
}