package optidata.aushakou.ventortechgifsaver.model;

import com.google.gson.annotations.SerializedName;

public class ImagesModel {

    @SerializedName("original")
    OriginalModel original;

    public OriginalModel getOriginal() {
        return original;
    }

    public void setOriginal(OriginalModel original) {
        this.original = original;
    }
}
