package capstone.app.toa.api.listener;

import capstone.app.toa.api.ToaApi;

public abstract class TodoRefreshListener {

    protected static ToaApi api = ToaApi.getInstance();

    public abstract void onRefreshed();

}
