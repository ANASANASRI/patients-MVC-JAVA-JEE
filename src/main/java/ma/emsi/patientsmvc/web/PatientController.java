package ma.emsi.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepositoriy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepositoriy patientRepositoriy;
    @GetMapping(path = "/index")
    public String patients(Model model,
                           @RequestParam(name ="page",defaultValue = "0") int page,
                           @RequestParam(name ="size",defaultValue = "5") int size,
                           @RequestParam(name ="keyword",defaultValue = "") String keyword) {

        Page<Patient> pagePatients = patientRepositoriy.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("pageCurrent",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

    @GetMapping(path = "/delete")
    public String delete(long id) {
        patientRepositoriy.deleteById(id);
        return "redirect:/index";
    }

    //1h:16min
}