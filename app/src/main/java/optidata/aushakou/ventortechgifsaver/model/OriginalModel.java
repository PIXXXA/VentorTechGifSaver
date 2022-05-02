package optidata.aushakou.ventortechgifsaver.model;

import com.google.gson.annotations.SerializedName;

public class OriginalModel {

    @SerializedName("url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OriginalModel(String url) {
        this.url = url;
    }

    public OriginalModel() {
    }
}
