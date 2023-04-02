package ma.emsi.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepositoriy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepositoriy patientRepositoriy;
    @GetMapping(path = "/index")
    public String patients(Model model) {

        List<Patient> patients = patientRepositoriy.findAll();
        model.addAttribute("listPatients",patients);
        return "patients";

    }
}