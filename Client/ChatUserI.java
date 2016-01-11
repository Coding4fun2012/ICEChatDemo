public class ChatUserI extends Chat._CMDReceiverDisp 
{

  public  void onCMD(String user, String CMD ,  Ice.Current current)
    {
		System.out.println("get cmd from "+user+":"+CMD);
		}
}
