package cl.ipss.evu2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // ! Querys para Admin
    // * Lista las reservas
    @Query("""
                SELECT r.id,
                c.nombre as,
                r.fecha,
                r.mesa_id,
                m.capacidad
                FROM Reserva r
                left join Cliente c on r.cliente_id = c.id
                left join Mesa m on r.mesa_id = m.id
            """)
    List<Reserva> listarReservas();

    // ! Querys para Cliente
    // * Crea una reserva
    @Query("INSERT INTO Reserva (mesa_id, cliente_id, fecha) VALUES (:mesa_id, :cliente_id, :fecha)")
    void crearReserva(Long mesa_id, Long cliente_id, Date fecha);

    // * lista las reservas de un cliente
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :cliente_id")
    List<Reserva> listarReservasCliente(Long cliente_id);

    // * Cancelar reserva
    @Query("DELETE FROM Reserva r WHERE r.id = :reserva_id")
    void cancelarReserva(Long reserva_id);

    // * Modificar reserva
    @Query("UPDATE Reserva r SET r.fecha = :fecha WHERE r.id = :reserva_id")
    void modificarReserva(Long reserva_id, Date fecha);

    

}
