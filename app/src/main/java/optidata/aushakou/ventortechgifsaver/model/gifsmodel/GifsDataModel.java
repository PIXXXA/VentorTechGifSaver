package optidata.aushakou.ventortechgifsaver.model.gifsmodel;

import com.google.gson.annotations.SerializedName;

public class GifsDataModel {

    @SerializedName("type")
    String type;

    @SerializedName("url")
    String url;

    @SerializedName("embed_url")
    String embed_url;

    @SerializedName("images")
    ImagesModel images;

    public ImagesModel getImages() {
        return images;
    }

    public void setImages(ImagesModel images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    public void setEmbed_url(String embed_url) {
        this.embed_url = embed_url;
    }

    public GifsDataModel(String type, String url, String embed_url) {
        this.type = type;
        this.url = url;
        this.embed_url = embed_url;
    }
}
