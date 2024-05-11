## Compulsory component - all requirements

Server side
```bash
Server started on port 12345
New client connected: /127.0.0.1:52389
Client request: hello
Client request: exit
New client connected: /127.0.0.1:52400
Client request: stop
Server stopped
```

Client side
```bash
Connected to server at localhost:12345
hello
Server response: Server received the request: hello
exit
```
```bash
Connected to server at localhost:12345
stop
Server response: Server stopped
Server has stopped. Closing client.
```
