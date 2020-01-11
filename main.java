import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
    public static void main(String args[]){
        Hash<String, Integer> x = new Hash<String, Integer>();
        x.put("hi", 10);
    }

}

class Hash<T, U>{
    ArrayList<LinkedList<Packet<T, U>>> hashlist;
    int base;
    Hash(){
        hashlist = new ArrayList<LinkedList<Packet<T, U>>>();
        base = 10;
        for(int i = 0; i < base; i++){
            hashlist.add(null);
        }
    }
    private int hashFunc(T key){
        int hash = 7;
        String strkey = (String) key;
        for (int i = 0; i < strkey.length(); i++) {
            hash = hash*31 + (int) strkey.charAt(i);
        }
        return hash;
    }
    public void put(T key, U value){
        int hash = this.hashFunc(key);
        int index = hash % this.base;
        if(hashlist.get(index) == null){
            LinkedList<Packet<T, U>> ll = new LinkedList<Packet<T, U>>();
            hashlist.set(index, ll);
        }
    }

}

class Packet<T, U> { //A packet is a key-value pair
    T key;
    U value;
    Packet(T key, U value){
        this.key = key;
        this.value = value;
    }
}