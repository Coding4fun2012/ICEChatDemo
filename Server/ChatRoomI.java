public class ChatRoomI extends Chat._RoomDisp 
{
	java.util.Map<String, Chat.MessageReceiverPrx> users = new java.util.HashMap<String, Chat.MessageReceiverPrx>();
    public boolean login(String user, Chat.MessageReceiverPrx receiver, Ice.Current current)
    {
	  
		
		if(users.containsKey(user)){
			return false;
			}
		users.put(user,receiver);
		broadcast(user, "---login---");
		return true;
    }

   public void logout(String user, Ice.Current current)
    {
		
	broadcast(user, "---logout---");
	}



  public  void sendMessage(String user, String message ,  Ice.Current current)
    {
		broadcast(user, message);
	}
    
  void broadcast(String user,String message){
	  for (java.util.Map.Entry<String, Chat.MessageReceiverPrx> i : users.entrySet()) {
          String name = i.getKey();
          Chat.MessageReceiverPrx Receiver = i.getValue();
          notif(Receiver,name,message);
          }
	}
  boolean notif(Chat.MessageReceiverPrx receiver, String sender, String message)
  {
	  boolean ret = true;
	      try {
        receiver.onMessage(sender, message);
    } catch(Exception e) {
        ret = false;
    }
    return ret;
}
}
