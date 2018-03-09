package com.udacity.gradle.builditbigger;

/**
 * Interface to be implemented by any fragment showing interstitial ads
 *
 *  Activity can interact with fragment accordingly depending whether is implementing this interface
 */

public interface InterstitialAdProvider {
    void showInterstitialAd();
    boolean isAdShowing();
}
