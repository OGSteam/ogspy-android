package com.ogsteam.ogspy.network.download;

import android.util.Log;
import android.widget.Spinner;

import com.ogsteam.ogspy.OgspyActivity;
import com.ogsteam.ogspy.OgspyApplication;
import com.ogsteam.ogspy.R;
import com.ogsteam.ogspy.data.models.Account;
import com.ogsteam.ogspy.fragments.tabs.RentabilitesFragment;
import com.ogsteam.ogspy.ui.displays.RentabilitesUtils;
import com.ogsteam.ogspy.utils.HttpUtils;
import com.ogsteam.ogspy.utils.OgspyUtils;
import com.ogsteam.ogspy.utils.StringUtils;
import com.ogsteam.ogspy.utils.helpers.Constants;
import com.ogsteam.ogspy.utils.helpers.RentabilitesHelper;

import org.json.JSONObject;

public class DownloadRentabilitesTask extends DownloadTask {
    public static final String DEBUG_TAG = DownloadRentabilitesTask.class.getSimpleName();

    private String interval;
    private String type;
    protected RentabilitesHelper helperRentabilites;
    protected static JSONObject dataJsonFromAsyncTask;

    public DownloadRentabilitesTask(String interval, String type) {
        dataJsonFromAsyncTask = null;
        this.helperRentabilites = null;
        this.interval = interval;
        this.type = type;
        typeDownload = DownloadType.RENTABILITES;
    }

    public DownloadRentabilitesTask() {
        dataJsonFromAsyncTask = null;
        this.helperRentabilites = null;
        Spinner rentaInterval = RentabilitesFragment.getRentabiliteInterval();
        Spinner rentatype = RentabilitesFragment.getRentabiliteType();
        int positionSelected = rentaInterval == null ? 0 : rentaInterval.getSelectedItemPosition();
        int positionSelectedType = rentatype == null ? 0 : rentatype.getSelectedItemPosition();
        this.interval = OgspyApplication.getApplication().getResources().getStringArray(R.array.interval_values)[positionSelected];
        this.type = OgspyApplication.getApplication().getResources().getStringArray(R.array.rentas_type_values)[positionSelectedType];
        typeDownload = DownloadType.RENTABILITES;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Account account = OgspyActivity.getSelectedAccount();
            if (!OgspyActivity.getHandlerAccount().getAllAccounts().isEmpty() && account != null) {
                String url = StringUtils.formatPattern(Constants.URL_GET_OGSPY_INFORMATION, account.getServerUrl(), account.getUsername(), OgspyUtils.enryptPassword(account.getPassword()), account.getServerUnivers(), OgspyActivity.versionAndroid, OgspyActivity.getVersionOgspy(), OgspyActivity.getDeviceName(), Constants.XTENSE_TYPE_RENTABILITES).concat("&interval=" + interval).concat("&typerenta=" + type);
                String data = HttpUtils.getUrl(url);
                if (data != null) {
                    dataJsonFromAsyncTask = new JSONObject(data.replaceAll("[(]", "").replaceAll("[)]", ""));
                    helperRentabilites = new RentabilitesHelper(dataJsonFromAsyncTask);
                }
            }
        } catch (Exception e) {
            Log.e(DEBUG_TAG, OgspyApplication.getApplication().getString(R.string.download_problem) + " (" + typeDownload.getLibelle() + ")", e);
            if (!isCancelled()) this.cancel(true);
        }
        return null;
    }

    public RentabilitesHelper getHelperRentabilites() {
        return helperRentabilites;
    }

    public String getType() {
        return type;
    }

    protected void onPostExecute(String result) {
        RentabilitesUtils.showRentabilites(helperRentabilites, type);
        super.onPostExecute(result);
    }
}
