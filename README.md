# MULTITHREADED-CHAT-APPLICATION-

*COMPANY* : CODETECH IT SOLUTIONS

*NAME* : JARIPITI POOJITHA

*INTERN ID* : CT04DF549

*DURATION* : 4-WEEKS

*MENTOR* : NEELA SANTOSH

*DESCRIPTION FOR SERVER* :

The provided Java program implements a basic multithreaded chat server using sockets, allowing multiple clients to connect and communicate with each other. The main class, ChatServer, listens for incoming client connections on a specific port (1234) using a ServerSocket. When a new client connects, the server accepts the connection and starts a new thread (ClientHandler) to handle communication with that specific client. This approach allows multiple clients to chat concurrently without blocking the server from accepting new connections.

A shared Set of PrintWriter objects, named clientWriters, is used to broadcast messages to all connected clients. Each time a new client joins, their output stream is added to this set, and a welcome message is broadcast to inform others of the new connection. The ClientHandler class manages the interaction with an individual client. It reads the client's name and subsequent messages using BufferedReader, and sends messages using PrintWriter. Whenever a message is received from a client, it is broadcast to all other clients in the format "[name]: message".

Thread synchronization is used when accessing the clientWriters set to avoid concurrency issues. If a client disconnects, their writer is removed from the set and a message is sent to the remaining users notifying them of the departure. The program also includes basic exception handling to ensure stability during network errors or disconnections. Overall, this server-side code lays the foundation for a simple chat application and demonstrates key concepts in socket programming, multithreading, and inter-client communication in Java.

*DESCRIPTION FOR CLIENT* :

The given Java program is a simple chat client designed to connect to a server and participate in a group chat session. The ChatClient class establishes a connection to a server running on localhost at port 1234, which must correspond to the server defined in the ChatServer program. Once connected, it uses input and output streams to send and receive messages. The client prompts the user to enter their name, which is sent to the server to identify the user in the chat. This setup enables personalized communication where each message includes the sender’s name.

The program uses two threads to handle input and output simultaneously. The main thread is responsible for capturing user input from the console and sending messages to the server using a PrintWriter. If the user types "exit", the client terminates the connection and exits gracefully. The second thread, created using a lambda expression, continuously listens for incoming messages from the server and prints them to the console in real-time. This concurrent design ensures that receiving messages from the server does not block the client from typing and sending messages, thereby allowing seamless communication.

The program also includes basic error handling to detect connection issues or server disconnection, ensuring that the user is informed if something goes wrong. Overall, this client-side code demonstrates core networking concepts in Java, such as sockets, multithreading, and stream handling. When combined with the corresponding ChatServer, this program allows multiple clients to engage in a live group chat, making it an effective example of a multithreaded client-server application.


*OUTPUT* :



