package pl.mqs.beword.db.util;

public class MockupHelper {
    public static String mockupPassword(String password) {
        if(password == null)
            return ModelConsts.UNSET;

        return ModelConsts.SET;
    }
}
