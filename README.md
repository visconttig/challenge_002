# 💱 Currency Converter - Java Certification Challenge

Welcome to the **Currency Converter**, a Java console application built to meet the core requirements of a certification challenge:

* ✅ Consume a public **Currency Exchange API**
* ✅ Support at least **three currencies** for conversion
* ✅ Use the **Gson** library to parse JSON into Java objects

...But we didn’t stop there. This project goes the extra mile with clean architecture, defensive programming, user-friendly feedback, and a polished user experience. 🚀

---

## 📌 Challenge Requirements

The goal of the challenge was to:

1. Connect to a currency exchange API and retrieve live exchange rates.
2. Let users convert between **at least three currency pairs**.
3. Parse the JSON API response using the **Gson** library.

---

## 🌟 Features (Beyond the Basics)

* ✅ **Modular & Maintainable**: Code is broken into well-scoped classes like `CurrencyConverter`, `MenuOption`, and `Currency`.
* ✅ **Robust Error Handling**: Gracefully handles network issues, missing API keys, and invalid input.
* ✅ **Dynamic Menu System**: Built using `LinkedHashMap` and a record-style `MenuOption` to easily extend and maintain options.
* ✅ **Smart Pluralization**: Custom logic ensures grammatically correct output like `1 U.S. Dollar is...` vs `5 U.S. Dollars are...`.
* ✅ **Readable Output**: Clean formatting makes results easy to understand at a glance.
* ✅ **Environment-Based Configuration**: API key and test/debug settings pulled from environment variables.

---

## 🖥️ Example Output

```
**********************************************
**********************************************
*** Welcome to the Currency Converter App! ***
1:	Dollar =>> Argentinian Peso.
2:	Argentinian Peso =>> Dollar.
3:	Dollar =>> Brazilian Real.
4:	Brazilian Real =>> Dollar.
5:	Dollar =>> Colombian Peso.
6:	Colombian Peso =>> Dollar.
7:	Exit
Choose an option to continue:
```

```
10 U.S. Dollars are $ 8,720.0000 Argentinian Pesos.
```

---

## 🛠️ Tech Stack

* Java 17+
* Maven
* Gson (`com.google.code.gson:gson`)
* Java HTTP Client (native to Java 11+)

---

## ⚙️ Environment Variables

Before running, set your Exchange Rate API key:

```bash
export EXCHANGE_API_KEY=your_api_key_here
```

Optional for debug mode:

```bash
export TESTING_MODE=true
```

---

## 📂 Project Structure

```
src/
├── Main.java              # Entry point and menu logic
├── CurrencyConverter.java # Core conversion logic and HTTP request handling
├── Currency.java          # POJO for parsed API data
├── MenuOption.java        # Record to represent each conversion option
```

---

## 🚀 How to Run

1. Clone the repo:

   ```bash
   git clone https://github.com/yourusername/currency-converter-java.git
   cd currency-converter-java
   ```

2. Set your API key and (optionally) enable test mode.

3. Build and run with Maven:

   ```bash
   mvn compile exec:java -Dexec.mainClass="Main"
   ```

---

## ✅ Why It Stands Out

This project goes beyond the minimum by delivering:

* ✅ **Clean code**
* ✅ **Scalability**
* ✅ **User-centric output**
* ✅ **Proactive error handling**
* ✅ **Respect for best practices**

All of this while staying 100% aligned with the original challenge specs. 💪