[English](./README_en-US.md) | 简体中文

# bankcard

[![Build Status](https://travis-ci.org/noob9527/bankcard.svg?branch=master)](https://travis-ci.org/noob9527/bankcard)
[![](https://jitpack.io/v/noob9527/bankcard.svg)](https://jitpack.io/#noob9527/bankcard)

### installation
#### kotlin/java lib
##### via gradle
```groovy
// step 1: add repository
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' } // add this line
    ...
}

// step 2: add dependency
dependencies {
    compile("com.github.noob9527:bankcard:master-SNAPSHOT")
    // ...
}
```
##### via maven
```xml
<!-- step 1: add repository -->
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<!-- step 2: add dependency -->
<dependency>
    <groupId>com.github.noob9527</groupId>
    <artifactId>bankcard</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

### api
- findBankInfo
    根据 `bankCode` 和 `countryCode` 查找银行， 目前通过查找 dist 目录中的 json 数据来实现， 举例：
    ```kotlin
    val bankInfo = Bankcards.findBankInfo("ABC", "cn")
    // bankInfo to json => 
    // {
    //     "name": "abc",
    //     "country": "cn",
    //     "localTitle": "中国农业银行",
    //     "engTitle": "Agricultural Bank of China",
    //     "url": "http://www.abchina.com/",
    //     "color": "#319c8b",
    //     "prefixes": [
    //         403361,
    //         ...
    //     ],
    //     "bankCode": "ABC"
    // }
    ```
- findBankLogoUrl
    根据 `bankCode` 和 `countryCode` 查找银行 logo， 目前通过 http 查找 source/bankLogo 目录实现, 举例：
    ```kotlin
    val logoUrl = Bankcards.findBankLogoUrl("ABC", "cn");
    // => "https://raw.githubusercontent.com/noob9527/bankcard/master/source/banklogo/cn/ABC.png"
    // 
    ```
- findBankCardInfo
    根据 `cardNumber` 查找银行卡信息，目前通过调用支付宝 api 实现，举例：
    ```kotlin
    val cardInfo = Bankcards.findBankCardInfo("your card number");
    // cardInfo to json =>
    // {
    //     "cardNumber": "your card number",
    //     "cardType": "DC",
    //     "bankCode": "ABC"
    // }
    ```

