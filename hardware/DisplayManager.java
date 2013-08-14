package com.actions.hardware;

import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.StringTokenizer;

public class DisplayManager
{
  public static final int ASPECT_16VS9 = 1;
  public static final int ASPECT_4VS3 = 0;
  public static final int CABLE_CVBS_CONNECTED = 2;
  public static final int CABLE_HDMI_CONNECTED = 1;
  public static final int CABLE_YPBPR_CONNECTED = 4;
  public static final int DISPLAY_MODE_HIGH_BANDWIDTH = 7;
  public static final int DISPLAY_MODE_LOW_BANDWIDTH = 4;
  public static final int DISPLAY_MODE_MEDIUM_BANDWIDTH = 6;
  private static final int FLAG_LCD_HAS_VIDEO = 2;
  private static final int FLAG_MIN_BANDWIDTH = 4;
  private static final int FLAG_TVOUT_SIMULTANEOUS = 1;
  public static final int NON_PROGRESSIVE = 0;
  public static final int PROGRESSIVE = 1;
  public static final String SCALE_FULLSCREEN = "fullscreen";
  public static final String SCALE_ORIGIN = "origin";
  public static final String SCALE_UNIFORM = "uniform";
  private static final String TAG = "DisplayManager";
  private int mDisplayNum = 0;
  private IDisplayService mDisplayService = IDisplayService.Stub.asInterface(ServiceManager.getService("actions.display"));

  public DisplayManager()
  {
    if (this.mDisplayService != null);
    while (true)
    {
      try
      {
        Log.i("DisplayManager", this.mDisplayService.toString());
        this.mDisplayNum = this.mDisplayService.getDisplayerCount();
        Log.e("DisplayManager", "enter DisplayManager !");
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("DisplayManager", "RemoteException when get Number of Displayer", localRemoteException);
        this.mDisplayNum = 0;
        continue;
      }
      Log.e("DisplayManager", "error! can not get the display service!");
      this.mDisplayNum = 0;
    }
  }

  public boolean enableColorKey(boolean paramBoolean)
  {
    return setDisplayerParam("colorkey=" + paramBoolean);
  }

