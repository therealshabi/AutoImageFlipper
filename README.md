# AutoImageFlipper
[![](https://jitpack.io/v/therealshabi/AutoImageFlipper.svg)](https://jitpack.io/#therealshabi/AutoImageFlipper)   [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)

Auto Scrolling Image Pager with Pager Indicator and Text

## Gradle 
```
	dependencies {
	        compile 'com.github.therealshabi:AutoImageFlipper:v1.4-beta'
	}
```

## Implementation

This is an Automatic scrolling Image Slider Library with the functionality of adding an image with its optional description,
it also has a View Pager Indicator and built in listeners.
The library is still in alpha phase and a lot of features are coming soon...

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
```        
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
            flipperLayout.setScrollTimeInSec(3); //setting up scroll time, by default it's 3 seconds
	    flipperLayout.getScrollTimeInSec(); //returns the scroll time in sec
	    flipperLayout.getCurrentPagePosition(); //returns the current position of pager
            flipperLayout.addFlipperView(view);
        }

```

## Tools and Libraries Used : 

1. Picaaso for Image loading
2. Circle Indicator by https://github.com/ongakuer/CircleIndicator


Please feel free to contribute by pull request, issues or feature requests.


