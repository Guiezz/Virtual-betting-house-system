# 🏟️ Virtual Betting House System

Este projeto é um sistema de **casa de apostas virtual baseado em console**, desenvolvido em **Java**.  
Ele simula as operações de uma casa de apostas, permitindo que **administradores gerenciem eventos** e **apostadores realizem apostas**.

---

## ✨ Funcionalidades

O sistema é dividido em **duas personas principais**: `Administrador` e `Apostador`.

### 👨‍💼 Administrador (`Admin.java`)

- **Criação de Eventos**: Pode criar novos eventos esportivos para apostas.
- **Edição de Eventos**: Pode editar informações de eventos existentes.
- **Finalização de Eventos**: Define o resultado de um evento, atualizando automaticamente o status das apostas como _"ganha"_ ou _"perdida"_.
- **Visualização de Apostas**: Permite ver todas as apostas realizadas em um evento específico.

### 🧑‍💰 Apostador (`Gambler.java`)

#### 💳 Gestão de Saldo

- **Depositar**: Adiciona fundos à conta.
- **Sacar**: Retira fundos, se o saldo for suficiente.

#### 🎲 Apostas

- **Realizar Apostas**: Faz uma aposta em um evento aberto, deduzindo o valor do saldo.

#### 📊 Consultas

- **Extrato de Transações**: Visualiza depósitos, saques e apostas.
- **Histórico de Apostas**: Lista todas as apostas realizadas.
- **Saldo Atual**: Mostra o saldo disponível.

---

## 🚀 Como Executar

Este projeto **não utiliza Maven ou Gradle**. A execução é feita manualmente por linha de comando:

1. Navegue até a pasta `src`:

   ```bash
   cd path/to/your/project/src
2. Compile os arquivos .java:
      ```bash
   javac *.java types/*.java utils/*.java
4. Execute a classe principal:
   ```bash
   java Main
O programa executará um fluxo de exemplo definido na classe Main, demonstrando a criação de um evento, uma aposta e a finalização do evento.


---
## 🧬 Conceitos do Domínio

  - **User.java:** Classe abstrata com propriedades comuns entre Admin e Gambler, como nome, e-mail e autenticação via SHA-256.

  - **Event.java:** Representa um evento esportivo com esporte, descrição, status (PENDING, COMPLETED) e resultado.

  - **Bet.java:** Representa uma aposta, com valor, cotação (odd), resultado esperado e status (PENDING, WON, LOST).

  - **Transaction.java:** Registra movimentações financeiras do apostador: DEPOSIT, WITHDRAWAL e BET.
