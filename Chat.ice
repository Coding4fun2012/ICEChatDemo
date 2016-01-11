module Chat {
 
interface MessageReceiver {
    void onMessage(string sender, string msg);
};
 
dictionary<string, MessageReceiver*> UserList;
  
interface Room {
    bool login(string user, MessageReceiver* receiver);
    void logout(string user);
    void sendMessage(string user, string message);
};
 
};