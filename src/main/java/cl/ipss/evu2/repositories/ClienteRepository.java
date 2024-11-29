package cl.ipss.evu2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ipss.evu2.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Encuentra un cliente por su correo electrónico.
     *
     * @param email correo electrónico del cliente.
     * @return el cliente correspondiente o null si no existe.
     */
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Cliente findClienteByEmail(String email);
}
