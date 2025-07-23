# 🧾 Membership Plans and Tiers - Spring Boot Application

This project provides a backend service for managing **membership plans and their corresponding tiers**, built with **Java Spring Boot**. It supports multiple plans, each with multiple tiers, and optional membership benefits.

---

## 📦 Features

- Create, read, and manage **Membership Plans**
- Define multiple **Membership Tiers** under each plan
- Fetch available tiers for a specific plan
- Structured in a **future-ready**, modular architecture

---

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.x
- H2 (in-memory) database (for testing/demo)
- Maven
- Postman for API testing

---

## 🛠️ Getting Started

### 📁 Prerequisites

- Java 17+
- Maven
- Spring Boot CLI (optional)

---
### API Endpoints
Method	Endpoint	Description
GET	/api/memberships/plans	Get all available membership plans.
GET	/api/memberships/tiers	Get all available membership tiers.
GET	/api/memberships/plans/{planId}/tiers	Get tiers under a specific plan.
POST	/api/memberships/subscribe	Subscribe a user to a plan and tier.
POST	/api/memberships/upgrade	Upgrade user's subscription.
POST	/api/memberships/downgrade	Downgrade user's subscription.
DELETE	/api/memberships/cancel/{userId}	Cancel a user's subscription.
GET	/api/memberships/subscription/{userId}	Get a user's current subscription.


### 🔧 Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/prashant201267/Featuer-MemebershipPlan.git
   cd Featuer-MemebershipPlan
