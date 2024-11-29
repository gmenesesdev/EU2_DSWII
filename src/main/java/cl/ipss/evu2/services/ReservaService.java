package cl.ipss.evu2.services;

import cl.ipss.evu2.models.Reserva;
import cl.ipss.evu2.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.listarReservas();
    }

    public List<Reserva> listarReservasCliente(Long clienteId) {
        return reservaRepository.listarReservasCliente(clienteId);
    }

    public void cancelarReserva(Long reservaId) {
        reservaRepository.cancelarReserva(reservaId);
    }

    public void crearReserva(Long mesaId, Long clienteId, Date fecha) {
        reservaRepository.crearReserva(mesaId, clienteId, fecha);
    }
}
