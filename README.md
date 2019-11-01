# AutoImageFlipper

[ ![Download](https://api.bintray.com/packages/therealshabi/AutoImageFlipper/AutoImageFlipper/images/download.svg) ](https://bintray.com/therealshabi/AutoImageFlipper/AutoImageFlipper/_latestVersion) [![](https://jitpack.io/v/therealshabi/AutoImageFlipper.svg)](https://jitpack.io/#therealshabi/AutoImageFlipper)

[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoImageFlipper-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6134)

Auto Scrolling Image Pager with Pager Indicator and Text

```
Note: It works only on Apps which are using AndroidX dependencies, if you're not using AndroidX,
you can migrate to AndroidX by selecting Refactor -> Migrate to AndroidX from the
Android Studio top bar.
```

## Gradle

### Using jCenter

- Maven

```xml
<dependency>
	<groupId>com.github.technolifestyle</groupId>
	<artifactId>AutoImageFlipper</artifactId>
	<version>1.5.8</version>
	<type>pom</type>
</dependency>
```

- Gradle

```groovy
implementation 'com.github.technolifestyle:AutoImageFlipper:1.5.8'
```

- Ivy

```xml
<dependency org="com.github.technolifestyle" name="AutoImageFlipper" rev="1.5.8">
	<artifact name="AutoImageFlipper" ext="pom"></artifact>
</dependency>
```

### Using Jitpack

- Gradle

1. In your top level `build.gradle` file, in the `repository` section add the `maven { url 'https://jitpack.io' }` as shown below

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2. Add the `AutoImageFlipper` dependency in your app level build.gradle file

```groovy
dependencies {
   implementation 'com.github.therealshabi:AutoImageFlipper:1.5.8'
}
```

- Maven

1. Add the JitPack repository to your build file

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

2. Add the dependency

```xml
<dependency>
    <groupId>com.github.therealshabi</groupId>
    <artifactId>AutoImageFlipper</artifactId>
    <version>1.5.7</version>
</dependency>
```

## Implementation

This is an Automatic scrolling Image Slider Library with the functionality of adding an image with its optional description,
it also has a View Pager Indicator and built in listeners.
The library is open for contributions. For adding extra features you may send me a Pull Request...

<img src="/gif/demo.gif" alt="Auto Image Slider" width= "300px"/>

## Usage

- In XML layout:

```xml
<technolifestyle.com.imageslider.FlipperLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/flipper_layout"
    android:layout_width="match_parent"
    android:layout_height="200dp"/>
```

- In Java File:
  For View Pager with 3 Views

```java
FlipperLayout flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);
int num_of_pages = 3;
for (int i = 0; i < num_of_pages; i++) {
  FlipperView view = new FlipperView(getBaseContext());
  view.setImageScaleType(ScaleType.CENTER_CROP) // You can use any ScaleType
      .setDescription("Description") // Add custom description for your image in the flipper view
      .setImage(R.mipmap.ic_launcher, new Function2<ImageView, Object, Unit>() {
          @Override
          public Unit invoke(ImageView imageView, Object image) {
              // As per the user discretion as to how they want to load the URL
              /* E.g since an image of Drawable type is sent as a param in setImage method, The Object
              * image will be of type Drawable
              * imageView.setImageDrawable((Drawable)image);
              */
              return Unit.INSTANCE;
          }
      })
      .setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
        @Override
        public void onFlipperClick(FlipperView flipperView) {
            // Handle View Click here
        }
    });
  flipperLayout.setScrollTimeInSec(5); //setting up scroll time, by default it's 3 seconds
  flipperLayout.getScrollTimeInSec(); //returns the scroll time in sec
  flipperLayout.getCurrentPagePosition(); //returns the current position of pager
  flipperLayout.addFlipperView(view);
}

```

- FlipperView customization includes:

```java
//Instantiate FlipperView
FlipperView view = new FlipperView(getBaseContext());
```

### Methods to set image resource into the Flipper View

- Kotlin

```kotlin
//Set Image into the flipperView using url
view.setImageUrl("https://source.unsplash.com/random") { imageView, image ->
    // Load image (url) into the imageview using any image loading library of your choice
    // E.g. Picasso.get().load(image as String).into(imageView);
}

//Set Image using Drawable resource
view.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder)) { imageView, image ->
  imageView.setImageDrawable(image as Drawable)
}

//Set Image using Bitmap image
view.setImageBitmap(bitmapImage) { imageView, image ->
  imageView.setImageBitmap(image as Bitmap)
}
```

or you can use a common method to set the image

```kotlin
view.setImage(R.drawable.error) { imageView, image ->
  imageView.setImageDrawable(image as Drawable)
}
```

There are 4 types of values setImage method can take as the first param, a url of `String` type, an integer drawable resource Id of `@DrawableRes Int` type and images of `Drawable` and `Bitmap` type. In case any other values are send in this method, the method will throw `IllegalArgumentException`.

It's worth noting that for each type of image we provide as the first param in the method we need to type cast the image with the same type while setting the image into the image view via the second higher order function, i.e. for instance in method

```kotlin
view.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder)) { imageView, image ->
  imageView.setImageDrawable(image as Drawable)
}
```

we sent a Drawable resource file as the first param, so while setting the image in the ImageView in the higher order function, we have type cast it to Drawable type explicitly as we can see in the line `imageView.setImageDrawable(image as Drawable)`, the only exception in this process being the instance when we send a drawable resource, we need to typecast the image to `Drawable` type, although we send an `@DrawableRes Int` param.

E.g.

```kotlin
view.setImage(R.drawable.error) { imageView, image ->
  imageView.setImageDrawable(image as Drawable)
}
```

Besides that `setImage` method throws 3 kinds of Exception:-

1. When we send a string URL, which does not actually resolve to an actual URL, a `MalformedURLException` is thrown.
2. `IllegalArgumentException` is thrown when we try to send any Illegal typed first param i.e types in addition to the types a url of `String` type, an integer drawable resource Id of `@DrawableRes Int` type and images of `Drawable` or `Bitmap` type.
3. `IllegalStateException` this exception is thrown when the user tries to set more than one image into a single `FlipperView`.

- Java

  For java just replace the 2nd param of above methods with:-

  ```java
  new Function2<ImageView, Object, Unit>() {
      @Override
      public Unit invoke(ImageView imageView, Object image) {
          // As per the user discretion as to how they want to load the URL
          /* E.g since an image of Drawable type is sent as a param in setImage method, The Object
          * image will be of type Drawable
          * imageView.setImageDrawable((Drawable)image);
          */
          return Unit.INSTANCE;
      }
  }
  ```

  For Instance the equivalent of `setImageDrawable` method in Java would be:-

  ```java
  view.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder), new Function2<ImageView, Object, Unit>() {
    @Override
    public Unit invoke(ImageView imageView, Object image) {
      imageView.setImageDrawable((Drawable) IMAGE);
      return Unit.INSTANCE;
    }
  });
  ```

  Similarly for all other methods

---

### Other important `FlipperView` methods

```java
// Set Image Description Text (Optional)
view.setDescription("Great Image");
```

```java
// Set Description text view background color
view.setDescriptionBackgroundColor(Color.Green);
```

```java
// Set Description text view background alpha (0 <= alpha <= 1)
view.setDescriptionBackgroundAlpha(0.5f);
```

```java
// Set Description text view background with color and alpha (0 <= alpha <= 1)
view.setDescriptionBackgroundAlpha(Color.BLUE, 0.5f);

// Set Description text view background with a drawable resource
view.setDescriptionBackgroundDrawable(R.drawable.bg_overlay);
```

```java
// Reset Description text view background and text color
view.resetDescriptionTextView();
```

```java
// Set Description Text Text color
view.setDescriptionTextColor(Color.WHITE);
```

```java
// Set Image scale type (E.g. ScaleType.CENTRE_CROP)
view.setImageScaleType(ScaleType.CENTER_INSIDE);
```

```java
// Set click listener
view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
  @Override
  public void onFlipperClick(FlipperView flipperView) {
    Toast.makeText(MainActivity.this, "I was clicked", Toast.LENGTH_SHORT).show();
  }
});
```

- FlipperLayout methods includes:-

```java
// Instantiation
FlipperLayout flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);
```

```java
// Set flipper scroll time in seconds (default 3s)
flipperLayout.setScrollTimeInSec(5);
```

```java
// Method to remove auto image sliding/flipping/cycling
flipperLayout.removeAutoCycle();
```

```java
// Method to start auto image sliding/flipping/cycling
flipperLayout.startAutoCycle();
flipperLayout.startAutoCycle(5); // will start auto cycling image with a delay of 5 seconds
```

```java
// Method to remove all existing Flipper Views from the Flipper Layout
flipperLayout.removeAllFlipperViews();
```

```java
// Set Circle Indicator width (in dp)
flipperLayout.setCircleIndicatorWidth(200);
```

```java
// Set Circle Indicator height (in dp)
flipperLayout.setCircleIndicatorHeight(20);
```

```java
// Set Circle Indicator width and height (in dp)
flipperLayout.setCircularIndicatorLayoutParams(200, 20);
```

```java
// Metod set circular Indicator background
flipperLayout.setIndicatorBackgroundColor(Color.parseColor("#90000000")); // To set background color
flipperLayout.setIndicatorBackground(R.drawable.your_drawable); // To set background drawable using drawable resource id
flipperLayout.setIndicatorBackground(ContextCompat.getDrawable(context, R.drawable.your_drawable)); // To set background drawable using drawable resource
```

```java
// Remove Circular indicator
flipperLayout.removeCircleIndicator();
```

```java
// Show Circular Indicator
flipperLayout.showCircleIndicator();
```

```java
// Method to show inner circle indicator rather than an exterior indicator
flipperLayout.showInnerPagerIndicator();
```

```java
// Method to customise the flipper layout
flipperLayout.customizeFlipperPager { flipperPager ->
  // Do whatever you like to do with the flipperPager
};
```

```java
// Method to customise flipper layout's default circular indicator
flipperLayout.customizeCircularIndicator { circularIndicator ->
  // Do whatever you like to do with the circular indicator
};
```

```java
// Returns the currently displayed
flipperLayout.getCurrentPagePosition();
```

```java
// Add flipperView into the flipperLayout
flipperLayout.addFlipperView(flipperView);
```

```java
// Add list of flipperViews into the flipperLayout at once
ArrayList<FlipperView> flipperViewList = new ArrayList()
flipperViewList.add(new FlipperView(context));
flipperViewList.add(new FlipperView(context)
        .setDescription("test flipper view"));
...
flipperLayout.addFlipperViewList(flipperViewList);
```

```java
// Add different PageTransformer animation similar to ViewPager
flipperLayout.addPageTransformer(false, new ZoomOutPageTransformer());
```

A couple of pre-defined PageTransformer is included in the library namely,
`ZoomOutPageTransformer` and `DepthPageTransformer`, you can add your custom PageTransformer logic as well.

```java
flipperLayout.addPageTransformer(false, new ViewPager.PageTransformer() {
  @Override
  public void transformPage(@NonNull View page, float position) {
    //Write your animation logic here
  }
});
```

---

## Usage with the RecyclerView

To use FlipperLayout in the RecyclerView, before binding the layout remove all the existing flipper views from the FlipperLayout
by calling `flipperLayout.removeAllFlipperViews()` and post that populate new flipper views in the flipperLayout. A sample code is present in the [HomeRecyclerAdapter](https://github.com/therealshabi/AutoImageFlipper/tree/master/app/src/main/java/technolifestyle/com/autoimageslider/home/HomeRecyclerAdapter.kt#L50-L70) class.

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
