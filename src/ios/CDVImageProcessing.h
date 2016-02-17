#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>

@interface CDVImageProcessing : CDVPlugin {}

- (void) resize:(CDVInvokedUrlCommand*)command;

@end