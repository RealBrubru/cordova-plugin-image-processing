var exec = require('cordova/exec');
var argscheck = require('cordova/argscheck');

var imageProcessing = new ImageProcessing();

function ImageProcessing() {
    console.log("ImageProcessing.js - is created");
}

imageProcessing.resize = function(successCallback, errorCallback, options) {
    console.log("ImageProcessing.js - resize: " + JSON.stringify(options));

    options = options || {};
    var getValue = argscheck.getValue;
    
    var sourceUri = options.sourceUri;
    var destinationUri = options.destinationUri;
    var width = getValue(options.width, -1);
    var height = getValue(options.height, -1);

    var args = [sourceUri, destinationUri, width, height];

    exec(successCallback, errorCallback, "ImageProcessing", "resize", args);
};


module.exports = imageProcessing;