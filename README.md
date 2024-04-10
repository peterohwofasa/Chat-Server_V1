Chat Server Application

Welcome to the Chat Server Application! This project is a Java-based server application that enables users to join chat rooms, send messages, and access chat history. 
It provides both RESTful endpoints and WebSocket support for real-time communication.


Features

Mandatory Features:

User Authentication: Basic username/password login authentication.

Single Chat Room: Creation of a single chat room upon server startup.

Persistent Storage: Chat messages are stored in a database.

Sending and Receiving Messages: Users can send and receive messages in the chat room.

RESTful Endpoints: Provides RESTful endpoints for message sending and retrieval.

Unit Testing: Includes unit tests for AuthService, MessageService, AuthController, and MessageController.


Bonus Features:

WebSocket Support: Real-time chat communication using WebSocket instead of REST API.

Message Deletion: Users can delete messages.

CI/CD Skeleton: Continuous Integration/Continuous Deployment skeleton for automated building and deployment.

Server Scalability: Ability to scale the server to handle increased traffic.


Installation

Clone the repository:
"git clone https://github.com/''/chat-server.git"

Navigate to the project directory:

cd chat-server

Build the project using Maven:

"mvn clean install"

Run the application:

"java -jar target/chat-server.jar"


Usage

RESTful Endpoints:
POST /register: Register a new user with a username and password.

POST /login: Authenticate a user with a username and password.

POST /send-message: Send a message to the chat room.


WebSocket Endpoint:

WebSocket URL: ws://localhost:8080/websocket

Subscribe to /topic/messages to receive chat messages in real-time.


Testing

Use tools like Postman to test the RESTful endpoints and WebSocket communication. Ensure that messages are persisted in the database and retrieved correctly.


Contributing

Contributions are welcome! Please feel free to fork the repository, make your changes, and submit a pull request.
