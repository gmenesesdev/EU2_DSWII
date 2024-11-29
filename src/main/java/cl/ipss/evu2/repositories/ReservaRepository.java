package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Reserva;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    /**
     * Encuentra reservas entre un rango de fechas.
     *
     * @param inicio la fecha y hora de inicio del rango.
     * @param fin    la fecha y hora de fin del rango.
     * @return una lista de reservas dentro del rango de fechas.
     */
    @Query("SELECT r FROM Reserva r WHERE r.fechaHora BETWEEN :inicio AND :fin")
    List<Reserva> findReservasByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Encuentra todas las reservas asociadas a una mesa específica.
     *
     * @param mesaId el ID de la mesa.
     * @return una lista de reservas para esa mesa.
     */
    @Query("SELECT r FROM Reserva r WHERE r.mesa.id = :mesaId")
    List<Reserva> findReservasByMesaId(Long mesaId);

    /**
     * Encuentra todas las reservas realizadas por un cliente específico.
     *
     * @param clienteId el ID del cliente.
     * @return una lista de reservas para ese cliente.
     */
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId")
    List<Reserva> findReservasByClienteId(Long clienteId);
}
