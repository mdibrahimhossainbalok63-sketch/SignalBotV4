# SignalBot V4 — Phone-only GitHub APK Build

This repository is prepared for building the Android debug APK with GitHub Actions, so a computer is not required for the build itself.

## Phone-only workflow

1. Create a GitHub repository.
2. Upload the CONTENTS of this folder to the repository root (not the ZIP file itself).
3. Make sure these exist at the repository root:
   - `settings.gradle.kts`
   - `build.gradle.kts`
   - `gradlew`
   - `gradle/wrapper/...`
   - `app/...`
   - `.github/workflows/build-apk.yml`
4. Open the repository's **Actions** tab.
5. Select **Build SignalBot V4 APK**.
6. Tap **Run workflow**.
7. Wait for the green check.
8. Open the completed workflow run.
9. Under **Artifacts**, download `SignalBot-V4-debug-APK`.
10. Extract the downloaded artifact ZIP and install `app-debug.apk`.

GitHub Actions artifacts are designed to store files produced by a workflow and can be downloaded after the run.

## Important

The Quotex official API execution adapter is disabled. This app is signal/paper mode until a documented and authorized broker API is available.

Do not put passwords, OTPs, cookies, SSIDs, or API secrets into the repository.
