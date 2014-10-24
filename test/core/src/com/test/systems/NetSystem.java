package com.test.systems;

import java.io.IOException;

import com.test.network.NetClient;
import com.test.network.NetServer;

public class NetSystem
{
	public NetServer server;
	public NetClient client;
	
	public void load()
	{
		//setAsClient();
	}
	
	public void setAsServer()
	{
		try
		{
			server = new NetServer();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setAsClient()
	{
		client = new NetClient();
	}
	
}
