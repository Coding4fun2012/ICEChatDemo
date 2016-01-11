module Chat {
 
interface CMDReceiver {
    void onCMD(string sender, string cmd);
};
 
dictionary<string, CMDReceiver*> UserList;
  
interface Room {
    bool login(string user, CMDReceiver* receiver);
    void logout(string user);
    void sendCMD(string user, string CMD);
};
 
};