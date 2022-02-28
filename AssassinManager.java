import java.util.*;

public class AssassinManager {
    private AssassinNode killKing;
    private AssassinNode graveyard;

    public AssassinManager(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(); 
        }
        killKing = new AssassinNode(names.get(0));
        AssassinNode current = killKing;
        for (int index = 1; index < names.size(); index++) {
            current.next = new AssassinNode(names.get(index));
            current = current.next;
        }
    }

    public void printKillRing() {
        AssassinNode current = killKing;
        while (current.next != null) {
            System.out.println("    " + current.name + " is stalking " + current.next.name);
            current = current.next;
        }
        System.out.println("    " + current.name + " is stalking " + killKing.name);

    }

    public void printGraveyard() {
        // if (graveyard != null) {
        //     int index = 0;
        //     AssassinNode current = graveyard;
        //     while (current.next != null) {
        //         index++;
        //         current = current.next;
        //     }
        //     for (int element = index; index > 0; index--) {
        //         int count = 0;
        //         while (current.next != null) {
        //             count++;
        //             if (count == element) {
        //                 System.out.println("    " + graveyard.name + " was killed by " + graveyard.killer);
        //             }
        //             current = current.next;
        //         }
        //     }
        // }
        AssassinNode current = graveyard;
        while (current != null) {
            System.out.println("    " + current.name + " was killed by " + current.killer);
            current = current.next;
        }
    }

    public boolean killRingContains(String name) {
        AssassinNode current = killKing;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean graveyardContains(String name) {
        AssassinNode current = graveyard;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean gameOver() {
        return (killKing.next == null);
    }

    public String winner() {
        if (!gameOver()) {
            return killKing.name;
        } else {
            return null;
        }
    }

    public void kill(String name) {
        if (gameOver()) {
            throw new IllegalStateException();
        } else if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        }
        AssassinNode current = killKing;
        while (current.next != null) {
            if (current.next.name.equalsIgnoreCase(name)) {
                AssassinNode newNode = new AssassinNode(name);
                newNode.killer = current.name;
                graveyard.next = newNode;
                
            } 
            current = current.next;
        }
    }

}