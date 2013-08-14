package com.actions.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import com.actions.hardware.ICableStatusListener;
import com.actions.hardware.IDisplayService.Stub;
import java.util.ArrayList;

public class DisplayService extends IDisplayService.Stub
{
  public static final int CABLE_CVBS_PLUG_IN = 1;
  public static final int CABLE_CVBS_PLUG_OUT = 2;
  public static final int CABLE_HDMI_ON = 1;
  public static final int CABLE_HDMI_PLUG_IN = 256;
  public static final int CABLE_HDMI_PLUG_OUT = 512;
  public static final int CABLE_TVOUT_ON = 2;
  public static final int CABLE_YPBPR_PLUG_IN = 4;
  public static final int CABLE_YPBPR_PLUG_OUT = 8;
  public static final int HOTPLUG = 1;
  private static final String TAG = "DisplayService";
  public static int mCableState = 0;
  Handler eventHander = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Intent localIntent = new Intent("com.actions.intent.cablechanged");
      Log.d("DisplayService", "Handler get new events:" + paramAnonymousMessage);
      if (paramAnonymousMessage.what == 1);
      String str1;
      String str2;
      switch (paramAnonymousMessage.arg1)
      {
      default:
        return;
      case 1:
        str1 = "cvbs";
        str2 = "plugin";
      case 2:
      case 4:
      case 8:
      case 256:
      case 512:
      }
      while (true)
      {
        localIntent.putExtra("action", str2);
        localIntent.putExtra("type", str1);
        if (DisplayService.this.mContext == null)
          break;
        DisplayService.this.mContext.sendBroadcast(localIntent);
        Log.v("DisplayService", "Send sendBroadcast:" + localIntent + paramAnonymousMessage.arg1);
        return;
        str1 = "cvbs";
        str2 = "plugout";
        continue;
        str1 = "ypbpr";
        str2 = "plugin";
        continue;
        str1 = "ypbpr";
        str2 = "plugout";
        continue;
        str1 = "hdmi";
        str2 = "plugin";
        continue;
        str1 = "hdmi";
        str2 = "plugout";
      }
      DisplayService.this.notifyStatusListener(str1, str2);
    }
  };
  private Context mContext;
  private boolean mCvbsPluggedIn = false;
  private int mDisplayerCount;
  private HandlerThread mHandlerThread;
  private boolean mHdmiPluggedIn = false;
  private ArrayList<Listener> mListeners = new ArrayList();
  private PowerManager.WakeLock mTvoutEventWakeLock;
  private boolean mVideoPlay = false;
  private String onoffMode;
  private int wakeLockFlag = 0;

  static
  {
    System.load("/system/lib/libactions_runtime.so");
  }

  public DisplayService(Context paramContext)
  {
    this.mContext = paramContext;
    _init();
    this.mDisplayerCount = _getDisplayerCount();
    this.onoffMode = SystemProperties.get("ro.hdmi.onoffmode", "alwayson");
    this.mTvoutEventWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "handleTvoutEvent");
    IntentFilter localIntentFilter1 = new IntentFilter();
    localIntentFilter1.addAction("android.intent.action.HDMI_PLUGGED");
    this.mContext.registerReceiver(new HdmiReceiver(null), localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter();
    localIntentFilter2.addAction("android.intent.action.CVBS_PLUGGED");
    this.mContext.registerReceiver(new CvbsReceiver(null), localIntentFilter2);
    if (this.onoffMode.equals("auto"))
    {
      IntentFilter localIntentFilter3 = new IntentFilter();
      localIntentFilter3.addAction("android.intent.action.SCREEN_ON");
      this.mContext.registerReceiver(new ScreenReceiver(null), localIntentFilter3);
      IntentFilter localIntentFilter4 = new IntentFilter();
      localIntentFilter4.addAction("android.intent.action.SCREEN_OFF");
      this.mContext.registerReceiver(new ScreenReceiver(null), localIntentFilter4);
    }
  }

  private native boolean _enableOutput(boolean paramBoolean);

  private native boolean _enableOutput1(String paramString, boolean paramBoolean);

  private native int _getCableState();

  private native int _getDisplayerCount();

  private native String _getDisplayerInfo(int paramInt);

  private native String _getDisplayerParam();

  private native String _getDisplayerParamStr(String paramString);

  private native String _getHdmiCap();

  private native boolean _getHdmiDisconnectFlag();

  private native int _getHdmiVid();

  private native int _getOutputDisplayer();

  private native String _getOutputDisplayerStr();

  private static native boolean _init();

  private native void _setDisplayMode(int paramInt);

  private native void _setDisplaySingleMode(int paramInt);

  private native boolean _setDisplayerParam(String paramString);

  private native boolean _setDualDisplay(boolean paramBoolean);

  private native void _setHdmiDisconnectFlag(boolean paramBoolean);

  private native void _setHdmiVid(int paramInt);

  private native boolean _setOutputDisplayer(int paramInt);

  private native boolean _setOutputDisplayer1(String paramString);

  private native void _setSwitchStatus(int paramInt);

  private void notifyStatusListener(String paramString1, String paramString2)
  {
    synchronized (this.mListeners)
    {
      int i = this.mListeners.size();
      int j = 0;
      while (true)
        if (j < i)
        {
          Listener localListener = (Listener)this.mListeners.get(j);
          try
          {
            localListener.mListener.onStatusChanged(paramString1, paramString2);
            j++;
          }
          catch (RemoteException localRemoteException)
          {
            while (true)
            {
              Log.w("DisplayService", "RemoteException in reportNmea");
              this.mListeners.remove(localListener);
            }
          }
        }
    }
  }

  public void addStatusListener(ICableStatusListener paramICableStatusListener)
    throws RemoteException
  {
    if (paramICableStatusListener == null)
      throw new NullPointerException("listener is null in addStatusListener");
    while (true)
    {
      int j;
      synchronized (this.mListeners)
      {
        IBinder localIBinder = paramICableStatusListener.asBinder();
        int i = this.mListeners.size();
        j = 0;
        if (j < i)
        {
          if (!localIBinder.equals(((Listener)this.mListeners.get(j)).mListener.asBinder()));
        }
        else
        {
          Listener localListener = new Listener(paramICableStatusListener);
          localIBinder.linkToDeath(localListener, 0);
          this.mListeners.add(localListener);
          return;
        }
      }
      j++;
    }
  }

  public boolean enableOutput(boolean paramBoolean)
  {
    try
    {
      boolean bool = _enableOutput(paramBoolean);
      return bool;
    }
    finally
    {
    }
  }

  public boolean enableOutput1(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = _enableOutput1(paramString, paramBoolean);
      return bool;
    }
    finally
    {
    }
  }

  public int getCableState()
  {
    try
    {
      int i = _getCableState();
      return i;
    }
    finally
    {
    }
  }

  public int getDisplayerCount()
  {
    return this.mDisplayerCount;
  }

  public String getDisplayerInfo(int paramInt)
  {
    return _getDisplayerInfo(paramInt);
  }

  public String getDisplayerParam()
  {
    return _getDisplayerParam();
  }

  public String getDisplayerParamStr(String paramString)
  {
    return _getDisplayerParamStr(paramString);
  }

  public String getHdmiCap()
  {
    try
    {
      String str = _getHdmiCap();
      return str;
    }
    finally
    {
    }
  }

  public boolean getHdmiDisconnectFlag()
  {
    Log.d("DisplayService", "[enter DisplayService.getHdmiDisconnectFlag]\n");
    try
    {
      boolean bool = _getHdmiDisconnectFlag();
      return bool;
    }
    finally
    {
    }
  }

  public int getHdmiVid()
  {
    Log.d("DisplayService", "[enter DisplayService.getDisplayVid]\n");
    try
    {
      int i = _getHdmiVid();
      return i;
    }
    finally
    {
    }
  }

  public int getOutputDisplayer()
  {
    try
    {
      int i = _getOutputDisplayer();
      return i;
    }
    finally
    {
    }
  }

  public String getOutputDisplayerStr()
  {
    try
    {
      String str = _getOutputDisplayerStr();
      return str;
    }
    finally
    {
    }
  }

  public void setDisplayMode(int paramInt)
  {
    Log.d("DisplayService", "[enter DisplayService.setDisplayMode]\n");
    try
    {
      _setDisplayMode(paramInt);
      return;
    }
    finally
    {
    }
  }

  public void setDisplaySingleMode(int paramInt)
  {
    Log.d("DisplayService", "[enter DisplayService.setDisplaySingleMode]\n");
    try
    {
      _setDisplaySingleMode(paramInt);
      return;
    }
    finally
    {
    }
  }

  public boolean setDisplayerParam(String paramString)
  {
    try
    {
      boolean bool = _setDisplayerParam(paramString);
      if ((paramString != null) && (paramString.indexOf("scale-x=") >= 0) && (paramString.indexOf("scale-y=") >= 0));
      return bool;
    }
    finally
    {
    }
  }

  public boolean setDualDisplay(boolean paramBoolean)
  {
    try
    {
      boolean bool = _setDualDisplay(paramBoolean);
      return bool;
    }
    finally
    {
    }
  }

  public void setHdmiDisconnectFlag(boolean paramBoolean)
  {
    Log.d("DisplayService", "[enter DisplayService.setHdmiDisconnectFlag]\n");
    try
    {
      _setHdmiDisconnectFlag(paramBoolean);
      return;
    }
    finally
    {
    }
  }

  public void setHdmiVid(int paramInt)
  {
    Log.d("DisplayService", "[enter DisplayService.setDisplayVid]\n");
    try
    {
      _setHdmiVid(paramInt);
      return;
    }
    finally
    {
    }
  }

  public boolean setOutputDisplayer(int paramInt)
  {
    try
    {
      boolean bool = _setOutputDisplayer(paramInt);
      return bool;
    }
    finally
    {
    }
  }

  public boolean setOutputDisplayer1(String paramString)
  {
    Log.d("DisplayService", "[enter setOutputDisplayer1]\n");
    try
    {
      boolean bool = _setOutputDisplayer1(paramString);
      if (this.onoffMode.equals("alwayson"))
      {
        if ((paramString.indexOf("cvbs") < 0) && (paramString.indexOf("hdmi") < 0))
          break label74;
        if (this.wakeLockFlag == 0)
        {
          this.mTvoutEventWakeLock.acquire();
          this.wakeLockFlag = 1;
        }
      }
      while (true)
      {
        return bool;
        label74: if (this.wakeLockFlag == 1)
        {
          this.mTvoutEventWakeLock.release();
          this.wakeLockFlag = 0;
        }
      }
    }
    finally
    {
    }
  }

  public void setSwitchStatus(int paramInt)
  {
    Log.d("DisplayService", "[enter DisplayService.setSwitchStatus]\n");
    try
    {
      _setSwitchStatus(paramInt);
      return;
    }
    finally
    {
    }
  }

  public void testInit()
  {
    try
    {
      _init();
      return;
    }
    finally
    {
    }
  }

  class CableMonitorThread extends Thread
  {
    public CableMonitorThread()
    {
      super();
    }

    private native void _cable_monitor();

    public void plugEvent(int paramInt)
    {
      Message localMessage = new Message();
      localMessage.what = 1;
      localMessage.arg1 = paramInt;
      Log.d("DisplayService", "Start to send message Pluged:" + localMessage);
      DisplayService.this.eventHander.sendMessage(localMessage);
    }

    public void run()
    {
      _cable_monitor();
    }
  }

  private final class CvbsReceiver extends BroadcastReceiver
  {
    private CvbsReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        if ("android.intent.action.CVBS_PLUGGED".equals(paramIntent.getAction()))
        {
          DisplayService.access$502(DisplayService.this, paramIntent.getBooleanExtra("state", false));
          if (DisplayService.this.onoffMode.equals("alwayson"))
          {
            if (!DisplayService.this.mCvbsPluggedIn)
              break label117;
            if (DisplayService.this.wakeLockFlag == 0)
            {
              DisplayService.this.mTvoutEventWakeLock.acquire();
              DisplayService.access$302(DisplayService.this, 1);
            }
          }
        }
        while (true)
        {
          Log.d("DisplayService", "here receive CVBS_PLUGGED broadcast,plug flag=" + DisplayService.this.mHdmiPluggedIn);
          return;
          label117: if (DisplayService.this.wakeLockFlag == 1)
          {
            DisplayService.this.mTvoutEventWakeLock.release();
            DisplayService.access$302(DisplayService.this, 0);
          }
        }
      }
      finally
      {
      }
    }
  }

  private final class HdmiReceiver extends BroadcastReceiver
  {
    private HdmiReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        if ("android.intent.action.HDMI_PLUGGED".equals(paramIntent.getAction()))
        {
          DisplayService.access$102(DisplayService.this, paramIntent.getBooleanExtra("state", false));
          if (DisplayService.this.onoffMode.equals("alwayson"))
          {
            if (!DisplayService.this.mHdmiPluggedIn)
              break label117;
            if (DisplayService.this.wakeLockFlag == 0)
            {
              DisplayService.this.mTvoutEventWakeLock.acquire();
              DisplayService.access$302(DisplayService.this, 1);
            }
          }
        }
        while (true)
        {
          Log.d("DisplayService", "here receive HDMI_PLUGGED broadcast,plug flag=" + DisplayService.this.mHdmiPluggedIn);
          return;
          label117: if (DisplayService.this.wakeLockFlag == 1)
          {
            DisplayService.this.mTvoutEventWakeLock.release();
            DisplayService.access$302(DisplayService.this, 0);
          }
        }
      }
      finally
      {
      }
    }
  }

  private final class Listener
    implements IBinder.DeathRecipient
  {
    final ICableStatusListener mListener;
    int mSensors = 0;

    Listener(ICableStatusListener arg2)
    {
      Object localObject;
      this.mListener = localObject;
    }

    public void binderDied()
    {
      Log.w("DisplayService", "DisplayManager status listener died");
      synchronized (DisplayService.this.mListeners)
      {
        DisplayService.this.mListeners.remove(this);
        if (this.mListener != null)
          this.mListener.asBinder().unlinkToDeath(this, 0);
        return;
      }
    }
  }

  private final class ScreenReceiver extends BroadcastReceiver
  {
    private ScreenReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        String str = paramIntent.getAction();
        if (str.equals("android.intent.action.SCREEN_ON"))
        {
          Log.d("DisplayService", "ACTION_SCREEN_ON");
          if ((!DisplayService.this.getHdmiDisconnectFlag()) && (!DisplayService.this.mVideoPlay))
            DisplayService.this.setSwitchStatus(1);
        }
        while (true)
        {
          return;
          if (str.equals("android.intent.action.SCREEN_OFF"))
          {
            Log.d("DisplayService", "ACTION_SCREEN_OFF");
            if ((!DisplayService.this.getHdmiDisconnectFlag()) && (!DisplayService.this.mVideoPlay))
              DisplayService.this.setSwitchStatus(0);
          }
        }
      }
      finally
      {
      }
    }
  }
}
