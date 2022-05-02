package optidata.aushakou.ventortechgifsaver.utils;

import android.content.Context;

import javax.inject.Inject;

public class Resources {

    Context context;

    @Inject
    public Resources(Context context) {
        this.context = context;
    }

    public String getStringById(int id){
        return context.getString(id);
    }
}
