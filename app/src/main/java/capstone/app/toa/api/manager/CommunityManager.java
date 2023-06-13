package capstone.app.toa.api.manager;

import java.util.HashMap;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.object.Community;

public class CommunityManager {

    private static HashMap<String, Community> map = new HashMap<>();

    /**
     * 사용자가 속해있는 Community 데이터가 포함된 데이터를 가져옵니다.
     * @return User Community Map
     */
    public HashMap<String, Community> toMap() {
        return map;
    }

    /**
     * Community데이터를 Map에 저장합니다.
     * @param community Community
     */
    public void set(Community community) {
        set(community.getName(), community);
    }

    /**
     * Community데이터를 이름을 키값으로 Map에 저장합니다.
     * @param name Name - Key
     * @param community Community - Value
     */
    public void set(String name, Community community) {
        if (exists(name)) {
            ToaApi.getDatabaseManager().teardownCommunity(community);
        }
        map.put(name, community);
        ToaApi.getDatabaseManager().setupCommunity(community);
    }

    /**
     * Community데이터를 Map에서 삭제합니다.
     * @param community
     * @return
     */
    public boolean remove(Community community) {
        return remove(community.getName());
    }

    /**
     * Community데이터 Map에서 해당 이름의 Community 데이터를 삭제합니다.
     * @param name
     * @return
     */
    public boolean remove(String name) {
        if (exists(name)) {
            ToaApi.getDatabaseManager().teardownCommunity(get(name));
            map.remove(name);
            return true;
        }
        return false;
    }

    /**
     * Map에서 해당 이름의 데이터가 존재하는지 확인합니다.
     * @param name
     * @return 존재할 경우 True, 존재하지 않을 경우 False
     */
    public boolean exists(String name) {
        return map.containsKey(name);
    }

    /**
     * Map에서 해당 이름의 데이터를 가져옵니다.
     * @param name
     * @return 데이터가 존재해서 가져온 경우 Community데이터, 존재하지 않을 경우 null
     */
    public Community get(String name) {
        return map.get(name);
    }

}
