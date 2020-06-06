package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSaver {
    private List<Person> personList;

    public FileSaver() {
        personList = new ArrayList<>();
    }

    public void savePersonsToFile(File file) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file))
        )) {
            for (Person person : personList) {
                objectOutputStream.writeObject(person);
            }
        }
    }

    public void loadPersonsFromFile(File file) throws IOException, ClassNotFoundException{

        try (ObjectInputStream inputStream = new ObjectInputStream
                (new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object object = inputStream.readObject();
                if (object instanceof Person) {
                    personList.add((Person)object);
                }
            }
        } catch (EOFException eofException) {
            // Reached the end of the file.
        }
    }

    public List<Person> getResult() {
        return Collections.unmodifiableList(personList);
    }
}
