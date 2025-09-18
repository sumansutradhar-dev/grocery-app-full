# GroceryApp (Full)

This is a Jetpack Compose + Room sample Grocery app. It includes:
- Multiple grocery lists
- Add items with name, quantity, price
- Checklist (mark bought)
- Simple bill/checkout
- ViewModel + Room repository
- Navigation Compose

**How to use**
1. Unzip, open in Android Studio.
2. Add Gradle wrapper if you don't have it (recommended: in Android Studio, _File → New → Project from Existing Sources_ will prompt).
3. Build & run.

**GitHub Actions**
A workflow is included to run `./gradlew assembleDebug`. Ensure your project has the Gradle wrapper (`gradlew` and `gradle/wrapper/*`) before relying on CI on first push.

