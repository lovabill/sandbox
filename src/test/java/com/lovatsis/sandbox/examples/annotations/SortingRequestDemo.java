package com.lovatsis.sandbox.examples.annotations;

import java.lang.reflect.Field;

/**
 * This is an example on custom annotations and how to enhance reflection capabilities of a java bean.
 * <p>
 * Let's assume that a database stores two tables: person and spouse. Both tables have a column named 'name'.
 * It is common -when using JPA- to collect many column data into a single bean (a.k.a Data Transfer Object or DTO).
 * <p>
 * If a mapping should exist between the DTO fields and the column of the database, field names are not adequate to
 * model this mapping without a custom convention (e.g. convert camelcase to lowercase with period delimiter:
 * personName -> person.name). If the convention cannot cover the complexity of the situation, a custom annotation can
 * carry additional information to assist the mapping.
 */
public class SortingRequestDemo {

    public static void main(String args[]) throws Exception {

        System.out.println("Valid SQL statements with all possible sorting types:");

        for (Field field : PersonDTO.class.getDeclaredFields()) {

            String orderByClause;
            SortClause alternativeName = field.getAnnotation(SortClause.class);
            if (alternativeName != null) {
                orderByClause = alternativeName.value();
            } else {
                orderByClause = field.getName();
            }
            String query = "select * from person join spouse on person.spouse_id = spouse.id order by " + orderByClause;

            System.out.println(query);
        }
    }
}