# 🚌 Bus Transit App (Android)

An Android application that helps students at **VIT Chennai Campus** track bus routes, view real-time seat availability, and submit service feedback — all from their mobile device.

---

## 📱 Features

- **Student Login** — Secure authentication using student ID and password with persistent session management
- **Dashboard** — Central hub to navigate between routes, seat availability, and feedback
- **Route Selection** — Choose from three bus routes connecting to VIT Chennai Campus
- **Live Map View** — Google Maps integration showing route polylines, start/end markers, and randomly placed bus indicators along the route
- **Seat Availability** — Visual grid layout showing available, occupied, and female-reserved seats with color coding
- **Feedback System** — Star rating bar with optional comment submission, stored locally per student

---

## 🗺️ Supported Routes

| Route | Start Location |
|---|---|
| Avadi → VIT College | Avadi (13.1144, 80.1489) |
| Manali → VIT College | Manali (13.1660, 80.2641) |
| Chennai Central → VIT College | Chennai Central (13.0827, 80.2707) |

All routes terminate at **VIT Chennai Campus** (12.8406, 80.1534).

---

## 🏗️ Project Structure

```
app/src/main/java/com/example/bustrasit/
├── MainActivity.java           # Splash/entry screen
├── LoginActivity.java          # Student authentication
├── DashboardActivity.java      # Main navigation hub
├── RouteActivity.java          # Route selection with spinner
├── MapActivity.java            # Google Maps route visualization
├── AvailabilityActivity.java   # Seat grid display
├── SeatAdapter.java            # Custom GridView adapter for seats
├── FeedbackActivity.java       # Rating and comment submission
└── DatabaseHelper.java         # SQLite database (students, buses, feedback)
```

---

## 🗄️ Database Schema

The app uses **SQLite** via `DatabaseHelper` with three tables:

**`students`**
```sql
id TEXT PRIMARY KEY, name TEXT, password TEXT
```

**`buses`**
```sql
bus_id INTEGER PRIMARY KEY AUTOINCREMENT,
route TEXT, timing TEXT, start_location TEXT,
destination TEXT, total_seats INTEGER,
occupied_seats INTEGER, female_seats INTEGER
```

**`feedback`**
```sql
id INTEGER PRIMARY KEY AUTOINCREMENT,
student_id TEXT, rating INTEGER, comment TEXT,
FOREIGN KEY(student_id) REFERENCES students(id)
```

### Sample Student Credentials (pre-seeded)

| Student ID | Password |
|---|---|
| 22MIS1134 | pass123 |
| 22MIS1084 | secure456 |
| 22MIS0000 | nvrfound123 |

---

## 🔧 Tech Stack

| Component | Details |
|---|---|
| Language | Java |
| Platform | Android (minSdk 24, targetSdk 35) |
| Build Tool | Gradle (AGP 8.7.3) |
| Maps | Google Maps SDK (`play-services-maps:19.1.0`) |
| UI | ConstraintLayout, Material Design 3 |
| Database | SQLite (via `SQLiteOpenHelper`) |
| Java Version | Java 17 |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (Hedgehog or later)
- Android SDK 24+
- A valid **Google Maps API Key**

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/Bus-Transit.git
   cd Bus-Transit
   ```

2. **Add your Google Maps API Key**

   Open `app/src/main/AndroidManifest.xml` and replace the placeholder value:
   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="YOUR_API_KEY_HERE"/>
   ```
   > ⚠️ Never commit a real API key to a public repository. Use `local.properties` or environment variables for production.

3. **Open in Android Studio**
   - Select **File → Open** and choose the project root folder
   - Let Gradle sync complete

4. **Run the app**
   - Connect a physical device or start an emulator (API 24+)
   - Click **Run ▶**

---

## 🎨 Seat Color Legend

| Color | Meaning |
|---|---|
| 🟢 Green | Available |
| 🔴 Red | Occupied |
| 🟣 Purple | Female Reserved |

---

## 📋 Permissions

The app requests the following permissions:

```xml
android.permission.ACCESS_FINE_LOCATION
android.permission.ACCESS_COARSE_LOCATION
android.permission.INTERNET
```

Location permissions are used for Google Maps; internet access is required to load map tiles.

---

## 🔮 Potential Improvements

- Replace hardcoded seat data with real-time database queries
- Add bus schedule/timing display per route
- Implement push notifications for bus arrival alerts
- Migrate to ViewModel + LiveData (MVVM architecture)
- Secure password storage using hashing (e.g., bcrypt)
- Add an admin panel for managing bus and student records

---

## 📄 License

This project is open source. Feel free to build upon it.
