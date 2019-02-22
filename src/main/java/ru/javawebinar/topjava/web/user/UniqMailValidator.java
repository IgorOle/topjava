package ru.javawebinar.topjava.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.CrudUserRepository;


@Service
public class UniqMailValidator implements org.springframework.validation.Validator {


    @Autowired
    private CrudUserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //case for CREATE
        User user = (User) target;
        User findedUser = repository.getByEmail(user.getEmail());
        if (findedUser != null && findedUser.getId() != user.getId()) {
            errors.rejectValue("email", "notUniqEmail", "email is not uniq");
        }
    }
}
