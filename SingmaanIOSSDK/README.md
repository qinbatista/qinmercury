## Get Started

###Import Singmaan SDK

Copy SDK folder or files from this Unity project into your Unity project, please check if scripts missing inside `SplashScreen.unity` after copying into game project, **SplashScreenManager.cs** is on `Main Camera` and **PluginMercury.cs** is on `PluginMercury`

* `Assets/Plugins`
* `Assets/Scene/SplashScreen.unity`
* `Assets/Script/SplashScreenManager.cs`
* `Assets/Script/PluginMercury.cs`
* `Assets/picture`

----

###Set Unity Scene

> Our scene will create a object named PluginMercury which will not be destoryed, thus you could use all Singmaan SDK functions without any issues. if developers don't want add scene, please make sure PluginMercury.cs is on the object named PluginMercury, or SDK can't recive callback.

* change your next scene name in **SplashScreenManager.cs**

```C#
public string nextSceneName = "YourGameScene";
```

* Set **SplashScreen.unity** as first Scenes in **File->Build Setting**

----

###Use Singmaan SDK

All usable methods could be seen in **PluginMercury.cs** in this Unity project, following are explaintation.

```C#
public void Purchase(string strProductId)
```

* giving correct product id which named by developers will receive same product id from `PurchaseSuccessCallBack` in `PluginMercury.cs` , make sure all productions are unique and giving user prodoctions in `PurchaseSuccessCallBack`.

```C#
public void Redeem()
```

* Singmaan will create really Redeem system in release version, make sure when game recived production id from `PurchaseSuccessCallBack` could give users correct production, current version just return simple log without any functions.

```C#
public void RestoreProduct()
```

* Singmaan will create really RestoreProduct system in release version, make sure when game recived production id from `PurchaseSuccessCallBack` could give users correct production, current version just return simple log without any functions.

```C#
public void ExitGame()
```

* pressing exit button on Android phone is requried for this function, usually `Input.GetKeyDown(KeyCode.Escape)` is enough to detect this event. IOS version don't need.

```C#
public void ActiveRewardVideo()
```

* Active advertisenment,  game are able to receive following callback if ad SDK returned, `AdLoadSuccessCallBack`,`AdLoadFailedCallBack`, `AdShowSuccessCallBack`, `AdShowFailedCallBack`

```C#
public void ActiveInterstitial()
```

* Active advertisenment,  game are able to receive following callback if ad SDK returned, `AdLoadSuccessCallBack`,`AdLoadFailedCallBack`, `AdShowSuccessCallBack`, `AdShowFailedCallBack`

```C#
public void ActiveBanner()
```

* Active advertisenment,  game are able to receive following callback if ad SDK returned, `AdLoadSuccessCallBack`,`AdLoadFailedCallBack`, `AdShowSuccessCallBack`, `AdShowFailedCallBack`

```C#
public void ActiveNative()
```

* Active advertisenment,  game are able to receive following callback if ad SDK returned, `AdLoadSuccessCallBack`,`AdLoadFailedCallBack`, `AdShowSuccessCallBack`, `AdShowFailedCallBack`

___

### Delivery Project

----

**Android**

* No crashing
* showing testing dialog
* recived callback and gave productions to users
* send APK to us

**IOS**

* No crashing
* showing testing dialog
* recived callback and gave productions to users
* send Xcode Project to us
