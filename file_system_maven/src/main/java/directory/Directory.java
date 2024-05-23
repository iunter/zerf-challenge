package directory;

import exceptions.DirectoryNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Directory extends Storage
{
	private Map<String, Storage> contentList = new HashMap<>();

	public Directory(Directory parent, String name)
	{
		super(parent, name);
	}

	@Override
	public boolean isDir()
	{
		return true;
	}

	public String ls()
	{
		Set<String> keySet = contentList.keySet();
		String listOfContent = String.join("\n", keySet);

		return listOfContent;
	}

	public String pwd()
	{
		String route = "/" + name;

		Directory directoryToRoute = parent;

		while (directoryToRoute != null)
		{
			route = "/" + directoryToRoute.name + route;

			directoryToRoute = directoryToRoute.getParent();
		}

		return route;

	}

	public Directory cd(String dirName) throws DirectoryNotFoundException
	{
		if (dirName.equals(".."))
		{
			if (parent == null)
			{
				return this;
			}

			return parent;
		}


		Storage dir = contentList.get(dirName);

		if (dir == null)
		{
			throw new DirectoryNotFoundException("No ha sido posible encontrar el directorio: " + dirName);
		}

		if (dir.isDir())
		{
			return (Directory) dir;
		}

		throw new DirectoryNotFoundException("No ha sido posible encontrar el directorio: " + dirName);
	}

	public void mkdir(String dirName)
	{
		Directory newDirectory = new Directory(this, dirName);
		contentList.put(dirName, newDirectory);
	}

	public void touch(String fileName)
	{
		File newFile = new File(this, fileName);
		contentList.put(fileName, newFile);
	}
}
