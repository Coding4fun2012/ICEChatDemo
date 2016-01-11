public class ChatUserI extends Chat._MessageReceiverDisp 
{

  public  void onMessage(String user, String message ,  Ice.Current current)
    {
		System.out.println("get cmd from "+user+":"+message);
		}
}
