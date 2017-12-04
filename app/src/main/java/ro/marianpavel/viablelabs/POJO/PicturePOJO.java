package ro.marianpavel.viablelabs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PicturePOJO {

    @Expose
    @SerializedName("large")
    private String large;

    @Expose
    @SerializedName("medium")
    private String medium;

    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
