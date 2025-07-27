# 🎵 iTunes Top Albums Android App

An Android application that displays **Top 100 iTunes Albums** using **Jetpack Compose**, **Clean Architecture**, and **Offline-First** approach with Room and Retrofit.

---

## ✅ Features
- **Clean Architecture (MVVM)**
    - Presentation Layer: Jetpack Compose + ViewModel
    - Domain Layer: Use Cases + Repository Interfaces
    - Data Layer: Retrofit + Room Database
- **Offline-First Support** using Room
- **Networking** with Retrofit and Moshi
- **Hilt for Dependency Injection**
- **Navigation Compose** with sealed routes
- **Album Detail Screen** with full album info
- **Error Handling** with retry button and message
- **Unit Tests** for Repository, UseCase, and ViewModel

---

## 🛠 Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose, Material 3
- **Architecture**: Clean Architecture + MVVM
- **Networking**: Retrofit + Moshi
- **Database**: Room
- **Dependency Injection**: Hilt
- **Async**: Coroutines + Flow
- **Navigation**: Navigation Compose
- **Testing**: JUnit4, MockK, Coroutines Test

---

## ▶ How to Build and Run

### ✅ Prerequisites
- Android Studio **Giraffe or newer**
- JDK **11+**
- Gradle **8+**

### ✅ Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/itunes-top-albums.git
    cd itunes-top-albums
    ```
2. Open in Android Studio
3. Sync Gradle
4. Run the app on an emulator or device

---

## 🔍 How It Works
- Fetches **Top 100 Albums** from `https://itunes.apple.com/us/rss/topalbums/limit=100/json`
- Caches data in **Room Database** for offline use
- Displays albums in a **LazyColumn**
- Clicking an album navigates to **Detail Screen**
- Shows **error message & retry button** on failure

---

## ✅ Testing
Run unit tests:
```bash
./gradlew test