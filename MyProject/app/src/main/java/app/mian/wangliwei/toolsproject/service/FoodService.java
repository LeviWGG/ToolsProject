package app.mian.wangliwei.toolsproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import app.mian.wangliwei.toolsproject.aidl.IFoodManager;

public class FoodService extends Service {
    private int mFoodNum = 10;

    public FoodService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    private Binder mBinder = new IFoodManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getFoodNum() throws RemoteException {
            return mFoodNum;
        }

        @Override
        public void addFoodNum(int num) throws RemoteException {
            mFoodNum += num;
        }
    };

}
