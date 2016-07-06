package com.eip.red.caritathelp.Activities.Main;

import android.os.Build;

import com.eip.red.caritathelp.Activities.Main.ViewPager.MyPagerAdapter;
import com.eip.red.caritathelp.Activities.Main.ViewPager.MyThirdPage;
import com.google.gson.JsonObject;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by pierr on 04/07/2016.
 */

public class MyWebSocket {

    private MyPagerAdapter  pagerAdapter;
    private WebSocketClient webSocketClient;

    public MyWebSocket(MyPagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
    }

    public void connectWebSocket(final String token) {
        URI uri;

        try {
            uri = new URI("ws://api.caritathelp.me:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                JsonObject json = new JsonObject();

                json.addProperty("token", "token");
                json.addProperty("token_user", token);
                webSocketClient.send(json.toString());
            }

            @Override
            public void onMessage(String s) {
                ((MyThirdPage) pagerAdapter.getFragment(2)).getNotificationsView().getPresenter().onMessage();

                /* DEBUG */
//                System.out.println("Websocket MSG : " + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                /* DEBUG */
//                System.out.println("CLOSE");
            }

            @Override
            public void onError(Exception e) {
                /* DEBUG */
//                System.out.println("Error : " + e.toString());
            }
        };
        webSocketClient.connect();
    }

    public void sendMessage() {
//        webSocketClient.send("COCO");
    }
}
