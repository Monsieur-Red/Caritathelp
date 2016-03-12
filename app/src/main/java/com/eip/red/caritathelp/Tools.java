package com.eip.red.caritathelp;

/**
 * Created by pierr on 12/03/2016.
 */

public class Tools {

    static public String upperCaseFirstLetter(String value) {
        StringBuilder newValue = new StringBuilder(value.toLowerCase());

        newValue.setCharAt(0, Character.toUpperCase(value.charAt(0)));

        return (newValue.toString());
    }

}
