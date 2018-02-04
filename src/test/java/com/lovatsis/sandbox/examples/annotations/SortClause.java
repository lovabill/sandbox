package com.lovatsis.sandbox.examples.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SortClause.value carries additional information in order
 * to build the 'order by' clause of a native SQL select statement.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SortClause {
    String value();
}