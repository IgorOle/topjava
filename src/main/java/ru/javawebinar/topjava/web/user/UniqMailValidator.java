package ru.javawebinar.topjava.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.CrudUserRepository;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.UserUtil;
import ru.javawebinar.topjava.web.SecurityUtil;


@Component
public class UniqMailValidator implements org.springframework.validation.Validator {


    @Autowired
    private CrudUserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {

        return User.class.equals(clazz) || UserTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = null;
        if (User.class.equals(target.getClass())) {
            user = (User) target;
        } else if (UserTo.class.equals(target.getClass())) {
            user = UserUtil.asUser((UserTo) target);
        } else return;

        User findedUser = repository.getByEmail(user.getEmail());
        if (findedUser == null) return;
        if (findedUser.getId() != SecurityUtil.safeGet().getId()) {
            errors.rejectValue("email", "notUniqEmail", "email is not uniq");
        }
    }
}
