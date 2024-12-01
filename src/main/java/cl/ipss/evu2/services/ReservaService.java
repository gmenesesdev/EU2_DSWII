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

    public List<Reserva> listarReservasPorClienteId(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long reservaId) {
        reservaRepository.deleteById(reservaId);
    }
}
