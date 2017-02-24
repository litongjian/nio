package game.nio.po;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.po
 * 概要：合法用户对象
 */
public final class AuthUser {
    private String auth;
    private String user;
    private boolean online;

    public Boolean isOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUser authUser = (AuthUser) o;

        return user != null ? user.equals(authUser.user) : authUser.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }
}
