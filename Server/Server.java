public class Server {
    public static void
    main(String[] args)
    {
        int status = 0;
        Ice.Communicator ic = null;
        try {
 
            // Server implementation here...
            ic = Ice.Util.initialize(args);
            Ice.ObjectAdapter adapter =
                ic.createObjectAdapterWithEndpoints("Chat.RoomAdapter", "default -p 10000");
            Ice.Object object = new ChatRoomI();
            adapter.add(object, ic.stringToIdentity("Chat.Room"));
            adapter.activate();
            ic.waitForShutdown();
 
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
