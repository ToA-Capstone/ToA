package capstone.app.toa.api.manager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserManager {

    private FirebaseAuth auth;

    /**
     * FirebaseAuth Instance를 가져옴
     *
     * @return FirebaseAuth.getInstance();
     *
     */
    public FirebaseAuth getAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    /**
     * FirebaseAuth에서 FirebaseUser를 가져옴
     *
     * @return 로그인일 경우 FirebaseUser, 로그아웃일 경우 null
     */
    public FirebaseUser get() {
        return getAuth().getCurrentUser();
    }

    /**
     * Firebase에 등록된 Google 계정의 Uid를 가져옴
     *
     * @return 로그인일 경우 Uid, 로그아웃일 경우 null
     */
    public String getUid() {
        return isLogin() ? get().getUid() : null;
    }

    /**
     * Firebase에 등록된 Google 계정의 Email을 가져옴
     *
     * @return 로그인일 경우 Email, 로그아웃일 경우 null
     */
    public String getEmail() {
        return isLogin() ? get().getEmail() : null;
    }

    /**
     * 로그인인지 확인함
     *
     * @return 로그인일 경우 true, 로그아웃일 경우 false
     */
    public boolean isLogin() {
        return get() != null;
    }

}
