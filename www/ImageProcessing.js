var exec = require('cordova/exec');
var argscheck = require('cordova/argscheck');

var imageProcessing = new ImageProcessing();

function ImageProcessing() {
    console.log("ImageProcessing.js - is created");
}

imageProcessing.resize = function (successCallback, errorCallback, options) {
    console.log("ImageProcessing.js - resize: " + JSON.stringify(options));

    options = options || {};
    var getValue = argscheck.getValue;

    var sourceUri = options.sourceUri;
    var destinationUri = options.destinationUri;
    var width = options.newWidth;
    var height = options.newHeight;
    var keepScale = getValue(options.keepScale, false);

    var args = [sourceUri, destinationUri, width, height, keepScale];

    exec(successCallback, errorCallback, "ImageProcessing", "resize", args);
};

imageProcessing.rotate = function (successCallback, errorCallback, options) {
    console.log("ImageProcessing.js - rotate: " + JSON.stringify(options));

    options = options || {};
    var getValue = argscheck.getValue;

    var sourceUri = options.sourceUri;
    var destinationUri = options.destinationUri;
    var angle = getValue(options.angle, 90);

    var args = [sourceUri, destinationUri, angle];

    exec(successCallback, errorCallback, "ImageProcessing", "rotate", args);
};

imageProcessing.crop = function (successCallback, errorCallback, options) {
    console.log("ImageProcessing.js - crop: " + JSON.stringify(options));

    options = options || {};

    var sourceUri = options.sourceUri;
    var destinationUri = options.destinationUri;
    var rect = options.rect;

    var args = [sourceUri, destinationUri, rect];

    exec(successCallback, errorCallback, "ImageProcessing", "crop", args);
};

module.exports = imageProcessing;