package org.apache.cordova.imageprocessing;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ImageProcessing extends CordovaPlugin {
    
    public static final String LOG_TAG = "ImageProcessing";
    public CallbackContext callbackContext;
    
    private Bitmap resize(Bitmap image, int newWidth, int newHeight, boolean keepScale)
    {
		if (newWidth > 0 && newHeight > 0) {
        int width = image.getWidth();
        int height = image.getHeight();
        int finalWidth = width;
        int finalHeight = height;

    		/*if(keepScale)
    		{
		        //Log.d("autocrop", width + " x " + height);
		        float scaleFactor = (width > height) ? (float)maxWidth / width : (float)maxHeight / height;
            //Log.d("autocrop", "" + scaleFactor);
            finalWidth = (int)(width * scaleFactor);
		        finalHeight = (int)(height * scaleFactor);
	        }*/

	        image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
	        return image;
	    } else {
	        return image;
	    }
    }
    
    private void saveImage(Bitmap image, String destinationUri)
    {
      //TODO: Support the full path
      File folder = new File(Environment.getExternalStorageDirectory(), "Pictures");
      if (!folder.exists()) {
        folder.mkdirs();
      }
      
      File f = new File(folder, destinationUri);

      FileOutputStream fos = new FileOutputStream(f);
      image.compress(Bitmap.CompressFormat.PNG, 100, fos);
      
      JSONObject jsonRes = new JSONObject();
      jsonRes.put("filePath",f.getAbsolutePath());
      PluginResult result = new PluginResult(PluginResult.Status.OK, jsonRes);
      callbackContext.sendPluginResult(result);
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(LOG_TAG, "action: " + action);
        Log.d(LOG_TAG, "args: " + args.toString());
        
        this.callbackContext = callbackContext;
        
        if (action.equals("resize")) {
          final String sourceUri = (String) args.get(0);
          final String destinationUri = (String) args.get(1);
          final int newWidth = args.getInt(2);
          final int newHeight = args.getInt(3);
          final boolean keepScale = args.getBoolean(4);

          super.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
              //TODO: Validate if image exists
              //TODO: Image quality
              Bitmap image = BitmapFactory.decodeFile(sourceUri);
              
              Bitmap newImage = resize(image, newWidth, newHeight, keepScale);
              
              saveImage(newImage, destinationUri);
            }
          });
          
          return;
        }
        else if (action.equals("rotate")) {
        }
        else if (action.equals("crop")) {
        }

        callbackContext.success();
        return true;
    }

}