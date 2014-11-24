package com.iassistant.android.collector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import com.iassistant.android.entities.AbstractModule;
import com.iassistant.android.entities.AnonymousData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by lan on 11/24/14.
 */
public class AnonymousDataCollector implements Collector {

    private static final Logger log = LoggerFactory.getLogger(AnonymousDataCollector.class);

    private Context mContext;

    public AnonymousDataCollector(Context context) {
        mContext = context;
    }

    @Override
    public AbstractModule collect() {
        final String deviceId = ((TelephonyManager) mContext.getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId();
        String version = "";
        try {
            version = (mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0)).versionName;
        }catch (PackageManager.NameNotFoundException e) {

        }

        AnonymousData data = new AnonymousData();
        data.setCarrier("UNKNOWN");
        data.setClientId(deviceId);
        data.setClientType("Android");
        data.setClientVersion(version);
        data.setIp(getLocalIpAddress());
        return data;
    }

    private String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            log.error("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }
}
