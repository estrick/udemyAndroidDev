package javaDeepDive;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class HelloWorld {

    public static void main(String[] args) {
            // STRINGS
//        String fName = "Elliot";
//        String lName = "Strickland";
//        String fullName = fName + lName;
//        int nameLength = fName.length() + lName.length();
//        int age = 25;
//        System.out.printf("My name is %s %s, I am %d years old.", fName, lName, age);
//        System.out.printf("\nThere are %d characters in my name.\n", nameLength);

            // ARRAYS
//        int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17};
//        System.out.println(primeNumbers.length);
//        for (int num : primeNumbers) {
//            System.out.printf("%d ", num);
//        }

            // LISTS
//        List countries = new ArrayList();
//        countries.add("England");
//        countries.add("Australia");
//        countries.add("Argentina");
//        System.out.println(countries);
//        System.out.println(countries.get(1));
//        countries.remove(2);
//        System.out.println(countries);

            // MAPS
//        Map family = new HashMap();
//        family.put("Brother1", "Jacob");
//        family.put("Brother2", "Freddy");
//        family.put("Father", "David");
//        family.put("Mother", "Zoe");
//        System.out.println(family.get("Father"));
//        System.out.println(family.toString());

            // IF STATEMENTS
        int[] familyAges = {18, 23, 25, 57, 60};
        if(familyAges[0] <= familyAges[3]) {
            System.out.println("Freddy is younger than Mum");
        }
    }
}
