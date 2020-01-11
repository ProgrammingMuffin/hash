import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main{
    public static void main(String args[]){
        Hash<String, Integer> x = new Hash<String, Integer>();
        x.insert("Deepak", 10);
        x.insert("Rahul", 25);
        x.insert("Rahul", 56);
        System.out.println("Deepak: " + x.get("Deepak"));
        System.out.println("Rahul: " + x.get("Rahul"));
    }

}

class Hash<T, U>{
    ArrayList<LinkedList<Packet<T, U>>> hashlist;
    int base;
    Hash(){
        hashlist = new ArrayList<LinkedList<Packet<T, U>>>();
        base = 100;
        for(int i = 0; i < base; i++){
            hashlist.add(null);
        }
    }
    private int hashFunc(T key){
        int hash = 7;
        String strkey = (String) key;
        for (int i = 0; i < strkey.length(); i++) {
            hash = hash*5 + (int) strkey.charAt(i);
        }
        return hash;
    }
    public void insert(T key, U value){
        //new Packet<T, U>(key, value)
        int hash = this.hashFunc(key);
        int index = hash % this.base;
        int flag = 0;
        if(hashlist.get(index) == null){
            LinkedList<Packet<T, U>> ll = new LinkedList<Packet<T, U>>();
            hashlist.set(index, ll);
            insert(key, value);
        } else {
            if(hashlist.get(index).size() == 0){
                hashlist.get(index).add(new Packet<T, U>(key, value));
                return;
            } else {
                for(int i=0;i<hashlist.get(index).size();i++){
                    if(hashlist.get(index).get(i).key == key){
                        hashlist.get(index).get(i).value = value;
                    }
                }
            }
        }
    }

    public U get(T key){
        int hash = this.hashFunc(key);
        int index = hash % this.base;
        if(hashlist.get(index) == null){
            return null;
        } else {
            if(hashlist.get(index).size() == 0){
                return null;
            } else {
                ListIterator<Packet<T, U>> li = hashlist.get(index).listIterator();
                while(li.hasNext()){
                    Packet<T, U> temp = li.next();
                    if(temp.key == key){
                        return temp.value;
                    }
                }
            }
        }
        return null;
    }

}

class Packet<T, U> { //A packet is a key-value pair
    public T key;
    public U value;
    Packet(T key, U value){
        this.key = key;
        this.value = value;
    }
}