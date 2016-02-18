package org.apache.cordova.imageprocessing;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class ImageProcessing extends CordovaPlugin {
    
    public static final String LOG_TAG = "ImageProcessing";
    
    private void resize(Bitmap image, String destinationUri, int newWidth, int newHeight, boolean keepScale)
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
    
    private void saveImage(String destinationUri)
    {
      //TODO: Support the full path
      File folder = new File(Environment.getExternalStorageDirectory(), "Pictures");
      if (!folder.exists()) {
        folder.mkdirs();
      }
      
      File f = new File(folder, destinationUri);

      FileOutputStream fos = new FileOutputStream(f);
      if(format.equals("png")){
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
      }
      if(format.equals("jpg")){
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality == null?100:quality, fos);
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

          super.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
              //TODO: Validate if image exists
              //TODO: Image quality
              Bitmap newImage = BitmapFactory.decodeFile(sourceUri);
              
              resize(image, , newWidth, newHeight, keepScale);
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