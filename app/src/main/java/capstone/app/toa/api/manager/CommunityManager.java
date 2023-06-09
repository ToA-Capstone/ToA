package capstone.app.toa.api.manager;

import java.util.HashMap;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.object.Community;

public class CommunityManager {

    private static ToaApi api = ToaApi.getInstance();

    private HashMap<String, Community> map = new HashMap<>();

    public void set(Community community) {
        set(community.getName(), community);
    }
    public void set(String name, Community community) {
        if (exists(name)) {
            api.getDatabaseManager().teardownCommunity(community);
        }
        map.put(name, community);
        api.getDatabaseManager().setupCommunity(community);
    }

    public boolean remove(Community community) {
        return remove(community.getName());
    }
    public boolean remove(String name) {
        if (exists(name)) {
            api.getDatabaseManager().teardownCommunity(get(name));
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

}
