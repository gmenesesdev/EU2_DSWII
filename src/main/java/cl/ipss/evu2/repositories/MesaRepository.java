package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Mesa;

import java.time.LocalDate; // Usamos LocalDate en lugar de Date
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    // Query modificada para aceptar LocalDate en lugar de Date
    @Query("""
                SELECT m
                FROM Mesa m
                WHERE m.id NOT IN (
                    SELECT r.mesa.id
                    FROM Reserva r
                    WHERE r.fecha = :fecha
                )
            """)
    List<Mesa> findMesasDisponibles(@Param("fecha") LocalDate fecha); // Cambiado de Date a LocalDate
}
