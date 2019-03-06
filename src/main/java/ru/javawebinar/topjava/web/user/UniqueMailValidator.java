package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.HasEmail;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.CrudUserRepository;
import ru.javawebinar.topjava.web.MessagesUtil;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {
    @Autowired
    private CrudUserRepository repository;

    @Autowired
    MessagesUtil messagesUtil;

    @Override
    public boolean supports(Class<?> clazz) {
        return HasEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasEmail user = ((HasEmail) target);
        User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            errors.rejectValue("email", "notUniqueEmail", messagesUtil.getMessage("user.emailNotUniq"));
        }
    }
}
