# 📈 Professional Stock Trading Platform

A desktop-based stock trading simulation platform built with **JavaFX**.  
The application provides a modern dashboard interface for visualizing and interacting with trading-related features.

---

## 🚀 Features

- Modern JavaFX UI dashboard
- Modular view structure (`view.DashboardView`)
- Scalable application architecture
- Ready for integration with:
  - Market data APIs
  - Portfolio management
  - Trading simulations
- Styled UI support via external CSS (optional)

---

## 🛠️ Tech Stack

- **Java 11+**
- **JavaFX**
- MVC-inspired project structure
- CSS (optional styling support)

---

## 📂 Project Structure

stock-trading-gui
│
├── pom.xml
│
├── src
│   └── main
│       ├── java
│       │   ├── app
│       │   ├── controller
│       │   ├── model
│       │   ├── service
│       │   └── view
│       │
│       └── resources.style
│           └── css

---

## 🎨Styling (Optional)
- You can enable CSS styling by uncommenting the following lines in MainApp.java:
  scene.getStylesheets().add(
    getClass().getResource("/styles.css").toExternalForm()
  );

---

## 🛠️ Requirements

- Java 11 or higher
- JavaFX SDK installed and configured
- IDE such as IntelliJ IDEA, Eclipse, or VS Code

