package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Mesa;

import java.util.Date;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    /**
     * Lista las mesas
     * 
     * @return una lista de todas las mesas.
     */
    @Query("SELECT m FROM Mesa m")
    List<Mesa> listarMesas();

    /**
     * Actualizar la mesa
     */
    @Query("UPDATE Mesa m SET m.capacidad = :capacidad WHERE m.id = :mesa_id")
    void actualizarMesa(Long mesa_id, int capacidad);

    /**
     * Muestra las mesas disponibles
     */
    @Query("""
                SELECT m
                FROM Mesa m
                WHERE m.id NOT IN (
                    SELECT r.mesa.id
                    FROM Reserva r
                    WHERE r.fecha = :fecha
                )
            """)
    List<Mesa> findMesasDisponibles(@Param("fecha") Date fecha);
}
