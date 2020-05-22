package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Person> personList;

    public Database() {
        personList = new ArrayList<>();
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void savePersons(File file) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file))
        )) {
            for (Person person : personList) {
                objectOutputStream.writeObject(person);
            }
        }
    }

    public List<Person> loadPersons(File file) throws IOException, ClassNotFoundException{
        List<Person> loadedPersons = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream
                (new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object object = inputStream.readObject();
                if (object instanceof Person) {
                    loadedPersons.add((Person)object);
                }
            }
        } catch (EOFException eofException) {
            // Reached the end of the file.
        }

        return loadedPersons;
    }
}