  public boolean enableOutput(boolean paramBoolean)
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return false;
    }
    try
    {
      boolean bool2 = this.mDisplayService.enableOutput(paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service enableOutput failed", localException);
        boolean bool1 = false;
      }
    }
  }

  public boolean enableOutput1(String paramString, boolean paramBoolean)
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return false;
    }
    try
    {
      boolean bool2 = this.mDisplayService.enableOutput1(paramString, paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service enableOutput1 failed", localException);
        boolean bool1 = false;
      }
    }
  }

  public int getCableState()
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed");
      return 0;
    }
    try
    {
      int j = this.mDisplayService.getCableState();
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service getCableState failed", localException);
        int i = 0;
      }
    }
  }

  public int getColor()
  {
    String str = getDisplayerParam();
    if (str != null)
    {
      ConfigInfo localConfigInfo = new ConfigInfo();
      localConfigInfo.unflatten(str);
      return localConfigInfo.color;
    }
    return 0;
  }

  public int getDisplayerCount()
  {
    return this.mDisplayNum;
  }

  public DisplayerInfo getDisplayerInfo(int paramInt)
  {
    if ((paramInt >= this.mDisplayNum) || (this.mDisplayService == null))
      return new DisplayerInfo();
    try
    {
      String str = this.mDisplayService.getDisplayerInfo(paramInt);
      DisplayerInfo localDisplayerInfo = new DisplayerInfo();
      localDisplayerInfo.unflatten(str);
      return localDisplayerInfo;
    }
    catch (Exception localException)
    {
    }
    return new DisplayerInfo();
  }

  public String getDisplayerParam()
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return null;
    }
    try
    {
      String str2 = this.mDisplayService.getDisplayerParam();
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service getDisplayerParam failed", localException);
        String str1 = null;
      }
    }
  }

  public String getDisplayerParam(String paramString)
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return null;
    }
    try
    {
      String str2 = this.mDisplayService.getDisplayerParamStr(paramString);
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service getDisplayerParam failed", localException);
        String str1 = null;
      }
    }
  }

  public String getHdmiCap()
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return null;
    }
    try
    {
      String str2 = this.mDisplayService.getHdmiCap();
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service getHdmiCap failed", localException);
        String str1 = null;
      }
    }
  }

  public boolean getHdmiParam(DisplayParam paramDisplayParam)
  {
    String str = getDisplayerParam();
    if ((str != null) && (paramDisplayParam != null))
    {
      ConfigInfo localConfigInfo = new ConfigInfo();
      localConfigInfo.unflatten(str);
      paramDisplayParam.width = localConfigInfo.hdmiResWidth;
      paramDisplayParam.height = localConfigInfo.hdmiResHeight;
      paramDisplayParam.hz = localConfigInfo.hdmiResHz;
      return true;
    }
    return false;
  }

  public int getHdmiVid()
  {
    Log.d("DisplayManager", "[enter DisplayManger.getHdmiVid]\n");
    try
    {
      int i = this.mDisplayService.getHdmiVid();
      return i;
    }
    catch (Exception localException)
    {
      Log.e("DisplayManager", "Display service getHdmiVid failed", localException);
    }
    return -1;
  }

  public int getOutputDisplayer()
  {
    int i = -1;
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return i;
    }
    try
    {
      int j = this.mDisplayService.getOutputDisplayer();
      i = j;
      label31: return i;
    }
    catch (Exception localException)
    {
      break label31;
    }
  }

  public String getOutputDisplayerStr()
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return null;
    }
    try
    {
      String str2 = this.mDisplayService.getOutputDisplayerStr();
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service getOutputDisplayerStr failed", localException);
        String str1 = null;
      }
    }
  }

  public String getScaleMode()
  {
    String str = getDisplayerParam();
    if (str != null)
    {
      ConfigInfo localConfigInfo = new ConfigInfo();
      localConfigInfo.unflatten(str);
      return localConfigInfo.scale;
    }
    return "";
  }

  public boolean getTvDisplayScale(ScaleInfo paramScaleInfo)
  {
    String str = getDisplayerParam();
    if ((str != null) && (paramScaleInfo != null))
    {
      ConfigInfo localConfigInfo = new ConfigInfo();
      localConfigInfo.unflatten(str);
      paramScaleInfo.scale_x = localConfigInfo.scale_x;
      paramScaleInfo.scale_y = localConfigInfo.scale_y;
      return true;
    }
    return false;
  }

  public boolean getYpbprParam(DisplayParam paramDisplayParam)
  {
    String str = getDisplayerParam();
    if ((str != null) && (paramDisplayParam != null))
    {
      ConfigInfo localConfigInfo = new ConfigInfo();
      localConfigInfo.unflatten(str);
      paramDisplayParam.width = localConfigInfo.ypbprResWidth;
      paramDisplayParam.height = localConfigInfo.ypbprResHeight;
      paramDisplayParam.hz = localConfigInfo.ypbprResHz;
      return true;
    }
    return false;
  }

  public boolean setColor(int paramInt)
  {
    return setDisplayerParam("color=" + paramInt);
  }

  public boolean setDisplayMode(int paramInt)
  {
    Log.d("DisplayManager", "[enter DisplayManger.setDisplayMode]\n");
    try
    {
      this.mDisplayService.setDisplayMode(paramInt);
      return true;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("DisplayManager", "Display service setDisplayerMode failed", localException);
    }
  }

  public boolean setDisplaySingleMode(int paramInt)
  {
    Log.d("DisplayManager", "[enter DisplayManger.setDisplaySingleMode]\n");
    try
    {
      this.mDisplayService.setDisplaySingleMode(paramInt);
      return true;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("DisplayManager", "Display service setDisplayerSingleMode failed", localException);
    }
  }

  public boolean setDisplayerParam(String paramString)
  {
    Log.e("DisplayManager", "enter setDisplayerParam!");
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return false;
    }
    try
    {
      boolean bool = this.mDisplayService.setDisplayerParam(paramString);
      return bool;
    }
    catch (Exception localException)
    {
      Log.e("DisplayManager", "Display service setDisplayerParam failed", localException);
    }
    return false;
  }

  public boolean setDualDisplay(boolean paramBoolean)
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return false;
    }
    try
    {
      boolean bool2 = this.mDisplayService.setDualDisplay(paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("DisplayManager", "Display service setDualDisplay failed", localException);
        boolean bool1 = false;
      }
    }
  }

  public boolean setFormat(String paramString)
  {
    return setDisplayerParam("format=" + paramString);
  }

  public void setHdmiDisconnectFlag(boolean paramBoolean)
  {
    Log.d("DisplayManager", "[enter DisplayManger.setHdmiDisconnectFlag]\n");
    try
    {
      this.mDisplayService.setHdmiDisconnectFlag(paramBoolean);
      return;
    }
    catch (Exception localException)
    {
      Log.e("DisplayManager", "Display service setHdmiDisconnectFlag failed", localException);
    }
  }

  public boolean setHdmiParam(int paramInt1, int paramInt2, float paramFloat)
  {
    return setHdmiParam(paramInt1, paramInt2, paramFloat, -1, -1);
  }

  public boolean setHdmiParam(int paramInt1, int paramInt2, float paramFloat, int paramInt3)
  {
    return setHdmiParam(paramInt1, paramInt2, paramFloat, paramInt3, -1);
  }

  public boolean setHdmiParam(int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4)
  {
    return setDisplayerParam("hdmi-res-width=" + paramInt1 + ";" + "hdmi-res-height" + "=" + paramInt2 + ";" + "hdmi-res-hz" + "=" + paramFloat + ";" + "hdmi-res-pg" + "=" + paramInt3 + ";" + "hdmi-res-aspect" + "=" + paramInt4);
  }

  public boolean setHdmiVid(int paramInt)
  {
    Log.d("DisplayManager", "[enter DisplayManger.setHdmiVid]\n");
    try
    {
      this.mDisplayService.setHdmiVid(paramInt);
      return true;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("DisplayManager", "Display service setHdmiVid failed", localException);
    }
  }

  public boolean setOpacity(int paramInt)
  {
    return setDisplayerParam("alpha=" + paramInt);
  }

  public boolean setOutputDisplayer(int paramInt)
  {
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return false;
    }
    try
    {
      boolean bool2 = this.mDisplayService.setOutputDisplayer(paramInt);
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean setOutputDisplayer(String paramString)
  {
    Log.d("DisplayManager", "[enter DisplayManger.setOutputDisplayer]\n");
    if (this.mDisplayService == null)
    {
      Log.e("DisplayManager", "Display service  connected failed or mDisplayNum==0!");
      return false;
    }
    try
    {
      boolean bool = this.mDisplayService.setOutputDisplayer1(paramString);
      return bool;
    }
    catch (Exception localException)
    {
      Log.e("DisplayManager", "Display service setOutputDisplayer failed", localException);
    }
    return false;
  }

  public boolean setScaleMode(String paramString)
  {
    return setDisplayerParam("scale=" + paramString);
  }

  public void setStatusListener(IStatusListener paramIStatusListener)
  {
    if ((this.mDisplayService == null) || (paramIStatusListener == null))
    {
      Log.e("DisplayManager", "setStatusListener invliad argument listener is null ");
      return;
    }
    try
    {
      this.mDisplayService.addStatusListener(new StatusListenerImp(paramIStatusListener));
      return;
    }
    catch (Exception localException)
    {
      Log.e("DisplayManager", "Display service getCableState failed", localException);
    }
  }

  public boolean setTvDisplayScale(int paramInt1, int paramInt2)
  {
    return setDisplayerParam("scale-x=" + paramInt1 + ";" + "scale-y" + "=" + paramInt2);
  }

  public boolean setYpbprParam(int paramInt1, int paramInt2, float paramFloat)
  {
    return setYpbprParam(paramInt1, paramInt2, paramFloat, -1, -1);
  }

  public boolean setYpbprParam(int paramInt1, int paramInt2, float paramFloat, int paramInt3)
  {
    return setYpbprParam(paramInt1, paramInt2, paramFloat, paramInt3, -1);
  }

  public boolean setYpbprParam(int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4)
  {
    return setDisplayerParam("ypbpr-res-width=" + paramInt1 + ";" + "ypbpr-res-height" + "=" + paramInt2 + ";" + "ypbpr-res-hz" + "=" + paramFloat + ";" + "ypbpr-res-pg" + "=" + paramInt3 + ";" + "ypbpr-res-aspect" + "=" + paramInt4);
  }

  public void testInit()
  {
    try
    {
      this.mDisplayService.testInit();
      return;
    }
    catch (Exception localException)
    {
      Log.e("DisplayManager", "error! mDisplayService.testInit() fail");
    }
  }

  public class ConfigInfo
  {
    public static final String KEY_ALPHA = "alpha";
    public static final String KEY_AUDIO_CHAN = "audio-chan";
    public static final String KEY_COLOR = "color";
    public static final String KEY_COLORKEY = "colorkey";
    public static final String KEY_FORMAT = "format";
    public static final String KEY_HDMI_RES_ASPECT = "hdmi-res-aspect";
    public static final String KEY_HDMI_RES_HEIGHT = "hdmi-res-height";
    public static final String KEY_HDMI_RES_HZ = "hdmi-res-hz";
    public static final String KEY_HDMI_RES_PG = "hdmi-res-pg";
    public static final String KEY_HDMI_RES_WIDTH = "hdmi-res-width";
    public static final String KEY_SCALE_X = "scale-x";
    public static final String KEY_SCALE_Y = "scale-y";
    public static final String KEY_VIDEO_SCALE = "scale";
    public static final String KEY_YPBPR_RES_ASPECT = "ypbpr-res-aspect";
    public static final String KEY_YPBPR_RES_HEIGHT = "ypbpr-res-height";
    public static final String KEY_YPBPR_RES_HZ = "ypbpr-res-hz";
    public static final String KEY_YPBPR_RES_PG = "ypbpr-res-pg";
    public static final String KEY_YPBPR_RES_WIDTH = "ypbpr-res-width";
    public int alpha;
    public String audio_chan;
    public int color;
    public boolean colorkey;
    public String format;
    public int hdmiResAspect;
    public int hdmiResHeight;
    public float hdmiResHz;
    public int hdmiResPg;
    public int hdmiResWidth;
    public String scale;
    public int scale_x;
    public int scale_y;
    public int ypbprResAspect;
    public int ypbprResHeight;
    public float ypbprResHz;
    public int ypbprResPg;
    public int ypbprResWidth;

    public ConfigInfo()
    {
    }

    public void unflatten(String paramString)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ";");
      while (localStringTokenizer.hasMoreElements())
      {
        String str1 = localStringTokenizer.nextToken();
        int i = str1.indexOf('=');
        if (i != -1)
        {
          String str2 = str1.substring(0, i);
          String str3 = str1.substring(i + 1);
          if (str2.equals("format"))
            this.format = str3;
          else if (str2.equals("audio-chan"))
            this.audio_chan = str3;
          else if (str2.equals("scale"))
            this.scale = str3;
          else if (str2.equals("colorkey"))
            this.colorkey = Boolean.parseBoolean(str3);
          else if (str2.equals("color"))
            this.color = Integer.parseInt(str3);
          else if (str2.equals("alpha"))
            this.alpha = Integer.parseInt(str3);
          else if (str2.equals("hdmi-res-width"))
            this.hdmiResWidth = Integer.parseInt(str3);
          else if (str2.equals("hdmi-res-height"))
            this.hdmiResHeight = Integer.parseInt(str3);
          else if (str2.equals("hdmi-res-hz"))
            this.hdmiResHz = Float.parseFloat(str3);
          else if (str2.equals("hdmi-res-pg"))
            this.hdmiResPg = Integer.parseInt(str3);
          else if (str2.equals("hdmi-res-aspect"))
            this.hdmiResAspect = Integer.parseInt(str3);
          else if (str2.equals("scale-x"))
            this.scale_x = Integer.parseInt(str3);
          else if (str2.equals("scale-y"))
            this.scale_y = Integer.parseInt(str3);
          else if (str2.equals("ypbpr-res-width"))
            this.ypbprResWidth = Integer.parseInt(str3);
          else if (str2.equals("ypbpr-res-height"))
            this.ypbprResHeight = Integer.parseInt(str3);
          else if (str2.equals("ypbpr-res-hz"))
            this.ypbprResHz = Float.parseFloat(str3);
          else if (str2.equals("ypbpr-res-pg"))
            this.ypbprResPg = Integer.parseInt(str3);
          else if (str2.equals("ypbpr-res-aspect"))
            this.ypbprResAspect = Integer.parseInt(str3);
          else
            Log.w("DisplayManager", "unknown display info" + str2 + str3);
        }
      }
    }
  }

  public static class DisplayParam
  {
    public int height;
    public float hz;
    public int width;
  }

  public class DisplayerInfo
  {
    private static final String KEY_DES = "description";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_WIDTH = "width";
    public String mDes;
    public int mHeight;
    public int mId;
    public String mName;
    public int mWidth;

    public DisplayerInfo()
    {
    }

    public void unflatten(String paramString)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ";");
      while (localStringTokenizer.hasMoreElements())
      {
        String str1 = localStringTokenizer.nextToken();
        int i = str1.indexOf('=');
        if (i != -1)
        {
          String str2 = str1.substring(0, i);
          String str3 = str1.substring(i + 1);
          if (str2.equals("width"))
            this.mWidth = Integer.parseInt(str3);
          else if (str2.equals("height"))
            this.mHeight = Integer.parseInt(str3);
          else if (str2.equals("name"))
            this.mName = str3;
          else if (str2.equals("description"))
            this.mDes = str3;
          else if (str2.equals("id"))
            this.mId = Integer.parseInt(str3);
          else
            Log.w("DisplayManager", "unknown display info" + str2 + str3);
        }
      }
    }
  }

  public static abstract interface IStatusListener
  {
    public abstract void onStatusChanged(String paramString1, String paramString2);
  }

  public static class ScaleInfo
  {
    public static final int MAX_SCALE_X = 75;
    public static final int MAX_SCALE_Y = 75;
    public int scale_x;
    public int scale_y;
  }

  private class StatusListenerImp extends ICableStatusListener.Stub
  {
    private DisplayManager.IStatusListener mListener;

    StatusListenerImp(DisplayManager.IStatusListener arg2)
    {
      Object localObject;
      this.mListener = localObject;
    }

    public void onStatusChanged(String paramString1, String paramString2)
    {
      Log.d("DisplayManager", "StatusListener displayer= " + paramString1 + "  status= " + paramString2);
    }
  }
}
