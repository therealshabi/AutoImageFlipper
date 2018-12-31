# AutoImageFlipper
[ ![Download](https://api.bintray.com/packages/therealshabi/AutoImageFlipper/AutoImageFlipper/images/download.svg?version=1.5.5) ](https://bintray.com/therealshabi/AutoImageFlipper/AutoImageFlipper/1.5.5/link) [![](https://jitpack.io/v/therealshabi/AutoImageFlipper.svg)](https://jitpack.io/#therealshabi/AutoImageFlipper)   

[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoImageFlipper-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6134)

Auto Scrolling Image Pager with Pager Indicator and Text

## Gradle 

### Using jCenter

* Maven
```xml
<dependency>
  <groupId>com.github.technolifestyle</groupId>
  <artifactId>imageslider</artifactId>
  <version>1.5.5</version>
  <type>pom</type>
</dependency>
```

* Gradle
```groovy
compile 'com.github.technolifestyle:imageslider:1.5.5'
```

* Ivy
```xml
<dependency org='com.github.technolifestyle' name='imageslider' rev='1.5.5'>
  <artifact name='imageslider' ext='pom' ></artifact>
</dependency>
```

### Using Jitpack

1. In your top level `build.gradle` file, in the `repository` section add the `maven { url 'https://jitpack.io' }` as shown below
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the `AutoImageFlipper` dependency in your app level build.gradle file
```
dependencies {
	implementation 'com.github.therealshabi:AutoImageFlipper:v1.4.3'
}
```

## Implementation

This is an Automatic scrolling Image Slider Library with the functionality of adding an image with its optional description,
it also has a View Pager Indicator and built in listeners.
The library is open for contributions. For adding extra features you may send me a Pull Request...

<img src="/gif/demo.gif" alt="Auto Image Slider" width= "300px"/>

## Usage
* In XML layout:
```xml
<technolifestyle.com.imageslider.FlipperLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/flipper_layout"
    android:layout_width="match_parent"
    android:layout_height="200dp"/>
```
* In Java File:
For View Pager with 3 Views
```java       
FlipperLayout flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);
int num_of_pages = 3;
        for (int i = 0; i < num_of_pages; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl("<valid image url>")
	    	.setImageDrawable(R.drawable.test) // Use one of setImageUrl() or setImageDrawable() functions, otherwise IllegalStateException will be thrown
		.setImageScaleType(ScaleType.CENTER_CROP) //You can use any ScaleType
                .setDescription("Description")
		.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    //Handle View Click here
                }
            });
            flipperLayout.setScrollTimeInSec(5); //setting up scroll time, by default it's 3 seconds
	    flipperLayout.getScrollTimeInSec(); //returns the scroll time in sec
	    flipperLayout.getCurrentPagePosition(); //returns the current position of pager
            flipperLayout.addFlipperView(view);
        }

```

* FlipperView customization includes:

```java
//Instantiate FlipperView
FlipperView view = new FlipperView(getBaseContext());
```
```java
//Set Image into the flipperView using url
view.setImageUrl("https://source.unsplash.com/random")
;
//Set Image using Drawable resource
view.setImageDrawable(R.drawable.test);
```
```java
//Set Image Description Text (Optional)
view.setDescription("Great Image");
```
```java
//Set Description text view background color
view.setDescriptionBackgroundColor(Color.Green);
```
```java
//Set Description text view background alpha (0 <= alpha <= 1)
view.setDescriptionBackgroundAlpha(0.5f);
```
```java
//Set Description text view background with color and alpha (0 <= alpha <= 1)
view.setDescriptionBackgroundAlpha(Color.BLUE, 0.5f);

//Set Description text view background with a drawable resource
view.setDescriptionBackgroundDrawable(R.drawable.bg_overlay);
```
```java
//Reset Description text view background and text color
view.resetDescriptionTextView();
```
```java
//Set Description Text Text color
view.setDescriptionTextColor(Color.WHITE);
```
```java
//Set Image scale type (E.g. ScaleType.CENTRE_CROP)
view.setImageScaleType(ScaleType.CENTER_INSIDE);
```
```java
//Set click listener
view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
	@Override
	public void onFlipperClick(FlipperView flipperView) {
		Toast.makeText(MainActivity.this, "I was clicked", Toast.LENGTH_SHORT).show();
	}
});
```

* FlipperLayout methods includes:-

```java
// Instantiation
FlipperLayout flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);
```
```java
//Set flipper scroll time in seconds (default 3s)
flipperLayout.setScrollTimeInSec(5) ;
```
```java
//Set Circle Indicator width (in dp)
flipperLayout.setCircleIndicatorWidth(200);
```
```java
//Set Circle Indicator height (in dp)
flipperLayout.setCircleIndicatorHeight(20);
```
```java
//Set Circle Indicator width and height (in dp)
flipperLayout.setCircularIndicatorLayoutParams(200, 20);
```
```java
//Remove Circular indicator
flipperLayout.removeCircleIndicator();
```
```java
//Show Circular Indicator
flipperLayout.showCircleIndicator();
```
```java
//Returns the currently displayed 
flipperLayout.getCurrentPagePosition();
```
```java
//Add flipperView into the flipperLayout
flipperLayout.addFlipperView(flipperView);
```

> Note: You have to include Internet permission into the manifest for downloading image from the url and setting that up into the FlipperView
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Tools and Libraries Used : 

1. Picasso for Image loading

Please feel free to contribute by pull request, issues or feature requests.

## License
```
Copyright 2019 Shahbaz Hussain

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

