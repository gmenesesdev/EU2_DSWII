package cl.ipss.evu2.services;

import cl.ipss.evu2.models.Reserva;
import cl.ipss.evu2.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva listarReservasPorId(Long clienteId) {
        return reservaRepository.findById(clienteId).orElse(null);
    }

    public void cancelarReserva(Long reservaId) {
        reservaRepository.deleteById(reservaId);
    }
}
