package ch.zli.m223.punchclock.ViewModel;

public class LoginResultViewModel {

    private String token;
    private long id;

    public LoginResultViewModel(String token, long id){
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
