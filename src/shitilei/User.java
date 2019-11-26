package shitilei;

public class User {
    private String uAccount;
    private String uPwd;
    private int eNo;
    private int uGrage;

    public String getuAccount() {
        return uAccount;
    }

    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public int geteNo() {
        return eNo;
    }

    public void seteNo(int eNo) {
        this.eNo = eNo;
    }

    public int getuGrage() {
        return uGrage;
    }

    public void setuGrage(int uGrage) {
        this.uGrage = uGrage;
    }
}
