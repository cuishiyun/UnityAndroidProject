//
//  test.m
//  Unity-iPhone
//
//  Created by Mac on 2019/08/20.
//

#import <Foundation/Foundation.h>

typedef void (*ResultHandler) (const char* object);


void outputAppendString(char* str1, char* str2)
{
    NSString* string1 = [[NSString alloc] initWithUTF8String:str1];
    NSString* string2 = [[NSString alloc] initWithUTF8String:str2];
    
    NSString* resultStr = [NSString stringWithFormat:@"%@ %@", string1, string2];
    NSLog(@"####%@", resultStr);
    
    UnitySendMessage("Canvas", "callback", resultStr.UTF8String);
}

void outputAppendString2(char* str1, char* str2, ResultHandler resultHandler)
{
    NSString* string1 = [[NSString alloc] initWithUTF8String:str1];
    NSString* string2 = [[NSString alloc] initWithUTF8String:str2];
    
    NSString* resultStr = [NSString stringWithFormat:@"%@ %@", string1, string2];
    NSLog(@"####%@", resultStr);
    
    resultHandler(resultStr.UTF8String);
}


bool isLiuHai()
{
    if (@available(iOS 11.0, *)) {
        UIEdgeInsets is =UIApplication.sharedApplication.windows[0].safeAreaInsets;
		bool result = is.top > 0;
        return result;
    } else {
        // Fallback on earlier versions
        return false;
    }
}
