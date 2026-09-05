# Build SignalBot V4 in Android Studio

1. Extract this ZIP to a normal folder (do not build from inside the ZIP archive).
2. Open the extracted **SignalBot_V4_Android_Prepared** folder in Android Studio.
3. Allow Android Studio to sync/download the Gradle and Android dependencies.
4. Select **Build > Build APK(s)**.
5. The debug APK will be generated at:
   `app/build/outputs/apk/debug/app-debug.apk`

Recommended Android Studio: a current stable release with Android SDK 35 installed.
Recommended JDK: 17.

## Important
The project is a paper/signal application. The Quotex execution adapter is intentionally disabled until an officially documented and authorized Quotex API is available. It does not use password/OTP/cookie/SSID extraction or browser-click automation.

### About the Gradle wrapper
The project includes the wrapper configuration. If Android Studio reports that the wrapper JAR is missing, use **Gradle > Generate Gradle Wrapper** from a local Gradle installation, or let Android Studio upgrade/regenerate the wrapper. The wrapper JAR itself is not redistributed in this package.
