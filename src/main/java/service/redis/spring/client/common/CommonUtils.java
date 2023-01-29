package service.redis.spring.client.common;

public class CommonUtils {
    public static boolean isStringEmpty(String str){
        return (str == null || str == "");
    }
}
