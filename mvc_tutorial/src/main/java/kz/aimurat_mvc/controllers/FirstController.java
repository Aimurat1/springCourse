package kz.aimurat_mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    // First method of GET parameters
    // @GetMapping("/hello")
    // public String hello(HttpServletRequest request) {
    // String name = request.getParameter("name");
    // System.out.println(name);
    // return "/first/hello";
    // }

    // Second method of GET parameters
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false) String name,
            Model model) {
        model.addAttribute("name", name);
        // System.out.println(name);
        return "/first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "/first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
            @RequestParam("b") int b,
            @RequestParam("action") String action,
            Model model) {
        int result = 0;
        if (action.equals("multiplication")) {
            result = a * b;
        }
        if (action.equals("addition")) {
            result = a + b;
        }
        if (action.equals("substraction")) {
            result = a - b;
        }
        if (action.equals("division")) {
            result = a / b;
        }

        model.addAttribute("result", result);

        return "/first/calculator";
    }
}
