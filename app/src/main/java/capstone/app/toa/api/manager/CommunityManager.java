package capstone.app.toa.api.manager;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.object.Community;

public class CommunityManager {

    private static ToaApi api = ToaApi.getInstance();

    private HashMap<String, DatabaseReference> references = new HashMap<>();

    public DatabaseReference getReference(Community community) {
        String name = community.getName();
        if (!references.containsKey(name)) {
            references.put(name, api.getDatabaseManager().getCommunityReference().child(name));
        }
        return references.get(name);
    }


    private HashMap<String, Community> map = new HashMap<>();

    public void set(Community community) {
        set(community.getName(), community);
    }
    public void set(String name, Community community) {
        map.put(name, community);
    }

    public boolean remove(Community community) {
        return remove(community.getName());
    }
    public boolean remove(String name) {
        if (exists(name)) {
            map.remove(name);
            return true;
        }
        return false;
    }

    public boolean exists(String name) {
        return map.containsKey(name);
    }
    public Community get(String name) {
        return map.get(name);
    }

    public void updateDB(Community community) {

    }
}
