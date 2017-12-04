package ro.marianpavel.viablelabs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class InfoPOJO {

    @Expose
    @SerializedName("seed")
    private String seed;

    @Expose
    @SerializedName("results")
    private long results;

    @Expose
    @SerializedName("page")
    private int page;

    @Expose
    @SerializedName("version")
    private String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
