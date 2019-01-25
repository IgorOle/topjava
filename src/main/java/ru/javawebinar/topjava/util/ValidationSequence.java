package ru.javawebinar.topjava.util;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationUtil.Validator.class})
public interface ValidationSequence {
}
