import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

/**
 * Created by kiran on 4/6/16.
 */
public class JGitOps {
    public static void main(String[] args) throws IOException {
        String localPath, remotePath;
        Repository localRepo;
        Git git;

        localPath = "/Users/kiran/Documents/github/test-git";
        remotePath = "https://github.com/knadigatla/test-git.git";
        localRepo = new FileRepository(localPath + "/.git");
        git = new Git(localRepo);
    }
}
