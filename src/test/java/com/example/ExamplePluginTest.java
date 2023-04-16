package com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

import java.io.File;


public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		boolean assertions = false;
		assert assertions = true;

		if (!assertions)
		{

			String selfPath = new File(
					RuneLite.class
							.getProtectionDomain()
							.getCodeSource()
							.getLocation()
							.toURI()
			).getAbsolutePath();


			//need to set base lang to java 11
//			String jCmd = ProcessHandle.current()
//					.info()
//					.command()
//					.orElse("java");


			new ProcessBuilder()
					.inheritIO()
					.command("java", "-ea", "-jar", selfPath, "--developer-mode")
					.start();
			return;
		}

		ExternalPluginManager.loadBuiltin(CompanionPetPlugin.class);

		RuneLite.main(args);

	}
}