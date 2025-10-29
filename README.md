# 📈 StockMonitor

**StockMonitor** is an **event-driven platform** designed for real-time stock data analysis and visualization.  
It combines live market updates, reliable asynchronous messaging, and a scalable architecture to deliver actionable financial insights.

---

## 🚀 Purpose

The main goal of **StockMonitor** is to simplify the creation of real-time stock monitoring tools by integrating data streams, asynchronous communication, and interactive visualization.  
The system follows a **Domain-Driven Design (DDD)** approach, promoting modularity, maintainability, and scalability.

---
## 🔧 How to Run Locally (Docker)

This application is fully containerized. You can run the entire stack (Frontend, Backend, Database, Messaging) with a single command.

### Prerequisites

* [Git](https://git-scm.com/downloads)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (which includes Docker Compose)

### 1. Clone the Repository

```bash
git clone [https://github.com/DavidCerdeiro/StockMonitor.git](https://github.com/DavidCerdeiro/StockMonitor.git)
cd StockMonitor
````
### 2. Create Environment File

This project uses a .env file to manage secrets and environment variables.

1. In the root directory of the project, create a new file named .env

2. Copy and paste the following into that file, replacing PASTE_YOUR_API_KEY_HERE with your actual Finnhub API key.
```bash
# Finnhub API Key (REQUIRED)
# Get yours from [https://finnhub.io/](https://finnhub.io/)
FINNHUB_API_KEY=PASTE_YOUR_API_KEY_HERE

# --- Configuration for Docker ---
# (These values match the docker-compose.yaml)

# Backend
PORT=8080
FRONTEND_URL=http://localhost:3000

# Database
POSTGRES_USER=user
POSTGRES_PASSWORD=password
POSTGRES_DB=stockmonitor_db
````
#### 3. Build and Run

Open a terminal in the project's root directory and run:
```bash
docker-compose up --build
````

This command will:

Build the custom Docker images for the frontend and backend.

Download the public images for Postgres and RabbitMQ.

Start all four containers and connect them.

This process may take a few minutes the first time.

4. Access the Application
Once all containers are running, you can access the services:
 - 🖥️ http://localhost:3000

## 💡 Key Features

- **🔌 WebSocket Integration:** Seamlessly stream live stock quotes to the frontend for dynamic updates.
- **📩 RabbitMQ Messaging:** Enables reliable and decoupled communication between system components.
- **📊 Finnhub API:** Provides accurate and real-time stock market data.
- **🖥️ Modern Frontend:** Built with **React**, **Tailwind CSS**, and full **i18n** support for a global audience.
- **⚙️ Robust Backend:** Developed with **Java (Spring Boot)** and **Spring Data JPA** for efficient data persistence.
- **🧱 Database:** **PostgreSQL**, structured to store and query financial information efficiently.
- **🧩 Scalable Architecture:** Clear separation of layers and domains for flexibility and long-term growth.
- **✅ Testing:** Comprehensive unit and integration tests using **JUnit 5**.
- **📡 Asynchronous Processing:** Uses **RabbitMQ** to handle data streams from **Finnhub** without blocking execution.

---


---

## 🧰 Tech Stack

| Layer | Technologies |
|-------|---------------|
| **Frontend** | React, Tailwind CSS, WebSocket |
| **Backend** | Java 21, Spring Boot, Spring Data JPA, RabbitMQ |
| **Database** | PostgreSQL |
| **Integration** | Finnhub API |
| **Testing** | JUnit 5 |
| **Architecture** | Domain-Driven Design, Event-Driven Architecture |
