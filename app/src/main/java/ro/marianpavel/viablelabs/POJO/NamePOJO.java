package ro.marianpavel.viablelabs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NamePOJO {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("first")
    private String first;

    @Expose
    @SerializedName("last")
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
