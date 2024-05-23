package directory;

public class File extends Storage
{
	public File(Directory parent, String name)
	{
		super(parent, name);
	}

	@Override
	public boolean isDir()
	{
		return false;
	}
}
