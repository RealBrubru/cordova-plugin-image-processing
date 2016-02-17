package org.apache.cordova.imageprocessing;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class ImageProcessing extends CordovaPlugin {
    
    public static final String LOG_TAG = "ImageProcessing";
    
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(LOG_TAG, "action: " + action);
        Log.d(LOG_TAG, "args: " + args.toString());
        
        callbackContext.success();
        return true;
    }

}