package org.apache.cordova.imageprocessing;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

public class ImageProcessing extends CordovaPlugin {
    
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        callbackContext.success();
        return true;
    }
}