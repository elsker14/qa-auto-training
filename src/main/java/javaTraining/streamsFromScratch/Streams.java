package javaTraining.streamsFromScratch;

// Count the number of names starting with alphabet A in list

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    List<String> names = new ArrayList<>();
    List<String> otherNames = new ArrayList<>();

    public void initializeList() {
        names.add("Asbufaibfsa");
        names.add("Jiji");
        names.add("Adam");
        names.add("Checu");
        names.add("Avram");
        names.add("Abraham");
        names.add("Asasadas");

        otherNames.add("Marcela");
        otherNames.add("Maria");
        otherNames.add("Dana");
        otherNames.add("Gabitza");
    }

    @Test
    public void noStreams() {
        System.out.println("######### noStreams test - START - ######### ");
        initializeList();
        int count = 0;

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).startsWith("A"))
                count++;
        }

        System.out.println("without Streams: " + count);
        System.out.println("######### noStreams test - END - ######### ");
    }

    @Test
    public void streamFilter() {
        System.out.println("######### streamFilter test - START - ######### ");

        /* Stream functions: Filter, Count, Stream.of */
        long count = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println("with Streams: " + count);
        System.out.println(Stream.of("Asbufaibfsa", "Jiji", "Adam").filter(s -> s.startsWith("A")).count());
        System.out.println("----------------------------");

        /* Stream functions: how to print */
        names.stream().filter(s -> s.length() > 4).forEach(s -> System.out.println(s));
        System.out.println("----------------------------");
        names.stream().filter(s -> s.length() > 4).forEach(System.out::println);
        System.out.println("----------------------------");
        names.stream().filter(s -> s.length() > 4).limit(1).forEach(System.out::println);

        System.out.println("######### streamFilter test - END - ######### ");
    }

    @Test
    public void streamMap() {
        System.out.println("######### streamMap test - START - ######### ");

        /* print names that end with "a" with Uppercase */
        Stream.of("Asbufaibfsa", "Jija", "Adam").filter(s -> s.endsWith("a")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        System.out.println("----------------------------");
        Stream.of("Asbufaibfsa", "Jija", "Adam").filter(s -> s.endsWith("a")).map(String::toUpperCase).forEach(System.out::println);
        System.out.println("----------------------------");

        /* print names that start with "a" with Uppercase and sorted*/
        List<String> namesList = Arrays.asList("Asbufaibfsa", "Jija", "Adam");
        namesList.stream().filter(s -> s.startsWith("A")).sorted().map(String::toLowerCase).forEach(System.out::println);

        System.out.println("######### streamMap test - END - ######### ");
    }

    @Test
    public void streamConcat() {
        System.out.println("######### streamConcat test - START - ######### ");

        /* Combine 2 lists into one using Stream.concat */
        Stream<String> combinedStreams = Stream.concat(names.stream(), otherNames.stream());
        combinedStreams.sorted().forEach(System.out::println);

        System.out.println("######### streamConcat test - END - ######### ");
    }

    @Test
    public void streamAnyMatch() {
        System.out.println("######### streamAnyMatch test - START - ######### ");

        Stream<String> combinedStreams = Stream.concat(names.stream(), otherNames.stream());

        /* Find name in list */
        boolean found = combinedStreams.anyMatch(s -> s.equalsIgnoreCase("jiji"));
        Assert.assertTrue(found, "Name was not found!");
        System.out.println(found);

        System.out.println("######### streamAnyMatch test - END - ######### ");
    }

    @Test
    public void streamCollect() {
        System.out.println("######### streamCollect test - START - ######### ");

        /* Create list, convert to stream, do stuff, convert back to list */
        List<String> newList = Stream.of("Checu", "Lixi", "Sasuke", "Naruto", "Arya").filter(s -> s.length() > 4).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(newList.get(0));
        newList.stream().limit(2).forEach(System.out::println);

        System.out.println("######### streamCollect test - END - ######### ");
    }

    @Test
    public void streamUnique() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
        /* print unique numbers from array */
        /* sort the array */

        // Solution 1
        Set<Integer> uniqueNumbers1 = new HashSet<>(numbers);
        uniqueNumbers1.forEach(System.out::println);
        System.out.println("---------");

        // Solution 2
        Set<Integer> uniqueNumbers2 = numbers.stream().collect(Collectors.toSet());
        uniqueNumbers2.forEach(System.out::println);
        System.out.println("---------");

        // Solution 3 (proposed solution)
        numbers.stream().distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("---------");
    }
}
