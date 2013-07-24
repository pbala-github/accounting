package plb.accounting.services.impl.reporting;

import java.util.HashSet;
import java.util.Set;

/**
 * User: pbala
 * Date: 11/13/12 10:06 PM
 */
public class Group<K,E> {

    /**
     * 
     */
    private K key;

    /**
     * 
     */
    private Set<E> items = new HashSet<E>();

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Set<E> getItems() {
        return items;
    }

    public void setItems(Set<E> items) {
        this.items = items;
    }
    
    public boolean containsItem(E item){
        return items.contains(item);
    }
    
    public boolean addItem(E item){
        return items.add(item);
    }

    @Override
    public String toString() {
        return "Group{" +
                "key=" + key +
                ", items=" + items +
                '}';
    }
}
