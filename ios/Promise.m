//
//  Promise.m
//  SuperApp
//
//  Created by Nha Tran on 12/12/2023.
//
#import "Promise.h"

@implementation Promise

-(instancetype)initWithResolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
    self = [super init];
    self.reject = reject;
    self.resolve = resolve;
    return self;
}

@end
