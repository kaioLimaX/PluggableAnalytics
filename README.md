# PluggableAnalytics

![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-purple?logo=kotlin)
![Android](https://img.shields.io/badge/Android-SDK-green?logo=android)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-blue)
![Pattern](https://img.shields.io/badge/Pattern-Composite-orange)
![State](https://img.shields.io/badge/State-StateFlow-teal)
![DI](https://img.shields.io/badge/DI-Koin-brightgreen)

Arquitetura de analytics desacoplada para Android, criada a partir de um problema real vivido em projeto profissional: ao adicionar um segundo provedor de analytics, a integração se tornou trabalhosa, altamente acoplada e acabou resultando em soluções improvisadas difíceis de manter.

Este projeto demonstra uma abordagem limpa, escalável e evolutiva para trabalhar com múltiplos provedores de analytics (como Firebase e Adjust), sem refatorar telas ou ViewModels, mantendo clareza arquitetural e ergonomia de uso.

---

## 🧩 Objetivo

- Evitar acoplamento de SDKs de analytics (Firebase, Adjust, etc.) na UI e nos ViewModels
- Padronizar eventos de analytics, evitando uso de strings soltas espalhadas pelo código
- Permitir múltiplos destinos por evento
- Tornar a adição ou remoção de provedores um ajuste de DI, não um refactor no app inteiro
- Manter uma API simples e ergonômica para disparo de eventos: `Analytics(event)`

---

## ❌ Problema comum em apps reais

Em muitos aplicativos Android, analytics acaba sendo implementado de forma acoplada:

- SDKs chamados diretamente em ViewModels ou Fragments
- Eventos definidos como strings (`"click_button"`, `"ClickButton"`, `"clicked"`)
- Falta de padronização de nomes e parâmetros
- Ao adicionar um novo provedor:
  - refatoração em vários pontos
  - duplicação de chamadas
  - aumento da complexidade e da chance de erro

---

## ✅ Solução proposta

- Eventos tipados utilizando `sealed class`
- Contrato único de envio (`AnalyticsTracker`)
- Provedores isolados na camada de dados
- Uso do Composite Pattern para múltiplos trackers
- Roteamento de eventos por destino (`AnalyticsDestination`)
- Facade global para simplificar o uso sem poluir ViewModels

---

## 🔧 Tecnologias e Ferramentas Utilizadas

- **Kotlin**
- **Android SDK**
- **MVVM**
- **StateFlow**
- **Clean Architecture**
- **Koin** (injeção de dependência)
- **Composite Pattern**
- **Arquitetura preparada para múltiplos provedores de analytics** (ex: Firebase, Adjust)

---

## 🧠 Decisões Arquiteturais

### Eventos no domínio
Eventos de analytics representam acontecimentos relevantes do domínio da aplicação e não detalhes de plataforma.
Por isso, ficam na camada `domain`, desacoplados de SDKs externos.

### Contrato único (`AnalyticsTracker`)
ViewModels e UI conhecem apenas um contrato.
Cada provedor decide como enviar o evento, não quando nem por que.

### Composite Pattern
O `CompositeAnalyticsTracker` permite enviar o mesmo evento para múltiplos provedores sem duplicar lógica ou espalhar condicionais pelo código.

### Roteamento por evento
Cada evento define explicitamente para quais plataformas pode ser enviado, evitando regras escondidas ou acoplamento com providers.

### Facade global
A facade `Analytics(event)` foi uma decisão consciente para melhorar ergonomia de uso, evitando injeção excessiva sem abrir mão do desacoplamento.

---

## 🗂️ Organização dos Packages (estrutura real do projeto)

```
com.example.interfaceexample
├── analytics
│   ├── data
│   │   └── providers
│   │       ├── AdjustAnalyticsTracker.kt
│   │       ├── FirebaseAnalyticsTracker.kt
│   │       └── CompositeAnalyticsTracker.kt
│   │
│   ├── domain
│   │   ├── events
│   │   │   └── MainAnalyticsEvents.kt
│   │   ├── AnalyticsDestination.kt
│   │   └── AnalyticsTracker.kt
│   │
│   ├── facade
│   │   └── Analytics.kt
│   │
│   └── di
│       ├── AnalyticsModule.kt
│       ├── App.kt
│       └── AppModule.kt
│
├── presentation
│   └── main
│       ├── MainScreen.kt
│       ├── MainUIAction.kt
│       ├── MainUIState.kt
│       └── MainViewModel.kt
│
├── ui
│   └── theme
│
└── MainActivity.kt
```

---

## 🚀 Exemplo de uso

### Definição do evento

```kotlin
data class NameChanged(val newName: String) : MainAnalyticsEvents(
    name = "name_changed",
    params = mapOf("new_name" to newName),
    destinations = setOf(AnalyticsDestination.FIREBASE)
)
```

### Disparo no ViewModel

```kotlin
override fun updateNome(nome: String) {
    _uiState.value = _uiState.value.copy(nome = nome)
    Analytics(MainAnalyticsEvents.NameChanged(nome))
}
```

---

## 🧯 Tratamento de falhas

Falhas no envio de analytics não quebram o app:

- Todos os trackers retornam `Result<Unit>`
- Erros são logados
- O fluxo da aplicação segue normalmente


---

## 📄 Licença

MIT License
