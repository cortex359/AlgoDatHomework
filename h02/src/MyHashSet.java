import java.util.ArrayList;

public class MyHashSet<K> {
    private int count;
    @SuppressWarnings("unchecked")
    private ArrayList<K>[] hashTable;
    
    public MyHashSet(){
        this.hashTable = initializeNewHashTable(10);
    }

    private ArrayList<K>[] initializeNewHashTable(int size){
        ArrayList<K>[] table = new ArrayList[size];
        for(int i = 0; i < hashTable.length; i++){
            hashTable[i] = new ArrayList<K>();
        }
        return table;
    }
    private void grow(){
        ArrayList<K>[] newHashTable = initializeNewHashTable(hashTable.length*2);
        for (ArrayList<K> list : this.hashTable){
            for(K elem : list){
                newHashTable[elem.hashCode() % newHashTable.length].add(elem);
            }
        }
        hashTable = newHashTable;
    }
    
    public boolean add(K element){
        if(count > hashTable.length*2){
            this.grow();
        }
        if(this.contains(element))
            return true;
        hashTable[element.hashCode() % hashTable.length].add(element);
        count++;
        return false;
    }
    
    public boolean delete(K element){
        return hashTable[element.hashCode()%hashTable.length].remove(element);
    }
    public boolean contains(K element){
        for(ArrayList<K> list : hashTable){
            for(K elem : list){
                if(element == elem)
                    return true;
            }
        }
        return false;
    }
    
    public ArrayList<K> getElements() {
        ArrayList<K> elems = new ArrayList<>();
        for(ArrayList<K> list : hashTable){
            elems.addAll(list);
        }
        return elems;
    }
}
