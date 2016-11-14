// Matthew Krawczyk
// Period 4 APCS
// 11/10/16
package com.Mattk4355;

public class CookieCanister {
    private int numCookies;
    private final int maxCookies;
    private boolean isOpen;
    private final String typeCookies;

    CookieCanister(int MaxCookies, String TypeCookies){
        numCookies = 0;
        isOpen = false;
        maxCookies = MaxCookies;
        typeCookies = TypeCookies;
    }
    public void removeCookies(int cookies){
        if (cookies > numCookies){
            numCookies = 0;
            return;
        }
        numCookies -= cookies;
    }
    public void addCookies(int cookies){
        if ((numCookies + cookies) >= maxCookies){
            numCookies = maxCookies;
            return;
        }
        numCookies += cookies;
    }
    public void addMaxCookies(){
        numCookies = maxCookies;
    }
    public void setOpen(boolean IsOpen){
        isOpen = IsOpen;
    }
    public String getTypeCookies() {
        return typeCookies;
    }
    public int getMaxCookies() {
        return maxCookies;
    }
    public int getNumCookies() {
        return numCookies;
    }
    public boolean getIsOpen(){
        return isOpen;
    }
    public String GetAllAttributes(){
        return ("Is Open:" + isOpen + " Num cookies: " + numCookies + " Max cookies: " + maxCookies + " Type of cookies: " + typeCookies);
    }
}