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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/delete")
    public String delete(long id ,String keyword,int page) {
        patientRepositoriy.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> lisPatients(){
        return patientRepositoriy.findAll();
        }

    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
    return "formPatients";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(path = "/index1")
    public String patients1(){
        return "patients1";
    }

}