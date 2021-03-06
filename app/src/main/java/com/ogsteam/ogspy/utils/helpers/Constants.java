package com.ogsteam.ogspy.utils.helpers;

public interface Constants {
    public static final String URL_GET_OGSPY_INFORMATION = "{0}/mod/xtense/xtense.php?toolbar_version=2.5.7&toolbar_type=FF&type=android&mod_min_version=2.5.1&user={1}&password={2}&univers={3}&versionAndroid={4}&versionOgspy={5}&device={6}&action={7}";
    public static final String URL_API_OGSPY= "{0}/index.php";
    public static final String REST_URL_API_OGSPY= "{0}/api";


    public static final int TIMER_DEFAULT_VALUE = 10;

    /* TYPE ENVOI XTENSE */
    public static final String XTENSE_TYPE_HOSTILES = "attacks";
    public static final String XTENSE_TYPE_ALLIANCE = "ally";
    public static final String XTENSE_TYPE_SPYS = "spys";
    public static final String XTENSE_TYPE_SERVER = "server";
    public static final String XTENSE_TYPE_RENTABILITES = "rentas";

    /* TYPE RENTAS */
    public static final String RENTA_TYPE_ME = "me";
    public static final String RENTA_TYPE_ALLY = "us";
    public static final String RENTA_TYPE_MEMBER = "member";

    /* TYPE DE NOTIFICATIONS */
    public static final String NOTIFICATION_TYPE_HOSTILES = "hostiles";
    public static final String NOTIFICATION_TYPE_MESSAGE = "message";

    /* EXEPTIONS */
    public static final Pair EXCEPTION_SAISIE = new Pair(0, "Problème de saisie");
    public static final Pair EXCEPTION_DATA_SAVE = new Pair(1, "Problème de sauvegarde");
    public static final Pair EXCEPTION_DATA_DELETE = new Pair(2, "Problème de suppression");
}
