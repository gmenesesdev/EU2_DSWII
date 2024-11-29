package cl.ipss.evu2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    /**
     * Lista las reservas
     */
    @Query("SELECT r FROM Reserva r")
    List<Reserva> listarReservas();
    /**
     * lista las reservas de un cliente
     */
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :cliente_id")
    List<Reserva> listarReservasCliente(Long cliente_id);
    /**
     * Cancelar reserva
     */
    @Query("DELETE FROM Reserva r WHERE r.id = :reserva_id")
    void cancelarReserva(Long reserva_id);
    /**
     * Crea una reserva (se inserta en la tabla Reserva con la fecha
     */   
    @Query("INSERT INTO Reserva (mesa_id, cliente_id, fecha) VALUES (:mesa_id, :cliente_id, :fecha)")
    void crearReserva(Long mesa_id, Long cliente_id, Date fecha);


    
}
