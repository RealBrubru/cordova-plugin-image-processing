#import <Cordova/CDV.h>
#import "CDVImageProcessing.h"

@implementation CDVImageProcessing

- (void) resize:(CDVInvokedUrlCommand *)command
{
    NSString *sourceUri = [command argumentAtIndex: 0];
    NSString *destinationUri = [command argumentAtIndex: 1];
    NSNumber *width = [command argumentAtIndex: 2];
    NSNumber *height = [command argumentAtIndex: 3];
    BOOL keepScale = [command argumentAtIndex: 4];
    
    NSLog(@"CDVImageProcessing AAAAAAAHHH");
}


- (void) rotate:(CDVInvokedUrlCommand *)command
{
    NSLog(@"CDVImageProcessing AAAAAAAHHH");
}

- (void) crop:(CDVInvokedUrlCommand *)command
{
    NSLog(@"CDVImageProcessing AAAAAAAHHH");
}

@end