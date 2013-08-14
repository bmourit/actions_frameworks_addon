package com.actions.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IPerformanceService extends IInterface
{
  public abstract boolean cleanAllVmCaches()
    throws RemoteException;

  public abstract boolean clearAlign()
    throws RemoteException;

  public abstract boolean clearOpt()
    throws RemoteException;

  public abstract boolean disableAutoAdjustBacklight()
    throws RemoteException;

  public abstract boolean enbleAutoAdjustBacklight()
    throws RemoteException;

  public abstract boolean getMaxPerformance()
    throws RemoteException;

  public abstract boolean notifier(int paramInt)
    throws RemoteException;

  public abstract boolean putMaxPerformance()
    throws RemoteException;

  public abstract boolean restoreCpuFreqRange(IBinder paramIBinder)
    throws RemoteException;

  public abstract boolean setAlign()
    throws RemoteException;

  public abstract boolean setCpuFreqRange(IBinder paramIBinder, int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract boolean setOpt()
    throws RemoteException;

  public abstract boolean singleThreadAccelerate(int paramInt)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IPerformanceService
  {
    private static final String DESCRIPTOR = "com.actions.hardware.IPerformanceService";
    static final int TRANSACTION_cleanAllVmCaches = 4;
    static final int TRANSACTION_clearAlign = 10;
    static final int TRANSACTION_clearOpt = 13;
    static final int TRANSACTION_disableAutoAdjustBacklight = 8;
    static final int TRANSACTION_enbleAutoAdjustBacklight = 7;
    static final int TRANSACTION_getMaxPerformance = 1;
    static final int TRANSACTION_notifier = 11;
    static final int TRANSACTION_putMaxPerformance = 2;
    static final int TRANSACTION_restoreCpuFreqRange = 6;
    static final int TRANSACTION_setAlign = 9;
    static final int TRANSACTION_setCpuFreqRange = 5;
    static final int TRANSACTION_setOpt = 12;
    static final int TRANSACTION_singleThreadAccelerate = 3;

    public Stub()
    {
      attachInterface(this, "com.actions.hardware.IPerformanceService");
    }

    public static IPerformanceService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.actions.hardware.IPerformanceService");
      if ((localIInterface != null) && ((localIInterface instanceof IPerformanceService)))
        return (IPerformanceService)localIInterface;
      return new Proxy(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.actions.hardware.IPerformanceService");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool13 = getMaxPerformance();
        paramParcel2.writeNoException();
        int i8 = 0;
        if (bool13)
          i8 = 1;
        paramParcel2.writeInt(i8);
        return true;
      case 2:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool12 = putMaxPerformance();
        paramParcel2.writeNoException();
        int i7 = 0;
        if (bool12)
          i7 = 1;
        paramParcel2.writeInt(i7);
        return true;
      case 3:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool11 = singleThreadAccelerate(paramParcel1.readInt());
        paramParcel2.writeNoException();
        int i6 = 0;
        if (bool11)
          i6 = 1;
        paramParcel2.writeInt(i6);
        return true;
      case 4:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool10 = cleanAllVmCaches();
        paramParcel2.writeNoException();
        int i5 = 0;
        if (bool10)
          i5 = 1;
        paramParcel2.writeInt(i5);
        return true;
      case 5:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool9 = setCpuFreqRange(paramParcel1.readStrongBinder(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        int i4 = 0;
        if (bool9)
          i4 = 1;
        paramParcel2.writeInt(i4);
        return true;
      case 6:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool8 = restoreCpuFreqRange(paramParcel1.readStrongBinder());
        paramParcel2.writeNoException();
        int i3 = 0;
        if (bool8)
          i3 = 1;
        paramParcel2.writeInt(i3);
        return true;
      case 7:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool7 = enbleAutoAdjustBacklight();
        paramParcel2.writeNoException();
        int i2 = 0;
        if (bool7)
          i2 = 1;
        paramParcel2.writeInt(i2);
        return true;
      case 8:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool6 = disableAutoAdjustBacklight();
        paramParcel2.writeNoException();
        int i1 = 0;
        if (bool6)
          i1 = 1;
        paramParcel2.writeInt(i1);
        return true;
      case 9:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool5 = setAlign();
        paramParcel2.writeNoException();
        int n = 0;
        if (bool5)
          n = 1;
        paramParcel2.writeInt(n);
        return true;
      case 10:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool4 = clearAlign();
        paramParcel2.writeNoException();
        int m = 0;
        if (bool4)
          m = 1;
        paramParcel2.writeInt(m);
        return true;
      case 11:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool3 = notifier(paramParcel1.readInt());
        paramParcel2.writeNoException();
        int k = 0;
        if (bool3)
          k = 1;
        paramParcel2.writeInt(k);
        return true;
      case 12:
        paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
        boolean bool2 = setOpt();
        paramParcel2.writeNoException();
        int j = 0;
        if (bool2)
          j = 1;
        paramParcel2.writeInt(j);
        return true;
      case 13:
      }
      paramParcel1.enforceInterface("com.actions.hardware.IPerformanceService");
      boolean bool1 = clearOpt();
      paramParcel2.writeNoException();
      int i = 0;
      if (bool1)
        i = 1;
      paramParcel2.writeInt(i);
      return true;
    }

    private static class Proxy
      implements IPerformanceService
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public boolean cleanAllVmCaches()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean clearAlign()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean clearOpt()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean disableAutoAdjustBacklight()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean enbleAutoAdjustBacklight()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String getInterfaceDescriptor()
      {
        return "com.actions.hardware.IPerformanceService";
      }

      public boolean getMaxPerformance()
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
            return bool;
          bool = false;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean notifier(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean putMaxPerformance()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean restoreCpuFreqRange(IBinder paramIBinder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          localParcel1.writeStrongBinder(paramIBinder);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setAlign()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setCpuFreqRange(IBinder paramIBinder, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          localParcel1.writeStrongBinder(paramIBinder);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setOpt()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean singleThreadAccelerate(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.actions.hardware.IPerformanceService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}
