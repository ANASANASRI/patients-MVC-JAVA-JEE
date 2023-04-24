package ma.emsi.patientsmvc.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepositoriy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepositoriy patientRepositoriy;
    @GetMapping(path = "/user/index")
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

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id,String keyword,int page){
        patientRepositoriy.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String form(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> lisPatients(){
        return patientRepositoriy.findAll();
        }


    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@Valid Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepositoriy.save(patient);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@RequestParam(name = "id") Long id, Model model){
        Patient patient=patientRepositoriy.findById(id).get();
        model.addAttribute("patient",patient);
        return "edit";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(path = "/index1")
    public String patients1(){
        return "patients1";
    }

    //50 min part 2
}