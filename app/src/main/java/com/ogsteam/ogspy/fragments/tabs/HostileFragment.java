package com.ogsteam.ogspy.fragments.tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ogsteam.ogspy.OgspyActivity;
import com.ogsteam.ogspy.R;
import com.ogsteam.ogspy.network.download.DownloadTask;
import com.ogsteam.ogspy.ui.displays.HostileUtils;

import java.lang.ref.WeakReference;


/**
 * @author mwho
 */
public class HostileFragment extends Fragment {

    private static ListView listHostiles;
    private WeakReference<OgspyActivity> activity;

    /**
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
        activity = new WeakReference<>((OgspyActivity) getActivity());
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.hostiles, container, false);

        listHostiles = (ListView) layout.findViewById(R.id.list_view_hostiles);

        if (OgspyActivity.getDownloadHostilesTask() != null && OgspyActivity.getDownloadHostilesTask().getHelperHostile() == null) {
            DownloadTask.executeDownload(OgspyActivity.downloadHostilesTask);
        } else {
            HostileUtils.showHostiles(OgspyActivity.getDownloadHostilesTask().getHelperHostile());
        }

        return layout;
    }

    public static ListView getListHostiles() {
        return listHostiles;
    }
}
