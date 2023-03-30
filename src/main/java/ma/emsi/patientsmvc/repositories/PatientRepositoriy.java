package ma.emsi.patientsmvc.repositories;

import ma.emsi.patientsmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositoriy extends JpaRepository<Patient,Long> {

}
