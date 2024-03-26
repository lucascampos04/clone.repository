package org.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Usarname in GitHub: ");
        String username = scanner.nextLine();
        System.out.println("Name repository: ");
        String repository = scanner.nextLine();

        String pathBase = "C:/Users/Lucas/Documents";
        String pathGithub = "Github";
        String path = pathBase + "/" + pathGithub + "/" + repository;

        File githubFolder = new File(pathBase + "/" + pathGithub);
        if (!githubFolder.exists()) {
            if (!githubFolder.mkdirs()) {
                logger.error("Failed to create Github folder.");
                return;
            }
        }

        String url = "https://github.com/" + username + "/" + repository + ".git";

        try {
            logger.info("Cloning repository...");
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(path))
                    .call();
            logger.info("Repository cloned! " + path);
        } catch (GitAPIException e) {
            logger.error("Error cloning repository: " + e.getMessage());
        }
    }
}
