package com.jcope.debug;

import com.jcope.vnc.shared.StateMachine.CLIENT_EVENT;
import com.jcope.vnc.shared.StateMachine.SERVER_EVENT;

//import static com.jcope.debug.Debug.DEBUG;

public class LLog
{
	public static void e(Throwable e)
	{
		e(e, true);
	}
	
	public static void e(Throwable e, boolean rethrow)
	{
		e(e, rethrow, false);
	}
	
	public static void e(Throwable e, boolean rethrow, boolean hardStop)
	{
		e.printStackTrace(rethrow ? System.err : System.out);
		if (hardStop)
		{
			System.exit(127);
		}
		else if (rethrow)
		{
			if (e instanceof RuntimeException)
			{
				throw ((RuntimeException)e);
			}
			else
			{
				throw new RuntimeException(e);
			}
		}
	}
	
	/*
	public static void e(String err_msg)
	{
		e(new Exception(err_msg));
	}
	*/
	
	public static void i(String info_msg)
	{
		System.out.println(info_msg);
	}
	
	public static void w(String warn_msg)
	{
		System.err.println(warn_msg);
	}
	
	public static void logEvent(String source, SERVER_EVENT event, Object[] args)
	{
		_logEvent(source, event, args);
	}

	public static void logEvent(String source, CLIENT_EVENT event, Object[] args)
	{
		_logEvent(source, event, args);
	}

	private static void _logEvent(String source, Object event, Object[] args)
	{
		String eventName;
		if (event instanceof SERVER_EVENT)
		{
			eventName = ((SERVER_EVENT)event).name();
		}
		else
		{
			eventName = ((CLIENT_EVENT)event).name();
		}
		System.out.print(String.format("%s sent event: %s", source, eventName));
		if (args == null)
		{
			System.out.println(" - null");
		}
		else
		{
			System.out.print(" - [");
			boolean isFirst = true;
			for (Object obj : args)
			{
				if (isFirst)
				{
					isFirst = false;
				}
				else
				{
					System.out.print(", ");
				}
				System.out.print(obj == null ? "null" : obj.toString());
			}
			System.out.println("]");
		}
	}
}
