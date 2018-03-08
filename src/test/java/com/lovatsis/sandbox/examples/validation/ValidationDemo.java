package com.lovatsis.sandbox.examples.validation;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ValidationDemo {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validCase() {
        Person person = new Person("John", 25, null);
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void invalidNameNullCase() {
        Person person = new Person(null, 25, null);
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Person> constraintViolation = constraintViolations.iterator().next();
        assertEquals("name", constraintViolation.getPropertyPath().toString());
        assertEquals("{javax.validation.constraints.NotNull.message}", constraintViolation.getMessageTemplate());
    }

    @Test
    public void invalidNameSizeCase() {
        Person person = new Person("", 25, null);
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Person> constraintViolation = constraintViolations.iterator().next();
        assertEquals("name", constraintViolation.getPropertyPath().toString());
        assertEquals("{javax.validation.constraints.Size.message}", constraintViolation.getMessageTemplate());
    }

    @Test
    public void invalidAgeCase() {
        Person person = new Person("John", 200, null);
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Person> constraintViolation = constraintViolations.iterator().next();
        assertEquals("age", constraintViolation.getPropertyPath().toString());
        assertEquals("{javax.validation.constraints.Max.message}", constraintViolation.getMessageTemplate());
    }

    //Showcase with custom constraint and extended validation enabling using groups.
    @Test
    public void invalidWebpageCase() {
        Person person = new Person("John", 25, "ftp://www.google.com");
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person, ExtendedValidationGroup.class);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Person> constraintViolation = constraintViolations.iterator().next();
        assertEquals("webpage", constraintViolation.getPropertyPath().toString());
        assertEquals("{com.lovatsis.constraints.URL.message}", constraintViolation.getMessageTemplate());

        //Interpolated message using resource bundle (located in ValidationMessage.properties file in test resources folder)
        System.out.println(constraintViolation.getMessage());
    }
}
