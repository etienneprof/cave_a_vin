package fr.eni.caveavin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HeritageTest {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ProprioRepository proprioRepository;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void display_allUtilisateurs() {
        System.out.println(utilisateurRepository.findAll());
    }

    @Test
    public void display_allProprios() {
        System.out.println(proprioRepository.findAll());
    }

    @Test
    public void display_allClients() {
        System.out.println(clientRepository.findAll());
    }
}