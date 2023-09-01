import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String description;
    private List<Item> geral;
    private Map<String, Room> exits;

    public Room(String description,ArrayList<Item> itenssala) {

        this.description = description;
        exits = new HashMap<>();
        geral = new ArrayList<>();
        geral.addAll(itenssala);
    }

    public void addItem(Item item) {
        geral.add(item);
    }

    public String getLongDescription() {
        StringBuilder descriçãototal = new StringBuilder();
        for (Item item : geral) {
            descriçãototal.append(item.getInformação());
        }
        return "Sua localização:" + description + descriçãototal + ".\n" + getExitString();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    private String getExitString() {
        return "Exits: " + String.join(" ", exits.keySet());
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
}