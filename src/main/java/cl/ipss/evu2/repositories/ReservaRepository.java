package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
