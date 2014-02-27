'''
KTN-project 2013 / 2014
Very simple server implementation that should serve as a basis
for implementing the chat server
'''
import SocketServer
import json
from datetime import datetime
import re

'''
The RequestHandler class for our server.

It is instantiated once per connection to the server, and must
override the handle() method to implement communication to the
client.
'''
clients = []
messages = []

class CLientHandler(SocketServer.BaseRequestHandler):
    def handle(self):
        username = ''
        # Get a reference to the socket object
        self.connection = self.request
        # Get the remote ip adress of the socket
        self.ip = self.client_address[0]
        # Get the remote port number of the socket
        self.port = self.client_address[1]
        print ('Client connected @' + self.ip + ':' + str(self.port))
        # Wait for data from the client
        while True:
            try:
                data = self.connection.recv(1024)
                data = json.loads(data)
            except:
                print ('Client disconnected!')
                break
            
            if(data['request'] == 'login'):
                username = self.login(data)
            elif(data['request'] == 'message'):
                self.message(data, username)
            elif(data['request'] == 'logout'):
                username = self.logout(data, username)
            elif(data['request'] == 'exit'):
                self.exit(username)  
    
    #Removes the client thats disconnecting from the clientlist.
    def exit(self, username):
        if username != '':
            clients.remove(username)
    
    #Method that handles clientlogout.
    def logout(self, data, username):
        if(username != ''):
            response = {'response' : 'logout', 'username' : username}
            print username + ' has now logged out!'
            clients.remove(username)
            username = ''
        else:
            response = {'response' : 'logout', 'error' : 'Not logged in!', 'username' : username}
        response = json.dumps(response)
        self.connection.sendall(response) 
        return username   
    
    #Method that handles the messagecommunication between the server and the client.
    def message(self, data, username):
        # Return the string in uppercase
        if(username != ''):
            response = {'response' : 'message', 'message' : data['message']}
            message = datetime.now().strftime("%Y-%m-%d %H:%M") + ' ' + username + ': ' + data['message']
            print message
            messages.append(message)
        else:
            response = {'response' : 'message', 'error' : 'You are not logged in!'}
        response = json.dumps(response)
        self.connection.sendall(response)
    
    #Method that handles the login
    def login(self, data):
        if isValid(data['username']) and data['username'] not in clients:
            response = {'response' : 'login', 'username' : data['username'], 'messages' : messages}
            clients.append(data['username'])
            username = data['username']
            print 'User: ' + username + ' has now joined the chatroom'
        elif isValid(data['username']) == False :
            response = {'response' : 'login','error' : 'Invalid username', 'username' : data['username']}
            username = ''
        elif data['username'] in clients:
            response = {'response' : 'login','error' : 'Username already taken', 'username' : data['username']}
            username = ''
              
        response = json.dumps(response)
        self.connection.sendall(response)
        return username
    
#Method that checks if the username is valid.
def isValid(string):
    if re.match('^[\w]+$', string):
        return True 
    return False          
            
'''
This will make all Request handlers being called in its own thread.
Very important, otherwise only one client will be served at a time
'''


class ThreadedTCPServer(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    pass

if __name__ == "__main__":
    HOST = 'localhost'
    PORT = 9999

    # Create the server, binding to localhost on port 9999
    server = ThreadedTCPServer((HOST, PORT), CLientHandler)

    # Activate the server; this will keep running until you
    # interrupt the program with Ctrl-C
    server.serve_forever()
