package client;

public class TokenManager {

    private static String token;

    public static void setToken(String token) {
        TokenManager.token = token;
    }

    public static String getToken() {
        return TokenManager.token;
    }

}
