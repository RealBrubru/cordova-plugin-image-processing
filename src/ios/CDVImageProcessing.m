#import <Cordova/CDV.h>
#import "CDVImageProcessing.h"

@implementation CDVImageProcessing

- (void) resize:(CDVInvokedUrlCommand *)command
{
    NSLog(@"CDVImageProcessing - resize() - start");

    NSString *sourceUri = [command argumentAtIndex: 0];
    NSString *destinationUri = [command argumentAtIndex: 1];
    NSNumber *width = [command argumentAtIndex: 2];
    NSNumber *height = [command argumentAtIndex: 3];
    BOOL keepScale = [[command argumentAtIndex:4 withDefault:[NSNumber numberWithBool:NO]] boolValue];

    UIImage *originalImage = [self loadImage:sourceUri];
    if (!originalImage) {
        NSLog(@"CDVImageProcessing - resize() - Original image not loaded!!");
        return;
    }

    UIImage *resizedImage = [self resizeImage:originalImage toSize:CGSizeMake([width floatValue], [height floatValue]) andKeepScale:keepScale];

    [self saveImage:resizedImage toFilePath:destinationUri];

    CDVPluginResult* result = [CDVPluginResult resultWithStatus: CDVCommandStatus_OK messageAsString:@"Image resized"];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];

    NSLog(@"CDVImageProcessing - resize() - stop");
}

- (void) rotate:(CDVInvokedUrlCommand *)command
{
    NSLog(@"CDVImageProcessing AAAAAAAHHH");
}

- (void) crop:(CDVInvokedUrlCommand *)command
{
    NSLog(@"CDVImageProcessing AAAAAAAHHH");
}




- (UIImage *)resizeImage:(UIImage *)originalImage toSize:(CGSize)size andKeepScale:(BOOL)keepScale {
    CGImageRef cgOriginalImage = originalImage.CGImage;

    NSLog(@"CDVImageProcessing - resizeImage() - image size (%fX%f)", originalImage.size.width, originalImage.size.height);
    NSLog(@"CDVImageProcessing - resizeImage() - desired size (%fX%f)", size.width, size.height);
    NSLog(@"CDVImageProcessing - resizeImage() - keepScale: %hhd", keepScale);
       
    if (keepScale) {
        size = [self estimatedScaleSize:size forImage:originalImage];
    }

    size_t bitsPerComponent = CGImageGetBitsPerComponent(cgOriginalImage);
    size_t bytesPerRow = CGImageGetBytesPerRow(cgOriginalImage);
    CGColorSpaceRef colorSpace = CGImageGetColorSpace(cgOriginalImage);
    CGBitmapInfo info = CGImageGetBitmapInfo(cgOriginalImage);
    
    CGContextRef context = CGBitmapContextCreate(nil, size.width, size.height, bitsPerComponent, bytesPerRow, colorSpace, info);
    
    CGContextSetInterpolationQuality(context, kCGInterpolationMedium);
    
    CGContextDrawImage(context, CGRectMake(0, 0, size.width, size.height), cgOriginalImage);
    
    CGImageRef cgResizedImage = CGBitmapContextCreateImage(context);
    
    UIImage *resizedImage = [UIImage imageWithCGImage:cgResizedImage];
    
    CGContextRelease(context);
    CGImageRelease(cgOriginalImage);
    CGImageRelease(cgResizedImage);

    return resizedImage;
}

- (CGSize)estimatedScaleSize:(CGSize)newSize forImage:(UIImage *)image {
    if (image.size.width > image.size.height) {
        newSize = CGSizeMake((image.size.width / image.size.height) * newSize.height, newSize.height);
    } else {
        newSize = CGSizeMake(newSize.width, (image.size.height / image.size.width) * newSize.width);
    }

    return newSize;
}

- (void)saveImage:(UIImage *)image toFilePath:(NSString *)filePath {
    NSData *pngData = UIImagePNGRepresentation(image);

    [pngData writeToFile:filePath atomically:YES];
}

- (UIImage *)loadImage:(NSString *)filePath {
    NSData *imgData = [NSData dataWithContentsOfFile:filePath];

    return [UIImage imageWithData:imgData];
}

- (NSString *)documentsPathForFileName:(NSString *)name {
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsPath = [paths objectAtIndex:0];

    return [documentsPath stringByAppendingPathComponent:name];
}

@end