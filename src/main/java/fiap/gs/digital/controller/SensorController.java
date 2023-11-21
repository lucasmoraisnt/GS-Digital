package fiap.gs.digital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fiap.gs.digital.model.Sensor;
import fiap.gs.digital.repository.SensorRepository;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SensorRepository repository;

    @GetMapping()
    public List<Sensor> index() {
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Sensor> create(@RequestBody Sensor sensor) {
        log.info("cadastrando novo sensor: " + sensor);
        repository.save(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(sensor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> show(@PathVariable Long id) {
        log.info("buscando sensor com id " + id);
        return ResponseEntity.ok(getSensor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sensor> destroy(@PathVariable Long id) {
        log.info("apagando sensor com id " + id);
        repository.delete(getSensor(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> update(@PathVariable Long id, @RequestBody Sensor sensor) {
        log.info("alterando sensor com id " + id);
        getSensor(id);
        sensor.setSensor_id(id);
        repository.save(sensor);
        return ResponseEntity.ok(sensor);
    }

    private Sensor getSensor(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor não encontrado"));
    }

}
