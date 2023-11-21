package fiap.gs.digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fiap.gs.digital.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
