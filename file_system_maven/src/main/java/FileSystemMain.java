import directory.Directory;
import exceptions.DirectoryNotFoundException;

import java.util.Scanner;

public class FileSystemMain
{

	private static Directory location = new Directory(null, "root");

	public static void main(String[] args)
	{
		System.out.println("Bienvenido al file system.");
		Scanner scanner = new Scanner(System.in);
		try
		{
			while (true)
			{
				System.out.println("Esperando su comando: ");
				String line = scanner.nextLine();

				String[] commandAndParam = line.split(" ", 2);

				try
				{
					ECommand command = ECommand.valueOf(commandAndParam[0]);

					try
					{
						if (commandAndParam.length > 1)
						{
							command.executeCommand(commandAndParam[1]);
						}
						else
						{
							command.executeCommand(null);
						}
					} catch (DirectoryNotFoundException e)
					{
						System.out.println(e.getMessage());
					}

				} catch (IllegalArgumentException e)
				{
					System.out.println("No se pudo encontrar el comando: " + commandAndParam[0]);
				}

			}
		} catch (Exception e)
		{
			System.out.println("Cerrando file system");
		}
	}

	enum ECommand
	{
		cd
				{
					@Override
					public void executeCommand(String param) throws DirectoryNotFoundException
					{
						location = location.cd(param);
					}
				},
		touch
				{
					@Override
					public void executeCommand(String param) throws DirectoryNotFoundException
					{
						location.touch(param);
					}
				},

		ls
				{
					@Override
					public void executeCommand(String param) throws DirectoryNotFoundException
					{
						String list = location.ls();

						System.out.println(list);
					}
				},
		mkdir
				{
					@Override
					public void executeCommand(String param) throws DirectoryNotFoundException
					{
						location.mkdir(param);
					}
				},
		pwd
				{
					@Override
					public void executeCommand(String param) throws DirectoryNotFoundException
					{
						String route = location.pwd();

						System.out.println(route);
					}
				};


		public void executeCommand(String param) throws DirectoryNotFoundException
		{

		}
	}
}
