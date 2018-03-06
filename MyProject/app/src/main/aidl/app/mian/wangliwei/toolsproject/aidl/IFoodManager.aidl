// IFoodManager.aidl
package app.mian.wangliwei.toolsproject.aidl;

// Declare any non-default types here with import statements

interface IFoodManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int getFoodNum();
    void addFoodNum(int num);
}
