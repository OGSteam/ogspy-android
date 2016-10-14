package com.ogsteam.ogspy.permission;

import android.content.Context;
import android.util.Log;

import com.ogsteam.ogspy.OgspyActivity;
import com.ogsteam.ogspy.R;
import com.ogsteam.ogspy.network.post.PostingTask;
import com.ogsteam.ogspy.network.post.SendMessage;
import com.ogsteam.ogspy.utils.StringUtils;

import java.net.URLEncoder;
import java.util.Random;

import static com.ogsteam.ogspy.permission.CommonUtilities.TAG;
import static com.ogsteam.ogspy.permission.CommonUtilities.displayMessage;

public final class ServerUtilities {
    private static final int MAX_ATTEMPTS = 5;
    private static final int BACKOFF_MILLI_SECONDS = 2000;
    private static final Random random = new Random();

    /**
     * Register this account/device pair within the server.
     *
     */
    public static void sendAlertMesage(final Context context, final String regId, final String username, final String message) {
        String serverUrl = OgspyActivity.commonUtilities.SERVER_URL_ALERT;
        try {
            SendMessage sendMessage = new SendMessage(context, StringUtils.formatPattern(serverUrl, regId, username, URLEncoder.encode(message)));
        sendMessage.execute(new String[] { "do"});
        } catch (Exception ex) {
            CommonUtilities.displayMessage(context, "Problème lors de l'envoi du message : " + ex.getMessage());
        }
    }
}
