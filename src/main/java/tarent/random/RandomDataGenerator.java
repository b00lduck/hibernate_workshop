package tarent.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tarent.entities.Person;

@Component
public final class RandomDataGenerator {

    private final List<String> firstnames;
    private final List<String> lastnames;
    private final List<String> streets;

    private Random randomGenerator = new Random();

    public RandomDataGenerator(final ResourceLoader resourceLoader) throws IOException {
        firstnames = createListFromResource(
                resourceLoader.getResource("classpath:testdata/firstnames").getInputStream());

        lastnames = createListFromResource(
                resourceLoader.getResource("classpath:testdata/surnames").getInputStream());

        streets = createListFromResource(
                resourceLoader.getResource("classpath:testdata/streets").getInputStream());
    }

    public Person getRandomPerson() {
        final String firstName = getRandomItem(firstnames);
        final String lastName = getRandomItem(lastnames);
        return new Person(firstName, lastName);
    }

    private static List<String> createListFromResource(final InputStream firstnamesStream) throws IOException {
        final List<String> ret = new ArrayList<>();
        final BufferedReader br = new BufferedReader(new InputStreamReader(firstnamesStream));
        String line;
        while ((line = br.readLine()) != null) {
            ret.add(line);
        }
        br.close();
        return ret;
    }

    private static String getRandomItem(final List<String> listOfStrings) {
        return listOfStrings.get(ThreadLocalRandom.current().nextInt(listOfStrings.size()));
    }

}

