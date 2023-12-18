package edu.miu.cs.cs544.controller;


import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Integer id) {
        return reservationRepository.findById(id)
                .map(reservation -> ResponseEntity.ok().body(reservation))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok().body(reservationRepository.findAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAllReservationsByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.ok().body(reservationRepository.findAllByCustomerId(customerId));
    }

    @PostMapping()
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            ResponseDto createdReservation = reservationService.createReservation(reservationDTO);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}