package com.jcope.vnc.shared;

public class StateMachine
{
	public enum CONNECTION_STATE
	{
		INIT,
		SELECTING_SESSION_TYPE,
		AUTHENTICATING_INPUT_ENABLED,
		AUTHENTICATING_VIEW_ONLY,
		SELECTING_SCREEN_INPUT_ENABLED,
		SELECTING_SCREEN_VIEW_ONLY,
		VIEW_WITH_INPUT_ENABLED,
		VIEW_ONLY,
	};
	
	public enum CLIENT_EVENT
	{
		SELECT_SESSION_TYPE,
		OFFER_SECURITY_TOKEN,
		SELECT_SCREEN,
		GET_SCREEN_SEGMENT,
		OFFER_INPUT,
		REQUEST_ALIAS,
		SEND_CHAT_MSG,
		ENABLE_ALIAS_MONITOR,
		ENABLE_CONNECTION_MONITOR
	};
	
	public enum SERVER_EVENT
	{
		NUM_SCREENS_CHANGED,
		CURSOR_GONE,
		CURSOR_MOVE,
		SCREEN_SEGMENT_UPDATE,
		SCREEN_SEGMENT_CHANGED,
		ENTIRE_SCREEN_UPDATE,
		ENTIRE_SCREEN_CHANGED,
		SCREEN_RESIZED,
		SCREEN_GONE,
		CHAT_MSG_TO_ALL, // includes from and text message
		CHAT_MSG_TO_USER, // for debug purposes, should assert only the target alias
		//gets the message
		
		// for identifying others listening in
		ALIAS_REGISTERED,
		ALIAS_UNREGISTERED,
		ALIAS_DISCONNECTED,
		ALIAS_CHANGED,
		
		// for monitoring connections to the server
		CONNECTION_ESTABLISHED, // server socket was bound
		FAILED_AUTHORIZATION, // a user failed to log in
		CONNECTION_CLOSED;

        public boolean isSerial()
        {
            boolean rval = (this != SCREEN_SEGMENT_CHANGED);
            
            return rval;
        }
	};
}
