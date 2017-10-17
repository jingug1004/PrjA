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
	
	// �� ���� ������ ������ ����� �غ� �� �� ȣ��
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		connectedUsers.add(session);
		System.out.println(session.getId() + "�� ����");
		System.out.println(session.getRemoteAddress().getHostName());
		System.out.println("����غ� �� ������ ����:" + connectedUsers.toString());
	}

	/**
	 * �� ���� �̺�Ʈ�� ó��
	 *
	 * 1. Send : Ŭ���̾�Ʈ�� �������� �޽����� ���� 2. Emit : ������ ����Ǿ� �ִ� Ŭ���̾�Ʈ���� �޽����� ����
	 *
	 * @param WebSocketSession
	 *            �޽����� ���� Ŭ���̾�Ʈ
	 * @param TextMessage
	 *            �޽����� ����
	 */

	/*
	 * Payload : ����ڰ� ���� �޽���
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
	 * Ŭ���̾�Ʈ�� ������ ������ �������� ����Ǵ� �޼ҵ�
	 *
	 * @param WebSocketSession
	 *            ������ ���� Ŭ���̾�Ʈ
	 * @param CloseStatus
	 *            ���� ����(Ȯ�� �ʿ���)
	 */

	// ������ ������ ��������
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		for (WebSocketSession webSocketSession : connectedUsers) {

			// �ڽ��� ���� �޽����� ���� �ʴ´�.

			if (!session.getId().equals(webSocketSession.getId())) {
				// webSocketSession.sendMessage(new
				// TextMessage(session.getRemoteAddress().getHostName() +
				// "�����߽��ϴ�."));
			}
		}
		System.out.println(session.getId() + "���� �����߽��ϴ�.");
		connectedUsers.remove(session);

	}

	// Controller�κ��� param �� ���� ���� ��� doMessage �̿��Ͽ� ������ ����. 
	public void doMessage(String ipaddr, String source) throws Exception {
		System.out.println("socket.doMessage");
		System.out.println(source);
		System.out.println("do�޼���:" + connectedUsers.toString());
		System.out.println(ipaddr);

		for (WebSocketSession webSocketSession : connectedUsers) {
			if (ipaddr.equals(webSocketSession.getRemoteAddress().getHostName())) {
				webSocketSession.sendMessage(new TextMessage(source));
				System.out.println("websocket:" + webSocketSession);
			}
		}
	}
}
