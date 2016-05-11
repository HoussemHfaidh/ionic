package com.utils;

import java.io.File;
import android.content.Context;

public class FileCache
{
	private File cacheDir;

	public FileCache(Context context)
	{
		cacheDir = context.getCacheDir();

		if (!cacheDir.exists())
		{
			cacheDir.mkdirs();
		}
	}

	public File getFile(String url)
	{
		// Identify images by hashcode
		String filename = String.valueOf(url.hashCode());
		File f = new File(cacheDir, filename);
		return f;
	}

	public void clear()
	{
		File[] files = cacheDir.listFiles();
		for (File f : files)
		{
			f.delete();
		}
	}

}
