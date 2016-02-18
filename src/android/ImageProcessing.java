package org.apache.cordova.imageprocessing;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class ImageProcessing extends CordovaPlugin {
    
    public static final String LOG_TAG = "ImageProcessing";
    
    private void resize(sourceUri, destinationUri, newWidth, newHeight, keepScale)
    {
		if (newWidth > 0 && newHeight > 0) {
        int width = image.getWidth();
        int height = image.getHeight();
        int finalWidth = width;
        int finalHeight = height;

    		if(keepScale)
    		{
		        //Log.d("autocrop", width + " x " + height);
		        float scaleFactor = (width > height) ? (float)maxWidth / width : (float)maxHeight / height;
            //Log.d("autocrop", "" + scaleFactor);
            finalWidth = (int)(width * scaleFactor);
		        finalHeight = (int)(height * scaleFactor);
	        }

	        image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
	        return image;
	    } else {
	        return image;
	    }
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(LOG_TAG, "action: " + action);
        Log.d(LOG_TAG, "args: " + args.toString());
        
	if (action.equals("resize")) {
		final String sourceUri = (String) args.get(0);
		final String destinationUri = (String) args.get(1);
		final int newWidth = (int) args.get(2);
		final int newHeight = (int) args.get(3);
		final boolean keepScale = (boolean) args.get(4);
	}
	else if (action.equals("rotate")) {
	}
	else if (action.equals("crop")) {
	}

        callbackContext.success();
        return true;
    }

}