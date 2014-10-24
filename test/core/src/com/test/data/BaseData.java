package com.test.data;

public class BaseData
{
	int baseId;
	int ownerId;
	
	public BaseData(int baseId)
	{
		this.baseId = baseId;
		ownerId = 0;
	}
	
	public void setOwner(int ownerId)
	{
		this.ownerId = ownerId;
	}

}
