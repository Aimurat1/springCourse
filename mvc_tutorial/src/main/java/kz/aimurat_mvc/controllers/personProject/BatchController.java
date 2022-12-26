package kz.aimurat_mvc.controllers.personProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kz.aimurat_mvc.dao.PersonDAO;

@Controller
@RequestMapping("/batch-update")
public class BatchController {
    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    private String index() {
        return "personProject/batch/index";
    }

    @GetMapping("/without")
    private String without() {
        personDAO.testWithoutBatchUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    private String with() {
        personDAO.testWithBatchUpdate();
        return "redirect:/people";
    }
}
