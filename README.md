# Osinton


**Osinton is an open-source OSINT (Open Source Intelligence) tool designed to perform structured investigations on individuals using publicly available information.**

The project allows users to search, correlate, and analyze digital footprints such as names, usernames, email addresses, and phone numbers by leveraging web search engines and optional local AI-assisted query generation.

---

## Features

- Field-based OSINT search:
  - First name
  - Last name
  - Username
  - Email address
  - Phone number
- Web search powered by SerpAPI (Google engine)
- Iterative and structured query generation
- Optional local AI assistance via Ollama
- No cloud AI APIs required
- Professional Swing-based graphical interface
- No hardcoded API keys
- Cross-platform (Linux, Windows, macOS)

---

## Requirements

- Java 21 or later
- Maven
- A SerpAPI account and API key
- (Optional) Ollama installed locally for AI-assisted query generation

---

## Getting a SerpAPI Key

Osinton uses **SerpAPI** to perform Google-based web searches.

### How to obtain a SerpAPI key

1. Visit:  
   https://serpapi.com/

2. Create an account (a free tier is available)

3. After logging in, open your **Dashboard**

4. Copy your **API Key**

The API key **must not be hardcoded** in the source code.  
Osinton allows you to insert the key directly in the graphical interface at runtime.

---

## Installing Ollama (Optional – Local AI)

Osinton can optionally use **Ollama** to improve OSINT query generation using a local Large Language Model, without relying on paid cloud services.

### Install Ollama on Linux

```
curl -fsSL https://ollama.com/install.sh | sh
```
## Installing Mistral

```
ollama pull mistral
```

## Start ollama

```
ollama serve
```

## Runnning the program
```
java -jar osinton.jar
```


## Donations

If you find this project useful and would like to support its development you can buy me a coffee

Any contributions is greatly appreciated and helps keep the project alive and evolving

## My btc address
```
bc1qpkncf9mfxhp36jzfd6fhrhy05k2nw3akud7jsa
```







