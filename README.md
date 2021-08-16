APP DEVELOPED IN: 

IDE -> Android Studio Artic Fox 2020.3.1

minSdk -> 16

targetSdk -> 31
  
-----
TECHNOLOGIES USED:

  Glide -> to load images
  
  Retrofit -> to HTTPs Requests
  
  Gson -> to convert Json
  
  dataBiding -> to reduce verbosity
  
-----

GRADLE DEPENDENCIES:

//Glide

implementation 'com.github.bumptech.glide:glide:4.12.0'

//Retrofit & GSON

implementation 'com.squareup.retrofit2:retrofit:2.9.0'

implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

-----

GRADLE PLUGINS: 

id 'com.android.application'

id 'kotlin-android'

id 'kotlin-kapt'

id 'kotlin-android-extensions'

-----

USAGE:
  - Open build.gradle(app)
      - add dependencies
      - add plugins
      - in android, add dataBinding.enabled = true 
      - Sync Gradle

  - Run App
	
      - Write a name movie at TextView to search a list of Movies
      - Click in Button Search
      - Click in some movie to more details
