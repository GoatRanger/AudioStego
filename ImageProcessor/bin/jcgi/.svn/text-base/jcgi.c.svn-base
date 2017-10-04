/* 
 * Copyright (C) 1996 Andrew Scherpbier <andrew@sdsu.edu>
 *
 * This file is part of the San Diego State University Java Library.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 * Modified 3/30/97, Roger Whitney
 * Added classpath, removed -cs compile option 
 */
 
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <memory.h>
#include <errno.h>

#define	JAVA		"/opt/java/bin/java"
#define CLASSPATH	".:/opt/java/classes:/usr/local/lib/java"
#define	MAX_ARGS	200

char **build_argv(char **envp, char **vars, char *programName);
char *make_arg(char *name, char *value);


char *vars[] =
{
	"SERVER_SOFTWARE",
	"SERVER_NAME",
	"GATEWAY_INTERFACE",
	"SERVER_PROTOCOL",
	"SERVER_PORT",
	"REQUEST_METHOD",
	"PATH_INFO",
	"PATH_TRANSLATED",
	"SCRIPT_NAME",
	"QUERY_STRING",
	"REMOTE_HOST",
	"REMOTE_ADDR",
	"AUTH_TYPE",
	"REMOTE_USER",
	"REMOTE_IDENT",
	"CONTENT_TYPE",
	"CONTENT_LENGTH",
	NULL
};


int
main(int ac, char **av, char **envp)
{
	char	**argv;
	char	*programName;
	char	*path = av[0];
	char	dir[1024];
	
	char	*p = strrchr(av[0], '/');
	char	*q = strrchr(av[0], '.');

	/* Separate the path from the command */
	if (q)
	*q = '\0';

	/* Get rid of any extension like .cgi that the program may have */
	if (p)
	{
		programName = p + 1;
		*p = '\0';
	}
	else
	{
		programName = av[0];
		path = ".";
	}

	/* Go to the directory that contains the java program */
	if (path)
	{
		if (chdir(path) != 0)
		{
			printf("Content-type: text/plain\r\n\r\n");
			printf("Error code %s\n", errno);
			perror(path);
			exit(1);
		}
	}

	if (ac > 1)
	{
		printf("Path: '%s'\n", path);
		printf("Name: '%s'\n", programName);
	}
	argv = build_argv(envp, vars, programName);
	
	execv(JAVA, argv);

	printf("Content-type: text/plain\n\n");
	printf("jcgi: unable to execute '%s'\n", JAVA);
}


char **
build_argv(char **envp, char **vars, char *programName)
{
	char	**argv = (char **) malloc(sizeof(char *) * MAX_ARGS);
	int	n = 0;
	char	*buffer;
	int	i;
	char	*p;

	argv[n++] = "java";
	argv[n++] = "-classpath";
	argv[n++] = CLASSPATH;

	/*
	 * Add all the standard CGI variables
	 */
	for (i = 0; vars[i] && n < MAX_ARGS; i++)
	{
		if ((p = getenv(vars[i])))
		{
			argv[n++] = make_arg(vars[i], p);
		}
	}

	/*
	 * Now add any variables that start with HTTP_
	 */
	for (; *envp && n < MAX_ARGS; envp++)
	{
		if (strncmp(*envp, "HTTP_", 5) == 0)
		{
			p = strchr(*envp, '=');
			if (p)
			{
				*p++ = '\0';
				argv[n++] = make_arg(*envp, p);
			}
		}
	}

	argv[n++] = programName;
	argv[n] = NULL;
	return argv;
}

char *
make_arg(char *name, char *value)
{
	char	*buffer = malloc(strlen(value) + strlen(name) + 4);

	sprintf(buffer, "-D%s=%s", name, value);
	return buffer;
}

