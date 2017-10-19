package kr.co.comes.projectA.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler {

	private static List<WebSocketSession> connectedUsers = new ArrayList<WebSocketSession>();

	WebSocketSession wssession;

	// 웹 소켓 연결이 열리고 사용이 준비 될 때 호출
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		connectedUsers.add(session);
		System.out.println(session.getId() + "님 접속");
		System.out.println(session.getRemoteAddress().getHostName());
		System.out.println("사용준비 전 웹소켓 접속:" + connectedUsers.toString());
	}

	/**
	 * 두 가지 이벤트를 처리
	 *
	 * 1. Send : 클라이언트가 서버에게 메시지를 보냄 2. Emit : 서버에 연결되어 있는 클라이언트에게 메시지를 보냄
	 *
	 * @param WebSocketSession
	 *            메시지를 보낸 클라이언트
	 * @param TextMessage
	 *            메시지의 내용
	 */

	/*
	 * Payload : 사용자가 보낸 메시지
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("message:"+message);
		String hostName = "";

		for (WebSocketSession webSocketSession : connectedUsers) {
			hostName = webSocketSession.getRemoteAddress().getHostName();
			System.out.println(hostName);
			if (hostName.equals(session.getRemoteAddress().getHostName())) {
				webSocketSession.sendMessage(new TextMessage(message.getPayload()));
				break;
			}
		}

	}

	/**
	 * 클라이언트가 서버와 연결을 끊었을때 실행되는 메소드
	 *
	 * @param WebSocketSession
	 *            연결을 끊은 클라이언트
	 * @param CloseStatus
	 *            연결 상태(확인 필요함)
	 */

	// 웹소켓 연결이 닫혔을때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		for (WebSocketSession webSocketSession : connectedUsers) {

			// 자신이 보낸 메시지를 받지 않는다.

			if (!session.getId().equals(webSocketSession.getId())) {
				// webSocketSession.sendMessage(new
				// TextMessage(session.getRemoteAddress().getHostName() +
				// "퇴장했습니다."));
			}
		}
		System.out.println(session.getId() + "님이 퇴장했습니다.");
		connectedUsers.remove(session);

	}

	// Controller로부터 param 값 전달 받을 경우 doMessage 이용하여 웹소켓 전달.
	public void doMessage(String ipaddr, String source) throws Exception {
		System.out.println("socket.doMessage");
		System.out.println(source);
		System.out.println("do메세지:" + connectedUsers.toString());
		System.out.println(ipaddr);

		for (WebSocketSession webSocketSession : connectedUsers) {
			if (ipaddr.equals(webSocketSession.getRemoteAddress().getHostName())) {
				webSocketSession.sendMessage(new TextMessage(source));
				System.out.println("websocket:" + webSocketSession);
			}
		}
	}
}
