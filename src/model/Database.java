package model;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Person> personList;

    public Database() {
        personList = new LinkedList<>();
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void deletePerson(int row) {
        personList.remove(row);
    }

    public void clear() {
        personList.clear();
    }

    public List<Person> getPersonList() {
        return Collections.unmodifiableList(personList);
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

    public void loadPersons(File file) throws IOException, ClassNotFoundException{

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
}
