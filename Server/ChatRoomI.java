public class ChatRoomI extends Chat._RoomDisp 
{
	java.util.Map<String, Chat.CMDReceiverPrx> users = new java.util.HashMap<String, Chat.CMDReceiverPrx>();
    public boolean login(String user, Chat.CMDReceiverPrx receiver, Ice.Current current)
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



  public  void sendCMD(String user, String CMD ,  Ice.Current current)
    {
		broadcast(user, CMD);
	}
    
  void broadcast(String user,String CMD){
	  for (java.util.Map.Entry<String, Chat.CMDReceiverPrx> i : users.entrySet()) {
          String name = i.getKey();
          Chat.CMDReceiverPrx Receiver = i.getValue();
          notif(Receiver,name,CMD);
          }
	}
  boolean notif(Chat.CMDReceiverPrx receiver, String sender, String CMD)
  {
	  boolean ret = true;
	      try {
        receiver.onCMD(sender, CMD);
    } catch(Exception e) {
        ret = false;
    }
    return ret;
}
}
