package kz.aimurat_mvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kz.aimurat_mvc.dao.PersonDAO;
import kz.aimurat_mvc.models.Person;

@Component
public class PersonValidator implements Validator {
    // Валидация ошибок с базы данных

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getEmail()) != null) {
            errors.rejectValue("email", "", "Email is already taken");
        }
    }
}
