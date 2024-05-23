package directory;

public abstract class Storage
{
	Directory parent;
	String name;

	public Storage(Directory parent, String name)
	{
		this.parent = parent;
		this.name = name;
	}

	public Directory getParent()
	{
		return parent;
	}

	public abstract boolean isDir();
}
