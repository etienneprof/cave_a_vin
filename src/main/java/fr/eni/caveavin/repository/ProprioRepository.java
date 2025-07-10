package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.client.Proprio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprioRepository extends JpaRepository<Proprio,String> {
}
