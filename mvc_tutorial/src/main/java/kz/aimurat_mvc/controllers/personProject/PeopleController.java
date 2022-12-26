package kz.aimurat_mvc.controllers.personProject;

import java.util.List;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kz.aimurat_mvc.dao.PersonDAO;
import kz.aimurat_mvc.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        // Take all People from DAO and show in the View
        model.addAttribute("people", personDAO.index());
        return "personProject/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // Take one Person with the ID and show in the View
        model.addAttribute("person", personDAO.show(id));
        return "personProject/people/show";

    }

    // First method
    // @GetMapping("/new")
    // public String newPerson(Model model) {
    // model.addAttribute("person", new Person());
    // return "personProject/people/new";
    // }

    // Second method
    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person) {
        return "personProject/people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "personProject/people/new";
        }
        this.personDAO.add(person);
        return "redirect:/people/" + person.getId();
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", this.personDAO.show(id));
        return "personProject/people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "personProject/people/" + id + "/edit";
        }
        this.personDAO.update(id, person);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        this.personDAO.delete(id);
        return "redirect:/people";
    }
}
