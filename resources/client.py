#-*- coding: utf-8 -*-
'''
KTN-project 2013 / 2014
'''
import socket
import json

class Client(object):
    def __init__(self):
        self.connection = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def start(self, host, port):
        self.connection.connect((host, port))
        user = False
        while True:
            choice = raw_input('Choices: |Create user| |Write message| |Logout| |Exit| |Help|: ')
            choice = choice.lower()
            
            if choice == 'create user' and user == False:
                self.createUser()
                user = True
            elif choice == 'logout':
                self.logout()
                user = False
            elif choice == 'write message':
                while True:
                    if self.createMessage():
                        continue
                    else:
                        break
            elif choice == 'exit':
                self.exit()
                break
            elif choice == 'help':
                self.help()
            elif choice == 'create user' and user:
                print 'Error: You have already created a user. You have to log out before you can create a new one'
            else:
                print 'Invalid command'
        

    def message_received(self, message, connection):
        print ('Message received: ' + message)

    def connection_closed(self, connection):
        self.connection.close()

    def send(self, data):
        self.connection.sendall(data)

    def force_disconnect(self):
        pass
    
    def exit(self):
        request = {'request' : 'exit'}
        request = json.dumps(request)
        self.send(request)
        self.connection_closed(self.connection)
    
    def help(self):
        print 'The first thing you need to do is to create a user; write \'create user\' to do this.'
        print 'After this you can start writing messages; write \'write message\' to do this.'
        print 'To logout of the server write \'logout\''
        print 'Write \'exit\' when you get the choices to exit the whole program.' 
    
    def createMessage(self):
        message = raw_input('Enter a message(write \'stop\' to go back to choices): ')
        
        if message == 'stop':
            return False        
        # Konstruer et JSON objekt som som skal
        # sendes til serveren
        request = {'request': 'message', 'message': message}

        # Lag en streng av JSON-objektet
        request = json.dumps(request)

        # Send meldingen til serveren
        self.send(request)

        # Motta data fra serveren
    
        received_data = self.connection.recv(1024)
    
        self.message_received(received_data, self.connection)
        
        return True
    
    def createUser(self):
        while True:
            username = raw_input('Enter your username(can only contain alphanumerical characters and underscores): ')
            request = {'request' : 'login', 'username' : username}
            request = json.dumps(request)
            self.send(request)
            received_data = self.connection.recv(1024)
            received_data = json.loads(received_data)
            if 'error' in received_data:
                print ('Message received: ' + received_data['error'])
            else:
                print ('Messages so far: ')
                for messages in received_data['messages']:
                    print messages
                break
    
    def logout(self):
        request = json.dumps({'request': 'logout'})
        self.send(request)
        received_data = self.connection.recv(1024)
        received_data = json.loads(received_data)
        if('error' in received_data):
            print ('Message received: ' + received_data['error'])
        
        
if __name__ == "__main__":
    client = Client()
    client.start('localhost', 9999)
    
