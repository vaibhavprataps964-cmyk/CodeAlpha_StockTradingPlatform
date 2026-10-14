# Artificial Intelligence Chatbot

![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![GUI](https://img.shields.io/badge/Interface-Java%20Swing-green.svg)
![Domain](https://img.shields.io/badge/Domain-Java%20Programming-blue.svg)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen.svg)

## Project Overview
This project is developed as **TASK 3: Artificial Intelligence Chatbot** for the **CodeAlpha Java Programming Internship**[cite: 2]. It is an interactive desktop chatbot that uses Natural Language Processing (NLP) techniques and rule-based logic to respond to user queries via a clean graphical user interface (GUI)[cite: 2].

## Key Features
- **Interactive GUI:** Built using Java Swing (`JFrame`, `JTextArea`, `JTextField`) for real-time user interaction[cite: 2].
- **NLP Preprocessing:** Cleans and normalizes incoming text to extract keywords and intents[cite: 2].
- **Rule-Based Answers:** Pre-trained FAQ database that answers queries about internships, certificates, tasks, and GitHub instructions[cite: 2].
- **Dynamic Responses:** Detects questions regarding the current system date and time.
- **Graceful Fallback:** Provides polite fallback replies when an input is outside its knowledge base.

## Tech Stack
- **Language:** Java (JDK 8 or above)
- **GUI Framework:** Java Swing / AWT
- **Core Concepts:** NLP Preprocessing, HashMaps (Knowledge Base), Event Handling, Date-Time API

## Project Structure
```text
CodeAlpha_AIChatbot/
│
├── AIChatbot.java   # Complete Java Swing GUI and Bot Logic
└── README.md        # Project documentation
