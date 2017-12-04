package ro.marianpavel.viablelabs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenericPOJO<T> {

    @Expose
    @SerializedName("results")
    private T object;

    @Expose
    @SerializedName("info")
    private InfoPOJO info;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
