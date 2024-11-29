package com.github.kanezaka;

import com.github.kanezaka.LeaguesAnnouncementFilterPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LeaguesAnnouncementFilterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LeaguesAnnouncementFilterPlugin.class);
		RuneLite.main(args);
	}
}