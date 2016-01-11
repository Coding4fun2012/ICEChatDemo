public class Client {
    public static void
    main(String[] args)
    {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);
            Ice.ObjectPrx base = ic.stringToProxy("Chat.Room:default -p 10000");
            Chat.RoomPrx chatRoom = Chat.RoomPrxHelper.checkedCast(base);
            if (chatRoom == null)
                throw new Error("Invalid proxy");
                
            Ice.ObjectAdapter adapter =
                ic.createObjectAdapterWithEndpoints("Chat.UserAdapter", "default");
            Ice.Object object = new ChatUserI();
            adapter.add(object, ic.stringToIdentity("Chat.User"));
            adapter.activate();
            
            //.ObjectPrx baseUser = ic.stringToProxy("Chat.User");
            Chat.MessageReceiverPrx userPrx = Chat.MessageReceiverPrxHelper.uncheckedCast(adapter.createProxy(ic.stringToIdentity("Chat.User")));
            if (userPrx == null)
                throw new Error("Invalid proxy");
                
            if(!chatRoom.login("user1", userPrx))
            {
				System.out.println("user1 login error");
			}
			chatRoom.sendMessage("user1","cmd xxxx");
			chatRoom.logout("user1");
 

        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}
