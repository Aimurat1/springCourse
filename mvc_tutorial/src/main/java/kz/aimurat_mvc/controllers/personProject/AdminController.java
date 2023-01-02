package kz.aimurat_mvc.controllers.personProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kz.aimurat_mvc.dao.PersonDAO;
import kz.aimurat_mvc.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDAO.index());
        return "personProject/admin/index";
    }

}
