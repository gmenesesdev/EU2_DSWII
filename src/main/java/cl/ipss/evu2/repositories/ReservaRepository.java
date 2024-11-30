package cl.ipss.evu2.repositories;

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
}
