package com.lovatsis.sandbox.examples.annotations;

public class PersonDTO {

    @SortClause("person.name")
    private String personName;

    @SortClause("spouse.name")
    private String spouseName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
}

