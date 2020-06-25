package controller;

import gui.ServerInfo;
import model.Message;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a server simulator.
 */
public class MessageServer implements Iterable<Message>{
    private Map<Integer, List<Message>> messages;
    private List<Message> selectedMessages;

    public MessageServer() {
        messages = new TreeMap<>();
        selectedMessages = new CopyOnWriteArrayList<>();

        // Amsterdam.
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("Links bolwerk", "Amsterdam is een links bolwerk"));
        messageList.add(new Message("Twee universiteiten", "Amsterdam heeft twee universiteiten"));
        messages.put(ServerInfo.AMSTERDAM, messageList);

        // Alkmaar.
        messageList = new ArrayList<>();
        messageList.add(new Message("Lekkere kaas", "Je kan hier lekkere kaas kopen"));
        messageList.add(new Message("Mooie stad", "Leuk winkelstadje"));
        messages.put(ServerInfo.ALKMAAR, messageList);

        // Den Bosch.
        messageList = new ArrayList<>();
        messageList.add(new Message("Niet te verstaan", "Ik versta die mensen hier niet onder de rivieren"));
        messageList.add(new Message("Wel gezellig", "De mensen zijn wel gezellig"));
        messages.put(ServerInfo.DEN_BOSCH, messageList);

        // London.
        messageList = new ArrayList<>();
        messageList.add(new Message("Where is the pub", "I really need a pint"));
        messageList.add(new Message("Where is the loo", "I really need to take a piss"));
        messages.put(ServerInfo.LONDON, messageList);

        // Bristol.
        messageList = new ArrayList<>();
        messageList.add(new Message("Nice beach", "Bristol has a beautiful beach"));
        messageList.add(new Message("Drukke kermis", "Vervelend dat de kermis zo druk is"));
        messages.put(ServerInfo.BRISTOL, messageList);

        // Leeds.
        messageList = new ArrayList<>();
        messageList.add(new Message("Welcome in Leeds", "What a shithole"));
        messageList.add(new Message("Goodbye", "Glad to move on"));
        messages.put(ServerInfo.LEEDS, messageList);
    }

    public void fillSelectedServers(Set<Integer> servers) {
        servers.stream()
                .forEach(
                        id -> messages.get(id).stream()
                                .filter(message -> !selectedMessages.contains(message))
                                .forEach(selectedMessages::add));
    }

    public int getMessageCount() {
        return selectedMessages.size();
    }

    @Override
    public Iterator<Message> iterator() {
        return new MessageIterator(selectedMessages);
    }
}

class MessageIterator implements Iterator<Message> {
    private Iterator<Message> iterator;

    public MessageIterator(List<Message> messages) {
        iterator = messages.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Message next() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
