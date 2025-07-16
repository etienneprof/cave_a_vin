package fr.eni.caveavin.restrepository;

import fr.eni.caveavin.bo.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRestRepository extends JpaRepository<Client, String> {
}
