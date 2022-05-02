package optidata.aushakou.ventortechgifsaver.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GifObjectModel {

    @SerializedName("data")
    List<GifsDataModel> type;

    public List<GifsDataModel> getType() {
        return type;
    }
}
