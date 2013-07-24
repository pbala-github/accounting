package plb.accounting.services.impl.reporting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: pbala
 * Date: 11/13/12 10:12 PM
 */
public class GroupContainer<K,E> {
    
    private Map<K,Group<K,E>> groups = new HashMap<K, Group<K, E>>();
    
    public List<Group<K,E>> getGroupList(){
        return new ArrayList<Group<K, E>> (groups.values());
    }
    
    public boolean containsGroupWithKey(K key){
        return groups.containsKey(key);
    }
    
    public Group<K,E> getGroupWithKey(K key){
        return getGroupWithKey(key,false);
    }
    
    public void addGroup(Group<K,E> group){
        groups.put(group.getKey(),group);
    }
    
    public Group<K,E> createGroupWithKey(K key){
        Group<K,E> group = new Group<K, E>();
        group.setKey(key);
        addGroup(group);
        return group;
    }

    public Group<K,E> getGroupWithKey(K key,boolean forceCreation){
        if(containsGroupWithKey(key))
            return groups.get(key);
        return createGroupWithKey(key);
    }

    @Override
    public String toString() {
        return "GroupContainer{" +
                "groups=" + groups +
                '}';
    }
}
