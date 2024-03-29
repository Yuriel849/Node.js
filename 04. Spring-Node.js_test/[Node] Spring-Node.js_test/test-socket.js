// Require HTTP module (to start server) and Socket.IO
var http = require('http'), io = require('socket.io');

// Start the server at port 12050
var server = http.createServer(function(req, res) {
    // Send HTML headers and message
    res.writeHead(200, { 'Content-Type' : 'text/html' });
    res.end('<h1>Hello guest!</h1>');
});
server.listen(12050);

// Create a Socket.IO instance, passing it to the server
var socket = io.listen(server);

// Add a connect listener
socket.on('connection', function(client) {
    // Success, now listen to messages to be received
    client.on('message', function(event) {
        console.log('Received message from client!', event);
    });
    client.on('disconnect', function() {
        clearInterval(interval);
        console.log('Server has disconnected');
    });
    
    // Create periodical which sends a message to the client every 5 seconds
    var interval = setInterval(function() {
        client.send('This is a message from the server!   ' + new Date().getTime());
    }, 5000);
})
