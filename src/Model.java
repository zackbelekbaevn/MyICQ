import java.io.*;
import java.net.*;
import java.util.*;


public class Model extends Thread {

    protected DatagramSocket socket = null;
    protected boolean moreQuotes = true;
    private Viewer viewer;

    public Model(Viewer viewer) {
        this.viewer = viewer;

        try {
            socket = new DatagramSocket(4445);
        } catch(IOException ioe) {
            System.out.println();
        }
        start();
    }

    public void clear() {
        viewer.clear();
    }
    public void messageTextClear() {
        viewer.messageTextClear();
    }


    public void send() {


        String addressName = viewer.getIpAddress();
        String text = viewer.getMessage();
        text = "Zakir Belekbaev:" + text;

        if (addressName.equals("") || text.equals("")) {
            System.out.println("Usage: Internet Address.\nType message for sending.");
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Usage: Internet Address.\nType message for sending.");
            return;
        }

        try {
            // get a datagram socket
            DatagramSocket socket = new DatagramSocket();

            // send request
            byte[] buf = text.getBytes();



            InetAddress address = InetAddress.getByName(addressName);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

//////////////
            String received = new String(packet.getData(), 0, packet.getLength());
            viewer.received(received);
//////////

            socket.close();

        } catch(IOException ioe) {
            System.out.println(ioe);
        }

    }

    public void run() {

        while (moreQuotes) {
            try {
                byte[] buf = new byte[3000];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                buf = packet.getData();
                socket.receive(packet);



                String received = new String(packet.getData(), 0, packet.getLength());

                viewer.received(received);


//		System.out.println("Quote of the Moment: " + received);

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }


}