package com.ogsteam.ogspy.network.download;

import android.util.Log;

import com.ogsteam.ogspy.OgspyActivity;
import com.ogsteam.ogspy.R;
import com.ogsteam.ogspy.data.models.Account;
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
    protected RentabilitesHelper helperRentabilites;
    protected static JSONObject dataJsonFromAsyncTask;

	public DownloadRentabilitesTask(OgspyActivity activity, String interval) {
        this.activity = activity;
        this.interval = interval;
        this.dataJsonFromAsyncTask = null;
        this.helperRentabilites = null;
    }

    @Override
	protected String doInBackground(String... params) {
		try {
			if(!activity.getHandlerAccount().getAllAccounts().isEmpty()){
				Account account = activity.getHandlerAccount().getAccountById(0);
				String url = StringUtils.formatPattern(Constants.URL_GET_OGSPY_INFORMATION, account.getServerUrl(), account.getUsername(), OgspyUtils.enryptPassword(account.getPassword()), account.getServerUnivers(), activity.versionAndroid, activity.getVersionOgspy(), Constants.XTENSE_TYPE_RENTABILITES).concat("&interval="+interval);
				String data = HttpUtils.getUrl(url);
                if(data != null){
                    dataJsonFromAsyncTask = new JSONObject(data.replaceAll("[(]", "").replaceAll("[)]", ""));
                    helperRentabilites = new RentabilitesHelper(dataJsonFromAsyncTask);
                }
			}
		} catch (Exception e) {
			Log.e(DEBUG_TAG, activity.getString(R.string.download_problem),e);
		}
		return null;
	}

    protected void onPostExecute(String result) {
        RentabilitesUtils.showRentabilites(helperRentabilites, activity);
        super.onPostExecute(result);
    }
}