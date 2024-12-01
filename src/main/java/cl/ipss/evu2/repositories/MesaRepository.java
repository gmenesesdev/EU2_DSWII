package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Mesa;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    @Query("""
                SELECT m
                FROM Mesa m
                WHERE m.id NOT IN (
                    SELECT r.mesa.id
                    FROM Reserva r
                )
            """)
    List<Mesa> findMesasDisponibles();
}
