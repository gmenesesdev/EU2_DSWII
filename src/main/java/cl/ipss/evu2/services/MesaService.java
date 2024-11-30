package cl.ipss.evu2.services;

import cl.ipss.evu2.models.Mesa;
import cl.ipss.evu2.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public List<Mesa> findMesasDisponibles(LocalDate fecha) {
        return mesaRepository.findMesasDisponibles(fecha);
    }

    public Mesa guardarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void eliminarMesa(long id) {
        mesaRepository.deleteById(id);
    }

    public Mesa obtenerMesa(long id) {
        return mesaRepository.findById(id).orElse(null);
    }
}
