package com.actions.hardware;

import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;

public class PerformanceManager
{
  private static final String TAG = "PerformanceManager";
  private IPerformanceService mPerformanceService = IPerformanceService.Stub.asInterface(ServiceManager.getService("performanceservice"));

  public PerformanceManager()
  {
    if (this.mPerformanceService == null)
      Log.e("PerformanceManager", "error! can not get PerformanceService!");
  }

  public boolean cleanAllVmCaches()
  {
    try
    {
      boolean bool = this.mPerformanceService.cleanAllVmCaches();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean clearAlign()
  {
    try
    {
      boolean bool = this.mPerformanceService.clearAlign();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean clearOpt()
  {
    try
    {
      boolean bool = this.mPerformanceService.clearOpt();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean disableAutoAdjustBacklight()
  {
    try
    {
      boolean bool = this.mPerformanceService.disableAutoAdjustBacklight();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean enbleAutoAdjustBacklight()
  {
    try
    {
      boolean bool = this.mPerformanceService.enbleAutoAdjustBacklight();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean getMaxPerformance()
  {
    try
    {
      boolean bool = this.mPerformanceService.getMaxPerformance();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean notifier(int paramInt)
  {
    try
    {
      boolean bool = this.mPerformanceService.notifier(paramInt);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean putMaxPerformance()
  {
    try
    {
      boolean bool = this.mPerformanceService.putMaxPerformance();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean restoreCpuFreqRange(IBinder paramIBinder)
  {
    try
    {
      boolean bool = this.mPerformanceService.restoreCpuFreqRange(paramIBinder);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean setAlign()
  {
    try
    {
      boolean bool = this.mPerformanceService.setAlign();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean setCpuFreqRange(IBinder paramIBinder, int paramInt1, int paramInt2)
  {
    try
    {
      boolean bool = this.mPerformanceService.setCpuFreqRange(paramIBinder, paramInt1, paramInt2);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean setOpt()
  {
    try
    {
      boolean bool = this.mPerformanceService.setOpt();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean singleThreadAccelerate(int paramInt)
  {
    try
    {
      boolean bool = this.mPerformanceService.singleThreadAccelerate(paramInt);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
}
