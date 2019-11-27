package shitilei;

public class Employee {
    private int eID;
    private int eNo;
    private String eName;
    private float eMouthIntegral;
    private float eSumIntegral;
    private float eExtraIntegral;

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }

    public int geteNo() {
        return eNo;
    }

    public void seteNo(int eNo) {
        this.eNo = eNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public float geteMouthIntegral() {
        return eMouthIntegral;
    }

    public void seteMouthIntegral(float eMouthIntegral) {
        this.eMouthIntegral = eMouthIntegral;
    }

    public float geteSumIntegral() {
        return eSumIntegral;
    }

    public void seteSumIntegral(float eSumIntegral) {
        this.eSumIntegral = eSumIntegral;
    }

    public float geteExtraIntegral() {
        return eExtraIntegral;
    }

    public void seteExtraIntegral(float eExtraIntegral) {
        this.eExtraIntegral = eExtraIntegral;
    }
}
