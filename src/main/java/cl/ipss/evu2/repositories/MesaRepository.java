package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Mesa;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    /**
     * Encuentra todas las mesas que están disponibles.
     *
     * @return una lista de mesas disponibles.
     */
    @Query("SELECT m FROM Mesa m WHERE m.disponible = true")
    List<Mesa> findMesasDisponibles();
}
