# FastFood Service

REST API for managing fast-food orders, built with **Spring Boot**. It is an exercise in applying **data structures** inside a real web service: orders are stored in a custom linked list, dispatched in FIFO order through a queue, and every operation can be undone through a stack-based history.

## Tech Stack

- Java 25
- Spring Boot 3.5 (Web, Actuator)
- springdoc-openapi (Swagger UI)
- Maven

## How the Data Structures Are Used

| Structure | Implementation | Purpose |
|---|---|---|
| Singly linked list | Custom `SinglyLinkedList` | Stores every order and supports search and removal by id |
| Queue | `PedidoQueue` | Pending orders, dispatched first in, first out |
| Stack | `OperationStack` | Operation history used to roll back the last action |
| Recursion | `calcularTotalRecursivo` | Walks the linked list node by node to sum all order amounts |

Data is kept in memory, so it resets each time the application restarts.

## Features

- Register orders with validation (customer name, description and an amount greater than 0)
- Dispatch the oldest pending order
- Cancel an order and remove it from the pending queue
- Undo the last operation (create, cancel or dispatch)
- Order statistics by status: `REGISTRADO`, `DESPACHADO`, `CANCELADO`

## Getting Started

```bash
./mvnw spring-boot:run
```

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Health check: `http://localhost:8080/actuator/health`

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/pedidos` | List all orders |
| `POST` | `/pedidos` | Create an order |
| `GET` | `/pedidos/{id}` | Get an order by id |
| `PUT` | `/pedidos/cancelar/{id}` | Cancel an order |
| `PUT` | `/pedidos/despachar` | Dispatch the next pending order |
| `PUT` | `/pedidos/rollback` | Undo the last operation |
| `GET` | `/pedidos/estadisticas` | Order statistics |
| `GET` | `/pedidos/total` | Total amount, calculated recursively |

### Example

```json
POST /pedidos
{
  "nombreCliente": "Carlos",
  "descripcion": "2 burgers and a soda",
  "monto": 189.50
}
```

Response:

```json
{
  "id": 1,
  "nombreCliente": "Carlos",
  "descripcion": "2 burgers and a soda",
  "monto": 189.5,
  "estado": "REGISTRADO"
}
```

## Project Structure

```
src/main/java/com/example/FastFood_Service
├── controller       REST endpoints
├── service          Business logic and rollback handling
├── model            Order, request, history and statistics classes
└── datastructures   Linked list, queue and stack
```
