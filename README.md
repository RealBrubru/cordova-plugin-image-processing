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
  - **sourceUri** (String): The Uri for the image on the device to get scaled
  - **destinationUri** (String): The name of the image to be saved
  - **newWidth** (Number): Width of the new resized image
  - **newHeight** (Number): Height of the new resize image

##### example
    var options = {
          sourceUri: sourceUri,
          destinationUri: destinationUri,
          newWidth: 800,
          newHeight: 400
          };

    ImageProcessing.resize(function(success) {
         // success: 
      }, function(fail) {
        // failed: 
      }, options);

### rotate

    ImageProcessing.rotate(success, failed, options);

#### options
  - **sourceUri** (String): The Uri for the image on the device to get scaled
  - **destinationUri** (String): The name of the image to be saved
  - **angle** (Number): Rotation angle
  - **keepSize** (Bool): Keep image size or not

##### example
    var options = {
          sourceUri: sourceUri,
          destinationUri: destinationUri,
          angle: 30,
          keepSize: true
          };

    ImageProcessing.rotate(function(success) {
         // success: 
      }, function(fail) {
        // failed: 
      }, options);

