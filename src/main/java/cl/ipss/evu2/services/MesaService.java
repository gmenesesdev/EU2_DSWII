package cl.ipss.evu2.services;

import cl.ipss.evu2.models.Mesa;
import cl.ipss.evu2.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public List<Mesa> findMesasDisponibles(Date fecha) {
        return mesaRepository.findMesasDisponibles(fecha);
    }
}
