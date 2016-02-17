# Image Processing for Cordova / Ionic #

Authors: Bruno Oliveira, Marcus Oliveira

## Adding the Plugin ##

Use the Cordova CLI and type in the following command:

`cordova plugin add https://github.com/RealBrubru/cordova-plugin-image-processing.git`

To remove:

`cordova plugin rm cordova-plugin-image-processing`

## Methods

At the moment the plugin is only avaible on the android and ios platform.

### resize

    ImageProcessing.resize(success, failed, options);

#### options
  - **sourceUri**(String): The Uri for the image on the device to get scaled
  - **destinationUri**(String): The name of the image to be saved
  - **width**(Number): The width of the new image
  - **height**(Number): The height of the new image

##### example
    var options = {
          sourceUri: sourceUri,
          destinationUri: destinationUri,
          width: 800,
          height: 400
          };

    ImageProcessing.resize(function(success) {
         // success: image is the new resized image
      }, function(fail) {
        // failed: grumpy cat likes this function
      }, options);

